import React from "react";
import Login from './components/Auth/Login'; // Update the import path
import Register from "./components/Auth/Register";
import { BrowserRouter as Router, Routes ,Route } from 'react-router-dom';

function App() {
  return (
   <Router>
      <div className="App">
        <Routes>
          <Route exact path="/login" element={ <Login/>} />
          <Route exact path="/register" element={ <Register/>} />
        </Routes>
      </div>
      </Router>
  );
}

export default App;
