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

  const handleSubmit = async e => {
    e.preventDefault();

    // Validate form, exit if invalid
    const isValid = await validateForm();
    if (!isValid) return;

    const token = await fetchToken();

    const user = await fetchUser(token);

    if (user !== null) {
      user.token = token;
      login(user);

      triggerRedirect();
    }
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

  const fetchToken = async () => {
    try {
      const response = await fetch("/auth", {
        method: "POST",
        body: JSON.stringify({
          "username": email,
          "password": password
        }),
        headers: {
          "content-type": "application/json"
        }
      });

      if (!response.ok) {
        handleError({
          isError: true,
          message: "Login failed. Check email and/or password."
        });

        return null
      } else {
        return await response.json()
      }
    } catch(e) {
      console.error(e);

      return null
    }
  }

  const fetchUser = async token => {
    // Exit if token does not exist
    if (token === null) return null;

    try {
      const response = await fetch("/users/login", {
        method: "POST",
        body: JSON.stringify({
          "email": email,
          "password": password
        }),
        headers: {
          "content-type": "application/json",
          "authorization": "Bearer " + token.jwt
        }
      });

      if (response.ok) {
        return await response.json();
      } else {
        return null
      }
    } catch (e) {
      console.error(e);

      return null;
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