import React, {useState ,useEffect} from "react";
import styles from "./index.module.css";

export default function SpeciesSelectInput({species, setSpecies, hasAny, alignRight=false}) {
  const [allSpecies, setAllSpecies] = useState([]);

  useEffect(() => {
    fetch(process.env.REACT_APP_API_URI + "/species", {
      method: "GET",
      headers: {
        "content-type": "application/json"
      }
    }).then(res => res.json())
      .then(res => setAllSpecies(res))
      .catch(error => console.log(error));
  }, []);

  const handleSpeciesChange = e => setSpecies(e.target.value)

  return (
    <div className={alignRight ? styles.speciesSelectRight : styles.speciesSelect}>
      <select name="species"
              className={alignRight ? styles.selectInputRight : styles.selectInput}
              value={species}
              onChange={handleSpeciesChange}
      >
        <option value={hasAny ? 0 : ""}>{hasAny ? "ANY" : "SELECT"}</option>
        {
          allSpecies.map(species => <option key={species.id} value={species.id}>{species.name}</option>)
        }
      </select>
    </div>
  )
}