import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const LoginPage = ({ setLoggedInUser }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      // Send login request to the correct login endpoint
      const response = await axios.post("/api/login", {
        username: username,
        password: password,
      });

      const token = response.data.token;
      document.cookie = `token=${token}; path=/; HttpOnly; Secure`;
      console.log("setLoggedInUser:", setLoggedInUser);
      localStorage.setItem("token", token); // Store the token in localStorage
      localStorage.setItem("loggedInUser", username);
      console.log("Login successful!");
      setLoggedInUser(username); // Set the logged-in user
      console.log("Updated loggedInUser:", username);
      navigate("/home");
    } catch (error) {
      setError("Invalid username or password"); 
    }
  };

  return (
    <div>
      <h2>Sign In</h2>
      {error && <div>{error}</div>}
      <form onSubmit={handleLogin}>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <button type="submit">Sign In</button>
      </form>
    </div>
  );
};

export default LoginPage;
