// src/components/Home.js
import React, {useEffect } from "react";
import MyButton from "../Common/MyButton"; // Import your MyButton component
import "../../styles/Home/Home.css"; 
import { Link} from "react-router-dom";
import useLogout from "../Auth/useLogout";

const Home = () => {
  const { handleLogout } = useLogout();
  const [loggedInUser, setLoggedInUser] = React.useState("");
  console.log('loggedInUser:', loggedInUser);

  useEffect(() => {
    const storedUser = localStorage.getItem("loggedInUser");
    if (storedUser) {
      setLoggedInUser(storedUser);
    }
  }, []);
  const generateRoomId = () => {
    // Generate a random 6-character alphanumeric room ID
    const roomId = Math.random().toString(36).substring(2, 8);
    return roomId;
  };

  return (
    <div className="home-container">
      <h1>Welcome to the Home Page.</h1>
      <p>Logged in as {loggedInUser}</p>
      <p>token: {localStorage.getItem("token")}</p>
      <p>user: {localStorage.getItem("loggedInUser")}</p>
      <div className="button-container">
      <Link to={`/room/${generateRoomId()}?user=${loggedInUser}`}>
          <MyButton label="Create Room" />
        </Link>
        <Link to="/join-room">
          <MyButton label="Join Room" />
        </Link>
        <button onClick={handleLogout}>Logout</button>
      </div>
    </div>
  );
};

export default Home;
