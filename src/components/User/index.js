import React, {useState} from "react";
import styles from "./index.module.css";
import UserProfile from "../UserProfile";
import UserStats from "../UserStats";
import Pets from "../Pets";

export default function User({user, login}) {
  const [isStatsVisible, setIsStatsVisible] = useState(false);
  const [isPetsVisible, setIsPetsVisible] = useState(true);

  const handleStatsClick = () => {
    setIsPetsVisible(false);
    setIsStatsVisible(true);
  }
  const handlePetsClick = () => {
    setIsPetsVisible(true);
    setIsStatsVisible(false);
  }

  return (
    <div className={styles.container}>
      <UserProfile user={user} login={login} />

      <div className={styles.displayContainer}>
        <nav className={styles.userNav}>
          <button onClick={handlePetsClick} className={isPetsVisible ? styles.userNavItemSelected : styles.userNavItem}>
            My Pets
          </button>
          <button onClick={handleStatsClick} className={isStatsVisible ? styles.userNavItemSelected : styles.userNavItem}>
            My Stats
          </button>
        </nav>

        <div className={styles.display}>
          {isPetsVisible && <Pets user={user} login={login} />}
          {isStatsVisible && <UserStats stats={user.stats} />}
        </div>
      </div>
    </div>
  )
}