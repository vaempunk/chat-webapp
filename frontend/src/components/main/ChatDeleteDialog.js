import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
} from "@mui/material";
import ChatApi from "../../utils/chatApi";

function ChatDeleteDialog(props) {
  const { chatId, isOpen, onCancel, onDeleteSuccess } = props;

  function onDelete() {
    ChatApi.deleteChat(chatId);
    onDeleteSuccess();
  }

  return (
    <Dialog open={isOpen} onClose={onCancel}>
      <DialogTitle>Chat settings</DialogTitle>
      <DialogContent></DialogContent>
      <DialogActions>
        <Button onClick={onCancel}>Cancel</Button>
        <Button onClick={onDelete}>Delete</Button>
      </DialogActions>
    </Dialog>
  );
}

export default ChatDeleteDialog;
