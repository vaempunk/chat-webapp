import Main from "./components/main/Main";
import Navbar from "./components/nav/Navbar";
import { CssBaseline, ThemeProvider, createTheme } from "@mui/material";
import { AuthUtils } from "./utils/authUtils";
import { useState } from "react";
import LoginRequirement from "./components/LoginRequirement";

const darkTheme = createTheme({
  palette: {
    mode: "dark",
  },
});

function App() {
  const [authenticated, setAuthenticated] = useState(AuthUtils.getToken());
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <div className="App">
        <Navbar authenticated={authenticated} setAuthenticated={setAuthenticated} />
        {authenticated && <Main />}
        {!authenticated && <LoginRequirement />}
      </div>
    </ThemeProvider>
  );
}

export default App;
