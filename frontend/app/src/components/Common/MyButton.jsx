// src/components/Common/MyButton.js
import React from "react";
import "../../styles/Button/MyButton.css";

const MyButton = ({ label, onClick }) => {
  return (
    <button className="my-button" onClick={onClick}>
      {label}
    </button>
  );
};

export default MyButton;
