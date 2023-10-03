package dev.vaem.websockets;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import dev.vaem.websockets.domain.entity.Chat;
import dev.vaem.websockets.domain.entity.Message;
import dev.vaem.websockets.domain.entity.Role;
import dev.vaem.websockets.domain.entity.User;
import dev.vaem.websockets.domain.repository.RoleRepository;
import dev.vaem.websockets.domain.service.ChatService;
import dev.vaem.websockets.domain.service.MessageService;
import dev.vaem.websockets.domain.service.UserService;

@SpringBootApplication
public class WebsocketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebsocketsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ChatService chatService, UserService userService,
			RoleRepository roleRepository, MessageService messageService) {
		return args -> {
			var roles = Arrays.asList(Role.Name.values()).stream()
					.map(roleName -> {
						var role = new Role();
						role.setName(roleName);
						return role;
					}).toList();
			roleRepository.saveAll(roles);

			var chat = new Chat();
			chat.setName("RU");
			chat.setCooldownMs(0);
			chat = chatService.addChat(chat);

			chat = new Chat();
			chat.setName("EN");
			chat.setCooldownMs(0);
			chat = chatService.addChat(chat);

			var admin = new User();
			admin.setUsername("admin");
			admin.setPassword("admin");
			admin = userService.registerUser(admin);
			userService.assignRoleToUser(admin.getId(), Role.Name.ADMIN);

			var message = new Message();
			message.setChatId(chat.getId());
			message.setSenderId(admin.getId());
			message.setText("Hello!");
			messageService.sendMessage(message);
			Thread.sleep(100);
			message = new Message();
			message.setChatId(chat.getId());
			message.setSenderId(admin.getId());
			message.setText("Goodbye!");
			messageService.sendMessage(message);
		};
	}

}
