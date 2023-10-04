import { API_BASE_URL } from "../utils/apiConst";
import { AuthUtils } from "../utils/authUtils";

function messageReducer(state, action) {
  if (action.type === "SEND") {
    if (action.data.error === null) return { textError: null };
    const textError = action.data.details
      .filter((d) => d.key === "text")
      .map((d) => d.value)
      .join(", ");
    return { textError };
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
