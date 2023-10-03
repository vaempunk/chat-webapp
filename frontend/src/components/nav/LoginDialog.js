import { Visibility, VisibilityOff } from "@mui/icons-material";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  IconButton,
  TextField,
} from "@mui/material";
import { useState } from "react";
import { UserApi } from "../../utils/userApi";

function LoginDialog(props) {
  const { isOpen, onCancel, onLoginSuccess } = props;
  const [error, setError] = useState(null);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  function onLogin() {
    UserApi.login(username, password)
    .then((resp) => resp.json())
    .then((data) => {
      if (data.error) {
        setError(data.error);
        return;
      }
      onLoginSuccess(data);
    });
  }

  return (
    <Dialog open={isOpen} onClose={onCancel}>
      <DialogTitle>Login</DialogTitle>
      <DialogContent>
        <DialogContentText>Enter your username and password</DialogContentText>
        <TextField
          autoFocus
          fullWidth
          label="Username"
          margin="dense"
          variant="standard"
          onChange={(e) => setUsername(e.target.value)}
          error={error !== null}
          helperText={error}
        />
        <br />
        <TextField
          fullWidth
          label="Password"
          type={showPassword ? "text" : "password"}
          margin="dense"
          variant="standard"
          onChange={(e) => setPassword(e.target.value)}
          error={error !== null}
          helperText={error}
          InputProps={{
            endAdornment: (
              <IconButton
                onClick={() => setShowPassword(!showPassword)}
                aria-label="toggle password visibility"
              >
                {showPassword ? <VisibilityOff /> : <Visibility />}
              </IconButton>
            ),
          }}
        />
      </DialogContent>
      <DialogActions>
        <Button onClick={onCancel}>Cancel</Button>
        <Button onClick={onLogin}>Login</Button>
      </DialogActions>
    </Dialog>
  );
}

export default LoginDialog;
