import AddIcon from "@mui/icons-material/Add";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import {
  Box,
  IconButton,
  List,
  ListItemButton,
  ListSubheader,
  Paper,
  Stack
} from "@mui/material";
import useChats from "../../hooks/useChats";

function ChatBar(props) {
  const { currentChatId, onChangeChatId } = props;
  const chats = useChats();

  const chatList = chats.map((chat) => (
    <ListItemButton
      key={chat.id}
      selected={currentChatId === chat.id}
      onClick={() => onChangeChatId(chat.id)}
    >
      <Stack
        direction={"row"}
        justifyContent={"space-between"}
        spacing={2}
        sx={{ width: "100%" }}
        alignItems={"center"}
      >
        <Box>{chat.name}</Box>
        <Box>
          <IconButton size="small">
            <MoreVertIcon />
          </IconButton>
        </Box>
      </Stack>
    </ListItemButton>
  ));

  return (
    <Paper>
      <Box padding={2}>
        <List
          subheader={
            <ListSubheader>
              <Stack
                direction={"row"}
                spacing={2}
                justifyContent={"space-between"}
              >
                <Box>Chats</Box>
                <Box>
                  <IconButton size="small">
                    <AddIcon />
                  </IconButton>
                </Box>
              </Stack>
            </ListSubheader>
          }
        >
          {chatList}
        </List>
      </Box>
    </Paper>
  );
}

export default ChatBar;
