import { Box, List, ListItemButton, ListSubheader } from "@mui/material";
import useParticipants from "../../hooks/useParticipants";

function ParticipantBar(props) {
  const { currentChatId } = props;
  const participants = useParticipants(currentChatId);

  const participantList = participants.map((participant) => (
    <ListItemButton key={participant.id}>{participant.username}</ListItemButton>
  ));

  return (
      <Box padding={1.5}>
    <List subheader={<ListSubheader>Participants</ListSubheader>}>
      {participantList}
    </List>
    </Box>
  );
}

export default ParticipantBar;
