import { Box, Typography } from "@mui/material";

function LoginRequirement() {
  return (
    <Box sx={{ display: "flex", justifyContent: "center", alignItems: "center", height: "90vh" }}>
      <Typography variant="h4">Please sign in.</Typography>
    </Box>
  );
}

export default LoginRequirement;
