import { useState } from "react";
import { useNavigate } from "react-router-dom";

const useLogout = () => {
  const navigate = useNavigate();
  const [loggedInUser, setLoggedInUser] = useState("");

  const handleLogout = () => {
    setLoggedInUser(""); // Clear the loggedInUser state
    localStorage.removeItem("loggedInUser"); // Replace "token" with the key used to store the user's authentication token
    localStorage.removeItem("token");
    console.log('logout completed')
    navigate("/login"); // Use the navigate function to redirect to the login page
  };

  return { loggedInUser, handleLogout };
};

export default useLogout;