import React, {useState} from "react";
import styles from "./index.module.css";
import errorIcon from "./error-icon.png";
import DatePicker from "react-date-picker";
import SpeciesSelectInput from "../SpeciesSelectInput";

export default function PetForm({user, login, pet, handleClick}) {
  const [petName, setPetName] = useState(pet === undefined ? "" : pet.name);
  const [bio, setBio] = useState(pet === undefined ? "" : pet.bio);
  const [birthday, setBirthday] = useState(pet === undefined ? null : pet.birthday);
  const [species, setSpecies] = useState(pet === undefined ? "" : pet.species);
  const [isFlagged, setIsFlagged] = useState(pet === undefined ? false : pet.isFlagged);
  const [errors, setErrors] = useState({
    petName: {
      message: "",
      isValid: true
    },
    species: {
      message: "",
      isValid: true
    },
    bio: {
      message: "",
      isValid: true
    }
  });

  const handlePetNameChange = e => setPetName(e.target.value)
  const handleBioChange = e => setBio(e.target.value)
  const handleIsFlaggedChange = () => setIsFlagged(!isFlagged)

  const handleSubmit = e => {
    e.preventDefault();

    validateForm()
      .then(isValid => {
        if (isValid) {
          const data = {
            id: pet === undefined ? null : pet.id,
            owner: determinePetOwner(),
            name: petName,
            bio: bio,
            birthday: birthday,
            species: parseInt(species),
            isFlagged: pet === undefined ? false : isFlagged
          }

          fetch(process.env.REACT_APP_API_URI + "/pets", {
            method: pet === undefined ? "POST" : "PUT",
            body: JSON.stringify(data),
            headers: {
              "content-type": "application/json",
              "authorization": "Bearer " + user.token.jwt
            }
          }).then(res => res.json())
            .then(res => {
              if (pet === undefined) {
                user.pets.push(res.id);
                user.stats.petCount += 1;
                login(user);
              }
              window.location.reload();
            })
            .catch(error => console.log(error));
        }
      })
  }

  const determinePetOwner = () => {
    // New pet so use current user id
    if (pet === undefined) return user.id;

    // Editing existing pet, don't use admin id if not the actual owner
    if (user.id === pet.owner) return user.id;
    else return pet.owner;
  }

  const validateForm = async () => {
    let errors = {
      petName: {},
      species: {},
      bio: {}
    };

    errors.petName = validateTextField(petName);
    errors.species = validateTextField(species);
    errors.bio = validateTextarea(bio);

    setErrors(errors);

    return errors.petName.isValid && errors.species.isValid && errors.bio.isValid
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
    else if (field.length > 25) {
      return {
        message: `This field cannot be over 25 characters.`,
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

  const validateTextarea = field => {
     if (field.length > 100) {
       return {
         message: "This field is over 100 character limit.",
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
    <div className={styles.wrapper}>
      <form onSubmit={handleSubmit} className={styles.formContainer}>
        <header className={styles.header}>
          <h2 className={styles.heading}>
            {pet === undefined ? "Create pet" : "Edit " + pet.name}
          </h2>
        </header>

        <div className={styles.textFieldContainer}>
          <label htmlFor="petName" className={styles.textInputLabel}>
            Name
            <p className={styles.errorText}>{errors.petName.message}</p>
          </label>
          <div className={styles.textInputContainer}>
            <input type="text"
                    name="petName"
                    value={petName}
                    className={errors.petName.isValid ? styles.textInput : styles.textInputError}
                    onChange={handlePetNameChange}
            />
            {
              !errors.petName.isValid &&
              <img src={errorIcon} alt="warning-icon" className={styles.errorIcon} />
            }
          </div>
        </div>

        <div className={styles.textFieldContainer}>
          <label htmlFor="bio" className={styles.textareaInputLabel}>
            Bio
            <p className={styles.errorText}>{errors.bio.message}</p>
          </label>
          <div className={styles.textareaInputContainer}>
            <textarea name="bio"
                      value={bio}
                      className={styles.textareaInput}
                      onChange={handleBioChange}
            />
          </div>
          <p className={styles.textareaState}>
            <span style={bio.length > 100 ? {color: "darkred"} : {}}>{bio.length}&nbsp;</span> / 100
          </p>
        </div>

        <div className={styles.fieldContainer}>
          <label className={styles.dateLabel}>Birthday</label>
          <DatePicker value={birthday} onChange={setBirthday} className={styles.datePicker} />
        </div>

        <div className={errors.species.isValid ? styles.fieldContainer : styles.fieldError}>
          <label className={styles.dateLabel}>Species</label>
          <SpeciesSelectInput species={species}
                              setSpecies={setSpecies}
                              hasAny={false}
                              alignRight={true}
          />
        </div>

        {
          user.roles.includes("ROLE_ADMIN") &&
            <div className={styles.fieldContainer}>
              <label className={styles.dateLabel}>Flagged</label>
              <input type="checkbox"
                     checked={isFlagged}
                     onChange={handleIsFlaggedChange}
                     className={styles.checkbox}
              />
            </div>
        }

        <div className={styles.buttonContainer}>
          <input type="submit" value="Submit" className={styles.button} />
          <button onClick={handleClick} className={styles.button}>
            Cancel
          </button>
        </div>

      </form>
    </div>
  )
}