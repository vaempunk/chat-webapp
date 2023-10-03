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

function SignUpDialog(props) {
  const { isOpen, onCancel, onRegisterSuccess } = props;
  const [error, setError] = useState(null);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);

  function onSingup() {
    UserApi.signup({ username, password })
      .then((resp) => (resp.ok ? { error: null } : resp.json()))
      .then((data) => {
        if (data.error) {
          const passwordErrorMessages = data.details
            .filter((detail) => detail.key === "password")
            .map((detail) => detail.value)
            .join(", ");
          const usernameErrorMessages = data.details
            .filter((detail) => detail.key === "username")
            .map((detail) => detail.value)
            .join(", ");
          setError({ passwordErrorMessages, usernameErrorMessages });
        } else {
          setError(null);
          onRegisterSuccess();
        }
      });
  }

  return (
    <Dialog open={isOpen} onClose={onCancel}>
      <DialogTitle>Sign up</DialogTitle>
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
          helperText={error === null ? "" : error.usernameErrorMessages}
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
          helperText={error === null ? "" : error.passwordErrorMessages}
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
        <Button onClick={onSingup}>Sign up</Button>
      </DialogActions>
    </Dialog>
  );
}

export default SignUpDialog;
