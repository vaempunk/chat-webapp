import { API_BASE_URL } from "./apiConst";
import { AuthUtils } from "./authUtils";

function getMessages(chatId) {
  return fetch(`${API_BASE_URL}/chats/${chatId}/messages`, {
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
  });
}

function sendMessage(chatId, text) {
  return fetch(`${API_BASE_URL}/chats/${chatId}/messages`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify({
      text,
    }),
  });
}

function deleteMessage(messageId) {
  return fetch(`${API_BASE_URL}/messages/${messageId}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
  });
}

const MessageApi = {
  getMessages,
  sendMessage,
  deleteMessage,
};

export default MessageApi;
