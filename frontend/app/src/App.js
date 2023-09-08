import React, { useState } from "react";
import LoginPage from './components/Auth/LoginPage'; // Update the import path
import Register from "./components/Auth/Register";
import Home from "./components/Home/Home";
import Room from "./components/Room/Room"
import { isAuthenticated } from "./components/Auth/routeGuard";
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Navigate } from 'react-router';

const App = () => {
  const storedUser = localStorage.getItem("loggedInUser");

  const [loggedInUser, setLoggedInUser] = useState(storedUser ? storedUser : "");
  return (
    <Router>
      <Routes>
        <Route
          path="/login"
          element={<LoginPage setLoggedInUser={setLoggedInUser} />}
        />
        <Route
          path="/home"
          element={
            isAuthenticated() ? (
              <Home loggedInUser={loggedInUser} setLoggedInUser={setLoggedInUser} />
            ) : (
              <Navigate to="/login" />
            )
          }
        />
        <Route exact path="/register" element={<Register />} />
        <Route path="/room/:id" element={<Room />} />
      </Routes>
    </Router>
  );
};

export default App;

