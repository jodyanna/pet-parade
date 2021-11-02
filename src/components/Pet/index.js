import React, {useState} from "react";
import PetRatingForm from "../PetRatingForm";
import styles from "./index.module.css";
import blankProfile from "./blank-profile.png";
import heartEmpty from "./heart-empty.png";
import heartFilled from "./heart-filled.png";
import PetForm from "../PetForm";
import PetReportForm from "../PetReportForm";

export default function Pet({user, pet, login}) {
  const [isRatingFormVisible, setIsRatingFormVisible] = useState(false);
  const [isEditFormVisible, setIsEditFormVisible] = useState(false);
  const [isReportFormVisible, setIsReportFormVisible] = useState(false);
  const [isRefresh, setIsRefresh] = useState(false);

  const handleRateClick = () => setIsRatingFormVisible(!isRatingFormVisible)
  const handleEditClick = () => setIsEditFormVisible(!isEditFormVisible)
  const handleReportClick = () => setIsReportFormVisible(!isReportFormVisible)

  const handleLikeClick = async () => {
    const response = await fetch("http://localhost:8080/likes", {
      method: "POST",
      body: JSON.stringify({
        petId: pet.id,
        userId: user.id
      }),
      headers: {
        "content-type": "application/json",
        "authorization": "Bearer " + user.token.jwt
      }
    }).then(res => res.text())
      .catch(error => console.log(error));

    if (response === "Success") {
      user.likedPets.push(pet.id);
      pet.likingUsers.push(user.id);
      user.stats.likesGivenCount += 1;
      pet.stats.likes += 1;
      localStorage.setItem("pet-parade-user-info", JSON.stringify(user));
      setIsRefresh(!isRefresh); // force reload of component
    }
  }

  const handleRemoveLikeClick = async () => {
    const response = await fetch("http://localhost:8080/likes", {
      method: "DELETE",
      body: JSON.stringify({
        petId: pet.id,
        userId: user.id
      }),
      headers: {
        "content-type": "application/json",
        "authorization": "Bearer " + user.token.jwt
      }
    }).then(res => res.text())
      .catch(error => console.log(error));

    if (response === "Success") {
      user.likedPets = user.likedPets.filter(id => id !== pet.id);
      pet.likingUsers = pet.likingUsers.filter(id => id !== user.id);
      user.stats.likesGivenCount -= 1;
      pet.stats.likes -= 1;
      localStorage.setItem("pet-parade-user-info", JSON.stringify(user));
      setIsRefresh(!isRefresh); // force reload of component
    }
  }

  const renderLikeButton = () => {
    // user is not logged in
    if (user === null) return;

    // user is the owner of this pet
    if (user.id === pet.owner) return;

    // user has already likes this pet, return filled heart with no button
    let isLiked = false;
    for (const petId of user.likedPets) {
      if (petId === pet.id) {
        isLiked = true;
        break;
      }
    }
    if (isLiked) return (
      <button onClick={handleRemoveLikeClick} className={styles.buttonLike}>
        <img src={heartFilled} className={styles.icon} alt="like-heart.png" />
      </button>
    )

    // passed all gates, show the button
    return (
      <button onClick={handleLikeClick} className={styles.buttonLike}>
        <img src={heartEmpty} className={styles.icon} alt="like-heart.png" />
      </button>
    )
  }

  const renderRatingButton = () => {
    // user is not logged in
    if (user === null) return;

    // user is the owner of this pet
    if (user.id === pet.owner) return;

    // user has already rated this pet
    let isRated = false;
    for (const rating of user.ratings) {
      if (rating.ratedPet === pet.id) {
        isRated = true;
        break;
      }
    }
    if (isRated) return;

    // passed all gates, show the button
    return <button onClick={handleRateClick} className={styles.buttonRate}>⭐</button>
  }

  const renderEditButton = () => {
    // user is not logged in
    if (user === null) return;

    // user is an admin and can edit any pet
    if (user.roles.includes("ROLE_ADMIN")) return (
      <button onClick={handleEditClick} className={styles.buttonRate}>
        Edit
      </button>
    )

    // user is the owner of this pet
    if (user.id === pet.owner) return (
      <button onClick={handleEditClick} className={styles.buttonRate}>
        Edit
      </button>
    )
  }

  return (
    <div className={styles.container}>

      <div className={styles.imageContainer}>
        <img src={blankProfile} alt="blank-profile.png" className={styles.profileImage} />
        {renderLikeButton()}
      </div>
      
      <div className={styles.info}>
        <header className={styles.header}>
          <h2 className={styles.name}>{pet.name}</h2>
          <div className={styles.rateAndEditButtons}>
            {renderRatingButton()}
            {
              isRatingFormVisible &&
              <PetRatingForm user={user} pet={pet} petImage={blankProfile} handleClick={handleRateClick} />
            }
            {renderEditButton()}
            {
              isEditFormVisible &&
              <PetForm user={user}
                       login={login}
                       pet={pet}
                       handleClick={handleEditClick}
              />
            }
          </div>
        </header>
        
        <span className={styles.bio}>
          {pet.bio}
        </span>
        
        <div className={styles.statContainer}>
          {
            pet.birthday !== null && <span className={styles.stat}>🎂 {pet.birthday}</span>
          }
          <span className={styles.stat}>
            ❤️ {pet.stats.likes}
          </span>
          {pet.stats.rating !== null && <span className={styles.stat}>⭐ {pet.stats.rating}</span>}
        </div>

        <div className={styles.reportButtonRow}>
          <button onClick={handleReportClick} className={styles.reportButton}>Report</button>
          {
            isReportFormVisible && <PetReportForm user={user} pet={pet} handleClick={handleReportClick} />
          }
        </div>
      </div>

    </div>
  )
}