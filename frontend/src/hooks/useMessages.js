import { useEffect, useRef, useState } from "react";
import { API_BASE_URL, WS_BASE_URL } from "../utils/apiConst";
import { AuthUtils } from "../utils/authUtils";
import { Stomp } from "@stomp/stompjs";

function useMessages(chatId) {
  const [messages, setMessages] = useState([]);

  function onMessageRecieve(message) {
    setMessages((messages) => [message, ...messages]);
  }

  function onMessageDelete(messageDelete) {
    setMessages((messages) =>
      messages.filter((message) => message.id !== messageDelete.messageId)
    );
  }

  useEffect(() => {
    if (!chatId) return;
    fetch(`${API_BASE_URL}/chats/${chatId}/messages`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + AuthUtils.getToken(),
      },
    })
      .then((resp) => resp.json())
      .then((data) => {
        setMessages(data.content);
      });
  }, [chatId]);

  useChatSubscription(chatId, onMessageRecieve, onMessageDelete);
  return messages;
}

export default useMessages;

function useChatSubscription(chatId, onRecive, onDelete) {
  const client = useRef(Stomp.client(`${WS_BASE_URL}/chat`));
  const subscription = useRef(null);
  const deleteSubscription = useRef(null);

  useEffect(() => {
    function changeSubscription() {
      if (subscription.current) {
        subscription.current.unsubscribe();
        deleteSubscription.current.unsubscribe();
      }
      subscription.current = client.current.subscribe(
        `/topic/${chatId}/messages`,
        (message) => onRecive(JSON.parse(message.body))
      );
      deleteSubscription.current = client.current.subscribe(
        `/topic/${chatId}/messages/delete`,
        (message) => onDelete(JSON.parse(message.body))
      );
    }

    if (!client.current.connected) {
      client.current.connect(
        {
          Authorization: "Bearer " + AuthUtils.getToken(),
        },
        () => changeSubscription()
      );
    } else changeSubscription();
  }, [chatId, onRecive, onDelete]);
}
