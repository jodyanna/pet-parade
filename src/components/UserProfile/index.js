import React, {useState, useEffect} from "react";
import styles from "./index.module.css";
import locationIcon from "./location.png";
import blankProfile from "./blank-profile.png";
import UserProfileForm from "../UserProfileForm";

export default function UserProfile({user, login}) {
  const [isFormVisible, setIsFormVisible] = useState(false);

  const handleClick = () => setIsFormVisible(!isFormVisible)

  useEffect(() => {

  }, []);

  return (
    <div className={styles.container}>
      
      <img src={blankProfile} alt="blank-profile.png" className={styles.profileImage} />

      <span className={styles.username}>{user.username}</span>

      {
        isFormVisible ? 
          <UserProfileForm user={user} login={login} handleClick={handleClick} />
          :
          <button onClick={handleClick} className={styles.button}>
            Edit profile
          </button>
      }

      {
        user.state !== null && user.city !== null &&
        <div className={styles.profileRow}>
          <img src={locationIcon} alt="location-icon.png" className={styles.icon} />
          <span>{user.city}, {user.state}</span>
        </div> 
      }

      <div className={styles.profileRow}>
        <span>Joined: {user.dateCreated}</span>
      </div>

    </div>
  )
}
