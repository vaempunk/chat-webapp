import { API_BASE_URL } from "../utils/apiConst";
import { AuthUtils } from "../utils/authUtils";

function messageReducer(state, action) {
  if (action.type === "SEND") {
    fetch(`${API_BASE_URL}/chats/${action.chatId}/messages`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + AuthUtils.getToken(),
      },
      body: JSON.stringify({
        text: action.text,
      }),
    })
      .then((resp) => {
        if (resp.ok) {
          return Promise.resolve({ error: null });
        } else {
          return resp.json();
        }
      })
      .catch((error) => {
        console.error(error);
        return state;
      });
  } else if (action.type === "DELETE") {
    fetch(`${API_BASE_URL}/messages/${action.messageId}`, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + AuthUtils.getToken(),
      },
    });
  }

  return state;
}

export default messageReducer;
