import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      // Send login request to the correct login endpoint
      const response = await axios.post("/api/custom-login", {
        username: username,
        password: password,
      });

      // Assuming the server returns a success response upon successful login
      // For example, you can return a status code 200 or a specific success message
      // If login is successful, you can handle the redirection here.
      console.log("Login successful!");
      // Redirect to the dashboard or any other authenticated page
      navigate('/home');
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

export default Login;
