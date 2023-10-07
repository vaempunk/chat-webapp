import { useEffect, useState } from "react";
import ChatApi from "../utils/chatApi";

function useChats() {
  const [chats, setChats] = useState([]);
  useEffect(() => {
    ChatApi.getChats()
      .then((resp) => resp.json())
      .then((data) => {
        setChats(data.content);
      });
  }, []);
  return chats;
}

export default useChats;
