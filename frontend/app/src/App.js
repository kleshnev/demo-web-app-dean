import React, { useState } from "react";
import LoginPage from './components/Auth/LoginPage'; // Update the import path
import Register from "./components/Auth/Register";
import Home from "./components/Home/Home";

import { BrowserRouter as Router, Routes ,Route } from 'react-router-dom';


const App = () => {
  const [loggedInUser, setLoggedInUser] = useState(""); // Initialize with an empty string

  return (
    <Router>
      <Routes>
        <Route
          path="/"
          element={<LoginPage setLoggedInUser={setLoggedInUser} />}
        />
        <Route
          path="/home"
          element={<Home loggedInUser={loggedInUser} />}
        />
        <Route exact path="/register" element={ <Register/>} />
      </Routes>
    </Router>
  );
};

export default App;

