package dev.vaem.websockets.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import dev.vaem.websockets.web.util.JwtUtil;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebSocketMessageBroker
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@AllArgsConstructor
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

	private final JwtUtil jwtUtil;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat").setAllowedOriginPatterns("*");
		registry.addEndpoint("/chat").setAllowedOriginPatterns("*").withSockJS();
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(new ChannelInterceptor() {
			@Override
			public Message<?> preSend(Message<?> message, MessageChannel channel) {
				var accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
				if (accessor == null || !StompCommand.CONNECT.equals(accessor.getCommand()))
					return message;

				var token = getBearerToken(accessor);
				if (token == null)
					return message;

				var subject = jwtUtil.parseToken(token);
				if (subject.isPresent()) {
					accessor.setUser(subject.get());
				}
				return message;
			}

			private String getBearerToken(StompHeaderAccessor accessor) {
				String bearerToken = accessor.getFirstNativeHeader("Authorization");
				if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
					return bearerToken.substring(7);
				}
				return null;
			}
		});
	}

}
