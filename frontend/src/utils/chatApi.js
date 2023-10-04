import { API_BASE_URL } from "./apiConst";
import { AuthUtils } from "./authUtils";

function getChats() {
  return fetch(`${API_BASE_URL}/chats`, {
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
  });
}

function getParticipants(chatId) {
  return fetch(`http://localhost:8080/chats/${chatId}/participants`, {
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
  });
}

function addChat(chat) {
  return fetch(`${API_BASE_URL}/chats`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify(chat),
  })
}

function isChatNameAvailable(chatName) {
  return fetch(`${API_BASE_URL}/chats/name-availability`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify({
      name: chatName,
    }),
  });
}

function updateChat(chatId, chat) {
  return fetch(`${API_BASE_URL}/chats/${chatId}`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify(chat),
  });
}

function deleteChat(chatId) {
  return fetch(`${API_BASE_URL}/chats/${chatId}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
  });
}

const ChatApi = {
  getChats,
  getParticipants,
  addChat,
  isChatNameAvailable,
  updateChat,
  deleteChat,
};

export default ChatApi;
