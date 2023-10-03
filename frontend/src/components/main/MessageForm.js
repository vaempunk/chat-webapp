import SendIcon from "@mui/icons-material/Send";
import { Button, Stack, TextField } from "@mui/material";
import { useReducer, useState } from "react";
import messageReducer from "../../reducers/messageReducer";

function MessageForm(props) {
  const { chatId } = props;
  const [text, setText] = useState("");
  const [state, dispatch] = useReducer(messageReducer, { error: null });

  function onSendClick() {
    dispatch({
      type: "SEND",
      chatId,
      text,
    });
    setText("");
  }

  return (
    <Stack direction="row" spacing={2}>
      <TextField
        label="Write message"
        multiline
        fullWidth
        onChange={(e) => setText(e.target.value)}
        value={text}
        error={state.error}
        helperText={state.error}
        size="small"
      />
      <Button
        color="primary"
        variant="outlined"
        size="large"
        onClick={onSendClick}
      >
        <SendIcon />
      </Button>
    </Stack>
  );
}

export default MessageForm;
