import { Cookies } from "react-cookie";

const cookies = new Cookies();

function removeToken() {
  cookies.remove("token");
}

function setToken(token) {
  cookies.set("token", token);
}

function getToken(token) {
  return cookies.get("token");
}

export const AuthUtils = {
  removeToken,
  setToken,
  getToken,
};
