import React from "react";

const Home = () => {
  const username = localStorage.getItem("username");

  return (
    <div>
      {username && <div>Logged in as: {username}</div>}
      {/* Rest of the Home component content */}
    </div>
  );
};

export default Home;
