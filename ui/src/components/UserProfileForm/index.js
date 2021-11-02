import React, {useState} from "react";
import styles from "./index.module.css";
import errorIcon from "./error-icon.png";

export default function UserProfileForm({user, login, handleClick}) {
  const [username, setUsername] = useState(user.username);
  const [city, setCity] = useState(user.city === null ? "" : user.city);
  const [state, setState] = useState(user.state === null ? "" : user.state);
  const [errors, setErrors] = useState({
    username: {
      message: "",
      isValid: true
    }
  });

  const handleUsernameChange = e => setUsername(e.target.value)
  const handleCityChange = e => setCity(e.target.value)
  const handleStateChange = e => setState(e.target.value)

  const handleSubmit = e => {
    e.preventDefault();

    validateForm()
      .then(isValid => {
        if (isValid) {
          user.username = username.length > 0 ? username : null;
          user.city = city.length > 0 ? city : null;
          user.state = state.length > 0 ? state : null;

          fetch(process.env.REACT_APP_API_URI + "/users", {
            method: "PUT",
            body: JSON.stringify(user),
            headers: {
              "content-type": "application/json",
              "authorization": "Bearer " + user.token.jwt
            }
          }).then(res => res.json())
            .then(res => {
              res.token = user.token
              login(res);
              handleClick();
            })
            .catch(error => console.log(error));
        }
      })
  }

  const validateForm = async () => {
    let errors = {
      username: {}
    }

    errors.username = validateTextField(username);

    setErrors(errors);

    return errors.username.isValid
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
    <form onSubmit={handleSubmit} className={styles.formContainer}>

      <div className={styles.fieldContainer}>
        <label htmlFor="username" className={styles.textInputLabel}>
          Username
          <p className={styles.errorText}>{errors.username.message}</p>
        </label>
        <div className={styles.textInputContainer}>
          <input type="text"
                  name="username"
                  value={username}
                  className={errors.username.isValid ? styles.textInput : styles.textInputError}
                  onChange={handleUsernameChange}
          />
          {
            !errors.username.isValid &&
            <img src={errorIcon} alt="warning-icon" className={styles.errorIcon} />
          }
        </div>
      </div>

      <div className={styles.fieldContainer}>
        <label htmlFor="username" className={styles.textInputLabel}>
          City
        </label>
        <div className={styles.textInputContainer}>
          <input type="text"
                  name="city"
                  value={city}
                  className={styles.textInput}
                  onChange={handleCityChange}
          />
        </div>
      </div>

      <div className={styles.fieldContainer}>
        <label htmlFor="state" className={styles.textInputLabel}>
          State
        </label>
        <div className={styles.textInputContainer}>
          <input type="text"
                  name="state"
                  value={state}
                  className={styles.textInput}
                  onChange={handleStateChange}
          />
        </div>
      </div>

      <input type="submit"
              value="Save"
              className={styles.button}
      />

      <button onClick={handleClick} className={styles.button}>
        Cancel
      </button>

    </form>
  )
}
