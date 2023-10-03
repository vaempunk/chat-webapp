import { useEffect, useState } from "react";
import { API_BASE_URL } from "../utils/apiConst";
import { AuthUtils } from "../utils/authUtils";

function useChats() {
  const [chats, setChats] = useState([]);
  useEffect(() => {
    fetch(`${API_BASE_URL}/chats`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + AuthUtils.getToken(),
      },
    })
      .then((resp) => resp.json())
      .then((data) => {
        setChats(data.content);
      });
  }, []);
  return chats;
}

export default useChats;
