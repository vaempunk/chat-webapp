import { useEffect, useState } from "react";
import { AuthUtils } from "../utils/authUtils";

function useParticipants(chatId) {
  const [participants, setParticipants] = useState([]);

  useEffect(() => {
    if (!chatId) return;
    fetch(`http://localhost:8080/chats/${chatId}/participants`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + AuthUtils.getToken(),
      },
    })
      .then((resp) => resp.json())
      .then((data) => {
        setParticipants(data.content);
      });
  }, [chatId]);

  return participants;
}

export default useParticipants;
