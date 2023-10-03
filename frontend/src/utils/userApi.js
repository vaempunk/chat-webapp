import { API_BASE_URL } from "./apiConst";
import { AuthUtils } from "./authUtils";

function login(username, password) {
  return fetch(`${API_BASE_URL}/auth/token`, {
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify({
      username,
      password,
    }),
  });
}

function signup(user) {
  return fetch(`${API_BASE_URL}/signup`, {
    method: "POST",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(user),
  });
}

function isUsernameAvailable(username) {
  return fetch(`${API_BASE_URL}/signup/username-availability`, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify({
      username: username,
    }),
  });
}

function getMe() {
  return fetch(`${API_BASE_URL}/users/me`, {
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
  });
}

function updateMe(newUser) {
  return fetch(`${API_BASE_URL}/users/me`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify(newUser),
  });
}

function updatePassword(oldPassword, newPassword) {
  return fetch(`${API_BASE_URL}/users/me/password`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify({
      oldPassword,
      newPassword,
    }),
  });
}

function deleteMe() {
  return fetch(`${API_BASE_URL}/users/me`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
  });
}

function updateUserRole(userId, role) {
  return fetch(`${API_BASE_URL}/users/${userId}/role`, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + AuthUtils.getToken(),
    },
    body: JSON.stringify({
      role,
    }),
  });
}

export const UserApi = {
  login,
  signup,
  isUsernameAvailable,
  getMe,
  updateMe,
  updatePassword,
  deleteMe,
  updateUserRole,
};
