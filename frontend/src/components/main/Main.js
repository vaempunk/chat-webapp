import { Container, Grid } from "@mui/material";
import { useState } from "react";
import ChatBar from "./ChatBar";
import MessageBar from "./MessageBar";
import ParticipantBar from "./ParticipantBar";

function Main() {
  const [currentChatId, setCurrentChatId] = useState(null);

  return (
    <Container>
      <Grid container spacing={4} margin={2}>
        <Grid item xs={3}>
          <ChatBar
            currentChatId={currentChatId}
            onChangeChatId={setCurrentChatId}
          />
        </Grid>
        <Grid item xs={6}>
          <MessageBar currentChatId={currentChatId} />
        </Grid>
        <Grid item xs={3}>
          <ParticipantBar currentChatId={currentChatId} />
        </Grid>
      </Grid>
    </Container>
  );
}

export default Main;
