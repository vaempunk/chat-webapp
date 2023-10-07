import { useEffect, useState } from "react";
import { UserApi } from "../utils/userApi";

function useUser() {
  const [user, setUser] = useState({ role: { name: "" } });

  useEffect(() => {
    UserApi.getMe()
      .then((resp) => resp.json())
      .then((data) => {
        setUser(data);
      });
  }, []);

  return user;
}

export default useUser;
