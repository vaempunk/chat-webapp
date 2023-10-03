import { AccountCircle } from "@mui/icons-material";
import MenuIcon from "@mui/icons-material/Menu";
import {
  Alert,
  AppBar,
  Box,
  Button,
  IconButton,
  Snackbar,
  Stack,
  Toolbar,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { AuthUtils } from "../../utils/authUtils";
import LoginDialog from "./LoginDialog";
import SignUpDialog from "./SignUpDialog";

function Navbar() {
  const [isLoginDialogOpen, setLoginDialogOpen] = useState(false);
  const [isSingupDialogOpen, setSignupDialogOpen] = useState(false);
  const [successLoginSnackbarOpen, setSucccessLoginSnackbarOpen] =
    useState(false);
  const [successSignupSnackbarOpen, setSuccessSignupSnackbarOpen] =
    useState(false);

  const navRightSide = (
    <div>
      {AuthUtils.getToken() && (
        <Stack direction={"row"} spacing={2}>
          <AccountCircle fontSize="large" />
          <Button color="inherit" onClick={() => AuthUtils.removeToken()}>
            Logout
          </Button>
        </Stack>
      )}
      {!AuthUtils.getToken() && (
        <Stack direction={"row"} spacing={2}>
          <Button color="inherit" onClick={() => setLoginDialogOpen(true)}>
            Login
          </Button>
          <LoginDialog
            isOpen={isLoginDialogOpen}
            onCancel={() => setLoginDialogOpen(false)}
            onLoginSuccess={(data) => {
              AuthUtils.setToken(data.token);
              setLoginDialogOpen(false);
              setSucccessLoginSnackbarOpen(true);
            }}
          />
          <Button color="inherit" onClick={() => setSignupDialogOpen(true)}>
            Sign up
          </Button>
          <SignUpDialog
            isOpen={isSingupDialogOpen}
            onCancel={() => setSignupDialogOpen(false)}
            onRegisterSuccess={() => {
              setSignupDialogOpen(false);
              setSuccessSignupSnackbarOpen(true);
            }}
          />
        </Stack>
      )}
    </div>
  );

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            News
          </Typography>
          {navRightSide}
        </Toolbar>
      </AppBar>
      <Snackbar
        open={successLoginSnackbarOpen}
        autoHideDuration={5000}
        onClose={() => setSucccessLoginSnackbarOpen(false)}
      >
        <Alert severity="success">You have been successfully logged in.</Alert>
      </Snackbar>
      <Snackbar
        open={successSignupSnackbarOpen}
        autoHideDuration={5000}
        onClose={() => setSuccessSignupSnackbarOpen(false)}
      >
        <Alert severity="success">You have been successfully signed up.</Alert>
      </Snackbar>
    </Box>
  );
}

export default Navbar;
