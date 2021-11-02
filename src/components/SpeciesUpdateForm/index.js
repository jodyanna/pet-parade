import React, {useState} from "react";
import styles from "./index.module.css";
import SpeciesSelectInput from "../SpeciesSelectInput";

export default function SpeciesUpdateForm({user}) {
  const [species, setSpecies] = useState("");
  const [newSpecies, setNewSpecies] = useState("");

  const handleTextChange = e => setNewSpecies(e.target.value)

  const handleSubmit = e => {
    e.preventDefault();

    fetch(process.env.REACT_APP_API_URI + "/species/update", {
      method: "PUT",
      body: JSON.stringify({
        id: parseInt(species),
        name: newSpecies.toUpperCase()
      }),
      headers: {
        "content-type": "application/json",
        "authorization": "Bearer " + user.token.jwt
      }
    }).then(res => res.json())
      .then(() => {
        setSpecies("");
        setNewSpecies("");
        window.location.reload();
      })
      .catch(error => console.log(error))
  }

  return (
    <form onSubmit={handleSubmit} className={styles.form}>
      <h3 className={styles.heading}>Edit existing species</h3>
      <div className={styles.fieldContainer}>
        <label>Change species name from</label>
        <SpeciesSelectInput species={species}
                            setSpecies={setSpecies}
                            hasAny={false}
        />
        to
        <input type="text"
               value={newSpecies}
               onChange={handleTextChange}
               className={styles.textInput}
        />
        <input type="submit" value="Submit" className={styles.submit}/>
      </div>
    </form>
  )
}