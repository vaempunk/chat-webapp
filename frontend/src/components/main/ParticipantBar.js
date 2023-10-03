import { Box, List, ListItemButton, ListSubheader, Paper } from "@mui/material";
import useParticipants from "../../hooks/useParticipants";

function ParticipantBar(props) {
  const { currentChatId } = props;
  const participants = useParticipants(currentChatId);

  const participantList = participants.map((participant) => (
    <ListItemButton key={participant.id}>{participant.username}</ListItemButton>
  ));

  return (
    <Paper>
      <Box padding={2}>
    <List subheader={<ListSubheader>Participants</ListSubheader>}>
      {participantList}
    </List>
    </Box>
    </Paper>
  );
}

export default ParticipantBar;
