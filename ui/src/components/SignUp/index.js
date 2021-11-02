import React, {useState} from "react";
import { Redirect } from "react-router-dom";
import SignUpForm from "../SignUpForm";
import styles from "./index.module.css";

export default function SignUp({login}) {
  const [isRedirect, setIsRedirect] = useState(false);
  const [error, setError] = useState({
    isError: false,
    message: ""
  })

  const triggerRedirect = () => setIsRedirect(true);
  const handleError = error => setError(error)

  return (
    <div className={styles.container}>
      {isRedirect ? <Redirect to="/"/> : null}
      <SignUpForm login={login} triggerRedirect={triggerRedirect} handleError={handleError} />
      {
        error.isError && <p className={styles.errorText}>{error.message}</p>
      }
    </div>
  )
}