import React from "react";
import styles from "./index.module.css";
import logoIcon from "./pet-parade-logo-rev.png";
import Nav from "../Nav";
import { Link } from "react-router-dom";

export default function Header({ user, logout }) {
  return (
    <header className={styles.container}>
      <img src={logoIcon} alt="site-logo" className={styles.logo} />
      <Link to="/" className={styles.link}><h1 className={styles.heading}>Pet Parade</h1></Link>
      <Nav user={user} logout={logout} />
    </header>
  )
}
