import React, {useState} from "react";
import Faq from "../Faq";
import styles from "./index.module.css";

export default function About() {
  const [isFaqVisible, setIsFaqVisible] = useState(true);
  const [isContactVisible, setIsContactVisible] = useState(false);

  const handleFaqClick = () => {
    setIsFaqVisible(true);
    setIsContactVisible(false);
  }

  const handleContactClick = () => {
    setIsContactVisible(true);
    setIsFaqVisible(false);
  }

  return (
    <div className={styles.container}>
      <nav className={styles.nav}>
        <button onClick={handleFaqClick} className={isFaqVisible ? styles.navItemSelected : styles.navItem}>
          FAQ
        </button>

        <button onClick={handleContactClick} className={isContactVisible ? styles.navItemSelected : styles.navItem}>
          Contact
        </button>
      </nav>

      <div>
        {isFaqVisible && <Faq />}
      </div>
      
    </div>
  )
}
