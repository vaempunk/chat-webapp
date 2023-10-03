import Main from "./components/main/Main";
import Navbar from "./components/nav/Navbar";
import { CssBaseline, ThemeProvider, createTheme } from "@mui/material";
import { AuthUtils } from "./utils/authUtils";

const darkTheme = createTheme({
  palette: {
    mode: "dark",
  },
});

function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <div className="App">
        <Navbar />
        {AuthUtils.getToken() && <Main />}
      </div>
    </ThemeProvider>
  );
}

export default App;
