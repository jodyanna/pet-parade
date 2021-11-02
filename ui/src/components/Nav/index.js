import React, {useState} from "react";
import {Link, useHistory} from "react-router-dom";
import styles from "./index.module.css";
import stackIcon from "./stack-icon.png";
import cancelIcon from "./x-icon.png";

export default function Nav({ user, logout }) {
  const [isMenuVisible, setIsMenuVisible] = useState(false);
  let history = useHistory();

  const handleClick = () => setIsMenuVisible(!isMenuVisible);
  const handleLogoutClick = () => {
    logout();
    history.push("/")
  }
  const handleNavMenuLogoutClick = () => {
    logout();
    handleClick();
  }
  
  return(
    <div className={styles.container}>

      <nav className={styles.navBar}>
        <Link to="/" className={styles.navBarItem}>Home</Link>
        <Link to="/about" className={styles.navBarItem}>About</Link>
        <Link to="/leaderboard" className={styles.navBarItem}>Leaderboard</Link>
        {
          user === null ?
          <div>
            <Link to="/login" className={styles.navBarItem}>Log in</Link>
            <Link to="/signup" className={styles.navBarItem}>Sign up</Link>
          </div>
          :
          <div>
            <Link to="/user" className={styles.navBarItem}>My Profile</Link>
            {
              user.roles.includes("ROLE_ADMIN") &&
                <Link to="/admin" className={styles.navBarItem}>Admin</Link>
            }
            <button className={styles.logoutButton} onClick={handleLogoutClick}>Logout</button>
          </div>
        }
        
      </nav>

      <div className={styles.navMobile}>
        {
          isMenuVisible ?
          <nav className={styles.navMenu}>
            <div className={styles.menuCancelButtonContainer}>
              <button onClick={handleClick} className={styles.navButton}>
                <img src={cancelIcon} alt="x-icon" style={{width: "36px"}}/>
              </button>
            </div>
            <Link to="/" onClick={handleClick} className={styles.navMenuItem}>Home</Link>
            <Link to="/about" onClick={handleClick} className={styles.navMenuItem}>About</Link>
            <Link to="/leaderboard" onClick={handleClick} className={styles.navMenuItem}>Leaderboard</Link>
            {
              user === null ?
                <div>
                  <Link to="/login" onClick={handleClick} className={styles.navMenuItem}>Log in</Link>
                  <Link to="/signup" onClick={handleClick} className={styles.navMenuItem}>Sign up</Link>
                </div>
                :
                <div>
                  <Link to="/user" onClick={handleClick} className={styles.navMenuItem}>My Profile</Link>
                  <button className={styles.navMenuLogoutButton} onClick={handleNavMenuLogoutClick}>Logout</button>
                </div>
            }
          </nav>
          :
          <button onClick={handleClick} className={styles.navButton}>
            <img src={stackIcon} alt="stack-icon" style={{width: "36px"}}/>
          </button>
        }
      </div>

    </div>
  )
}