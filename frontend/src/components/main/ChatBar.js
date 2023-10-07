import AddIcon from "@mui/icons-material/Add";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import {
  Box,
  IconButton,
  List,
  ListItemButton,
  ListSubheader,
  Stack,
} from "@mui/material";
import { useState } from "react";
import useChats from "../../hooks/useChats";
import useUser from "../../hooks/useUser";
import AddChatDialog from "./AddChatDialog";
import ChatDeleteDialog from "./ChatDeleteDialog";

function ChatBar(props) {
  const { currentChatId, onChangeChatId } = props;
  const [addChatOpen, setAddChatOpen] = useState(false);
  const [deleteChatOpen, setDeleteChatOpen] = useState(false);
  const chats = useChats();
  const user = useUser();

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
          {user.role.name === "ADMIN" && (
            <IconButton size="small" onClick={() => setDeleteChatOpen(true)}>
              <MoreVertIcon />
            </IconButton>
          )}
        </Box>
      </Stack>
    </ListItemButton>
  ));

  return (
    <Box padding={1.5}>
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
                {user.role.name === "ADMIN" && (
                  <IconButton size="small" onClick={() => setAddChatOpen(true)}>
                    <AddIcon />
                  </IconButton>
                )}
              </Box>
            </Stack>
          </ListSubheader>
        }
      >
        {chatList}
      </List>
      <AddChatDialog
        isOpen={addChatOpen}
        onCancel={() => setAddChatOpen(false)}
        onAddSuccess={() => setAddChatOpen(false)}
      />
      <ChatDeleteDialog
        chatId={currentChatId}
        isOpen={deleteChatOpen}
        onCancel={() => setDeleteChatOpen(false)}
        onDeleteSuccess={() => setDeleteChatOpen(false)}
      />
    </Box>
  );
}

export default ChatBar;
