import React, {useState} from "react";
import { Redirect } from "react-router-dom";
import LoginForm from "../LoginForm";
import styles from "./index.module.css";

export default function Login({login}) {
  const [isRedirect, setIsRedirect] = useState(false);
  const [error, setError] = useState({
    isError: false,
    message: ""
  });

  const triggerRedirect = () => setIsRedirect(true);
  const handleError = error => setError(error);

  return (
    <div className={styles.container}>
      {isRedirect ? <Redirect to="/" /> : null}
      <LoginForm login={login} handleError={handleError} triggerRedirect={triggerRedirect} />
      {
        error.isError && <p className={styles.errorText}>{error.message}</p>
      }
    </div>
  )
}
