import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField,
} from "@mui/material";
import { useState } from "react";
import ChatApi from "../../utils/chatApi";

function AddChatDialog(props) {
  const { isOpen, onCancel, onAddSuccess } = props;
  const [error, setError] = useState(null);
  const [name, setName] = useState("");

  function onAdd() {
    ChatApi.addChat({ name: name, cooldownMs: 0 })
      .then((resp) => {
        if (resp.ok) return Promise.resolve({ error: null });
        else return resp.json();
      })
      .then((data) => setError(data.error))
      .then(() => onAddSuccess());
  }

  return (
    <Dialog open={isOpen} onClose={onCancel}>
      <DialogTitle>Add new chat</DialogTitle>
      <DialogContent>
        <TextField
          autoFocus
          fullWidth
          label="Name"
          margin="dense"
          variant="standard"
          onChange={(e) => setName(e.target.value)}
          error={error !== null}
          helperText={error}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onCancel}>Cancel</Button>
        <Button onClick={onAdd}>Add</Button>
      </DialogActions>
    </Dialog>
  );
}

export default AddChatDialog;