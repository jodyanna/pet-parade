import React, {useState} from "react";
import styles from "./index.module.css";

export default function SpeciesForm({user}) {
  const [speciesName, setSpeciesName] = useState("");

  const handleTextChange = e => setSpeciesName(e.target.value)

  const handleSubmit = e => {
    e.preventDefault();

    fetch(process.env.REACT_APP_API_URI + "/species/save", {
      method: "POST",
      body: JSON.stringify({
        id: null,
        name: speciesName.toUpperCase()
      }),
      headers: {
        "content-type": "application/json",
        "authorization": "Bearer " + user.token.jwt
      }
    }).then(res => res.json())
      .then(() => {
        setSpeciesName("");
        window.location.reload();
      })
      .catch(error => console.log(error))
  }

  return (
    <form onSubmit={handleSubmit} className={styles.form}>
      <h3 className={styles.heading}>Create new species</h3>
      <div className={styles.fieldContainer}>
        <label>New species:</label>
        <input type="text"
               value={speciesName}
               onChange={handleTextChange}
               className={styles.textInput}
        />
        <input type="submit" value="Submit" className={styles.submit}/>
      </div>
    </form>
  )
}