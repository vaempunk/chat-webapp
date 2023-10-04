import SendIcon from "@mui/icons-material/Send";
import { Button, Stack, TextField } from "@mui/material";
import { useReducer, useState } from "react";
import messageReducer from "../../reducers/messageReducer";
import MessageApi from "../../utils/messageApi";

function MessageForm(props) {
  const { chatId } = props;
  const [text, setText] = useState("");
  const [state, dispatch] = useReducer(messageReducer, { textError: null });

  function onSendClick() {
    MessageApi.sendMessage(chatId, text)
      .then((resp) => {
        if (resp.ok) {
          return Promise.resolve({ error: null });
        } else {
          return resp.json();
        }
      })
      .then((data) => dispatch({ type: "SEND", data: data }));
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
        error={state.textError !== null}
        helperText={state.textError}
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
