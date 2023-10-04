import { Container, Grid, Paper } from "@mui/material";
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
          <Paper variant="outlined" sx={{ borderRadius: 5 }}>
            <ChatBar
              currentChatId={currentChatId}
              onChangeChatId={setCurrentChatId}
            />
          </Paper>
        </Grid>
        {currentChatId && (
          <Grid item xs={6}>
            <Paper variant="outlined" sx={{ borderRadius: 5 }}>
              <MessageBar currentChatId={currentChatId} />
            </Paper>
          </Grid>
        )}
        {currentChatId && (<Grid item xs={3}>
          <Paper variant="outlined" sx={{ borderRadius: 5 }}>
            <ParticipantBar currentChatId={currentChatId} />
          </Paper>
        </Grid>)}
      </Grid>
    </Container>
  );
}

export default Main;
