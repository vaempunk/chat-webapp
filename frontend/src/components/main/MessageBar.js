import DeleteIcon from "@mui/icons-material/Delete";
import {
  IconButton,
  List,
  ListItem,
  ListItemText,
  ListSubheader,
  Paper,
  Stack,
} from "@mui/material";
import { useReducer } from "react";
import useMessages from "../../hooks/useMessages";
import useUser from "../../hooks/useUser";
import messageReducer from "../../reducers/messageReducer";
import MessageForm from "./MessageForm";

function MessageBar(props) {
  const { currentChatId } = props;
  const messages = useMessages(currentChatId);
  const [state, dispatch] = useReducer(messageReducer, { error: null });
  const user = useUser();

  function onMessageDelete(messageId) {
    dispatch({ type: "DELETE", messageId });
  }

  const messageList = messages
    .filter((message) => message.chat.id === currentChatId)
    .map((message) => (
      <ListItem key={message.id}>
        <Stack direction="row" spacing={2} justifyContent="space-between" sx={{ width: "100%" }}>
          <ListItemText>
            {message.sender.username}: {message.text} ({message.sentAt})
          </ListItemText>
          {user.role.name === "ADMIN" && (
            <IconButton onClick={() => onMessageDelete(message.id)}>
              <DeleteIcon />
            </IconButton>
          )}
        </Stack>
      </ListItem>
    ))
    .reverse();

  return (
    <Paper>
      <Stack padding={2}>
        <List subheader={<ListSubheader>Messages</ListSubheader>}>
          {messageList}
        </List>
        <MessageForm chatId={currentChatId} />
      </Stack>
    </Paper>
  );
}

export default MessageBar;
