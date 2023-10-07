import DeleteIcon from "@mui/icons-material/Delete";
import {
  IconButton,
  List,
  ListItem,
  ListItemText,
  ListSubheader,
  Stack,
} from "@mui/material";
import moment from "moment/moment";
import useMessages from "../../hooks/useMessages";
import useUser from "../../hooks/useUser";
import MessageApi from "../../utils/messageApi";
import MessageForm from "./MessageForm";

function MessageBar(props) {
  const { currentChatId } = props;
  const messages = useMessages(currentChatId);
  const user = useUser();

  function onMessageDelete(messageId) {
    MessageApi.deleteMessage(messageId);
  }

  const messageList = messages
    .filter((message) => message.chat.id === currentChatId)
    .map((message) => (
      <ListItem key={message.id}>
        <Stack
          direction="row"
          spacing={2}
          justifyContent="space-between"
          sx={{ width: "100%" }}
        >
          <ListItemText
            primary={message.sender.username}
            secondary={message.text}
            sx={{
              width: "75%",
            }}
          ></ListItemText>
          <ListItemText
            sx={{
              width: "25%",
              textAlign: "right",
            }}
            secondary={moment(message.sentAt, moment.ISO_8601).fromNow()}
          ></ListItemText>
          {user.role.name === "ADMIN" && (
            <IconButton
              onClick={() => onMessageDelete(message.id)}
              sx={{ height: "100%" }}
            >
              <DeleteIcon />
            </IconButton>
          )}
        </Stack>
      </ListItem>
    ))
    .reverse();

  return (
    <Stack padding={1.5}>
      <List subheader={<ListSubheader>Messages</ListSubheader>}>
        {messageList}
      </List>
      <MessageForm chatId={currentChatId} />
    </Stack>
  );
}

export default MessageBar;
