import React, { useState } from "react";
import axios from "axios";

const Register = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      // Send registration request to the backend API
      const response = await axios.post("/api/register", {
        username: username,
        password: password,
      });

      // Handle the response from the backend if needed (e.g., show a success message)
      console.log("Registration successful!");
    } catch (error) {
      if (error.response && error.response.status === 409) {
        setError("User with this username already exists.");
      } else {
        setError("An error occurred while registering the user.");
      }
  };
}

  return (
    <div>
      <h2>Register</h2>
      {error && <div>{error}</div>}
      <form onSubmit={handleRegister}>
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
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;
