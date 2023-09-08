import React from 'react';
import './../../styles/Room/RoomCreationPopup.css';
import userAvatar from '../../userFiles/avatar/userAvatar.jpg'
import emptyPlayer from '../../userFiles/avatar/emptyPlayer.png'
const RoomCreationPopup = ({ roomCode, userCreatorName }) => {
  console.log("pop-up name pass "+ userCreatorName )
  const handleClose = () => {
    // You can implement this based on your application's logic
    // For example, by setting a state to hide the popup
  };

  return (
    <div className="popup">
      <div className="popup-content">
        <div className="top">
          <h2>Room {roomCode}</h2>
        </div>
        <div className="body">
          <div className="user">
            <div className="avatar">
              <img src={userAvatar}/>
            </div>
            <div className="user-info">
              <p>{userCreatorName}</p>
            </div>
          </div>
          <div className="divider"></div> {/* Add another divider */}
          <div className="user">
            <div className="avatar">
            <img src={emptyPlayer} alt="Waiting for player..." />
            </div>
            <div className="user-info">
              <p>Empty...</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default RoomCreationPopup;
