import React, {useState} from "react";
import styles from "./index.module.css";
import cancelIcon from "./x-icon.png";
import Ratings from "react-ratings-declarative";

export default function PetRatingForm({user, pet, petImage, handleClick}) {
  const [rating, setRating] = useState();

  const changeRating = newRating => setRating(newRating)

  const handleSubmit = async e => {
    e.preventDefault();

    const newRating = await fetch(process.env.REACT_APP_API_URI + "/ratings", {
      method: "POST",
      body: JSON.stringify({
        rating: rating,
        petId: pet.id,
        userId: user.id
      }),
      headers: {
        "content-type": "application/json",
        "authorization": "Bearer " + user.token.jwt
      }
    }).then(res => res.json())
      .catch(error => console.log(error))

    user.ratings.push(newRating);
    user.stats.ratingsGivenCount += 1;
    pet.ratings.push(newRating);
    localStorage.setItem("pet-parade-user-info", JSON.stringify(user));

    handleClick();
  }

  return (
    <div className={styles.wrapper}>
      <form onSubmit={handleSubmit} className={styles.formContainer}>
        <div className={styles.buttonRow}>
          <button onClick={handleClick} className={styles.cancelButton}>
            <img src={cancelIcon} alt="x-icon" style={{width: "24px"}}/>
          </button>
        </div>

        <div className={styles.overview}>
          <img src={petImage} className={styles.image} alt="pet" />

          <div className={styles.info}>
            <h2 className={styles.name}>{pet.name}</h2>
            
            <span className={styles.bio}>
              {pet.bio}
            </span>

            <div className={styles.ratingContainer}>
              <Ratings 
                rating={rating} 
                changeRating={changeRating} 
                widgetRatedColors="#ffd700" 
                widgetDimensions="40px"
                widgetSpacings="8px"
              >
                <Ratings.Widget widgetHoverColor="#ffd700" />
                <Ratings.Widget widgetHoverColor="#ffd700" />
                <Ratings.Widget widgetHoverColor="#ffd700" />
                <Ratings.Widget widgetHoverColor="#ffd700" />
                <Ratings.Widget widgetHoverColor="#ffd700" />
              </Ratings>

              <input type="submit" value="Submit" className={styles.submitButton} />
            </div>
          </div>
        </div>

      </form>
    </div>
  )
}
