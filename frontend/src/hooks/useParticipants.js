import { useEffect, useState } from "react";
import ChatApi from "../utils/chatApi";

function useParticipants(chatId) {
  const [participants, setParticipants] = useState([]);

  useEffect(() => {
    if (!chatId) return;
    ChatApi.getParticipants(chatId)
      .then((resp) => resp.json())
      .then((data) => {
        setParticipants(data.content);
      });
  }, [chatId]);

  return participants;
}

export default useParticipants;
