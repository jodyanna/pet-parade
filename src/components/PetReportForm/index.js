import React from "react";
import styles from "./index.module.css";
import cancelIcon from "../PetRatingForm/x-icon.png";

export default function PetReportForm({user, pet, handleClick}) {
  const handleSubmit = e => {
    e.preventDefault();

    // Don't do anything if pet is already flagged true
    if (!pet.isFlagged) {
      pet.isFlagged = true;
      fetch(process.env.REACT_APP_API_URI + "/pets", {
        method: "PUT",
        body: JSON.stringify(pet),
        headers: {
          "content-type": "application/json",
          "authorization": "Bearer " + user.token.jwt
        }
      }).then(() => {
        // update pet in state
      }).catch(error => console.log(error));
    }

    handleClick();
  }

  return (
    <div className={styles.wrapper}>
      <form onSubmit={handleSubmit} className={styles.form}>
        <div className={styles.heading}>
          <button onClick={handleClick} className={styles.cancelButton}>
            <img src={cancelIcon} alt="x-icon" style={{width: "24px"}}/>
          </button>
        </div>

        <div className={styles.content}>
          <h3>Does this pet contain prohibited content? </h3>
          <p>
            Help us keep Pet Parade safe and fun by reporting this content
            to our admins for review.
          </p>
        </div>

        <div className={styles.buttonRow}>
          <input type="submit" value="Yes" className={styles.button} />

          <button className={styles.button} onClick={handleClick}>
            No
          </button>
        </div>

      </form>
    </div>
  )
}