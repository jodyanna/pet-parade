import React, {useState} from "react";
import {Link} from "react-router-dom";
import styles from "./index.module.css";
import errorIcon from "./error-icon.png";

export default function LoginForm({login, handleError, triggerRedirect}) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState({
    email: {
      message: "",
      isValid: true
    },
    password: {
      message: "",
      isValid: true
    }
  });

  const handleEmailChange = e => setEmail(e.target.value)
  const handlePasswordChange = e => setPassword(e.target.value)

  const handleSubmit = e => {
    e.preventDefault();

    validateForm()
      .then(async isValid => {
        if (isValid) {
          const token = await fetch(process.env.REACT_APP_API_URI + "/auth", {
              method: "POST",
              body: JSON.stringify({
                "username": email,
                "password": password
              }),
              headers: {
                "content-type": "application/json"
              }
            })
            .then(res => res.json())
            .then(res => {
              if (res.status === 404 || res.status === 403) {
                handleError({
                  isError: true,
                  message: "Login failed. Check email and/or password."
                });
              }
              else {
                return res;
              }
            })
            .catch(() => handleError({isError: true, message: "Incorrect password."}));

          if (token !== undefined) {
            fetch(process.env.REACT_APP_API_URI + "/users/login", {
              method: "POST",
              body: JSON.stringify({
                "email": email,
                "password": password
              }),
              headers: {
                "content-type": "application/json",
                "authorization": "Bearer " + token.jwt
              }
            }).then(res => res.json())
              .then(res => {
                res.token = token;
                login(res);
                triggerRedirect();
              })
              .catch(error => console.log(error))
          }
        }
      })
  }

  const validateForm = async () => {
    let errors = {
      email: {},
      password: {}
    };

    errors.email = validateTextField(email);
    errors.password = validateTextField(password);

    setErrors(errors);

    return errors.email.isValid && errors.password.isValid
  }

  /**
   * Validate a single text input field.
   * @param {string} field
   * @return {{isValid: boolean, message: string}}
   */
  const validateTextField = field => {
    if (field.length < 1) {
      return {
        message: `This field cannot be empty.`,
        isValid: false
      }
    }
    else {
      return {
        message: "",
        isValid: true
      }
    }
  }

  return (
    <div className={styles.container}>
        <form onSubmit={handleSubmit} className={styles.formContainer}>
          <div className={styles.fieldContainer}>
            <label htmlFor="email" className={styles.textInputLabel}>
              Email
              <p className={styles.errorText}>{errors.email.message}</p>
            </label>
            <div className={styles.textInputContainer}>
              <input type="email"
                     name="email"
                     value={email}
                     onChange={handleEmailChange}
                     className={errors.email.isValid ? styles.textInput : styles.textInputError}
              />
              {
                !errors.email.isValid &&
                <img src={errorIcon} alt="warning-icon" className={styles.errorIcon} />
              }
            </div>
          </div>
          <div className={styles.fieldContainer}>
            <label htmlFor="password" className={styles.textInputLabel}>
              Password
              <p className={styles.errorText}>{errors.password.message}</p>
            </label>
            <div className={styles.textInputContainer}>
              <input type="password"
                     name="password"
                     value={password}
                     onChange={handlePasswordChange}
                     className={errors.password.isValid ? styles.textInput : styles.textInputError}
              />
              {
                !errors.email.isValid &&
                <img src={errorIcon} alt="warning-icon" className={styles.errorIcon} />
              }
            </div>
          </div>
          <input type="submit"
                 value="Log in"
                 className={styles.button}
          />
          <div className={styles.prompt}>
            Don't have an account? <Link to={"/signup"} className={styles.link}>Sign up</Link>
          </div>
        </form>
      </div>
  )
}