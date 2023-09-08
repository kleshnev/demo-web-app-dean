import React from 'react';
import RoomCreationPopup from './RoomCreationPopUp';
import { useParams, useLocation } from 'react-router-dom';
import '../../styles/Room/Room.css';

const Room = () => {

  const { id } = useParams();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const creatorName = queryParams.get('user');
  console.log('name is ' + creatorName)
  return (
    <div className="room">
      <h1>Welcome to the Room {id}</h1>
      <RoomCreationPopup userCreatorName={creatorName} roomCode={id} />
    </div>
  );
}

export default Room;