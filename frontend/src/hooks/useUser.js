import { useEffect, useState } from "react";
import { API_BASE_URL } from "../utils/apiConst";
import { AuthUtils } from "../utils/authUtils";

function useUser() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    fetch(`${API_BASE_URL}/users/me`, {
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + AuthUtils.getToken(),
      },
    })
      .then((resp) => resp.json())
      .then((data) => {
        setUser(data);
      });
  }, []);

  return user;
}

export default useUser;
