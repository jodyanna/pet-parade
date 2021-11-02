import React, {useState, useEffect} from "react";
import styles from "./index.module.css";
import Pet from "../Pet";

export default function ContentManager({user}) {
  const [pets, setPets] = useState([]);

  useEffect(() => {
    fetch(process.env.REACT_APP_API_URI + "/pets/flagged", {
      method: "GET",
      headers: {
        "content-type": "application/json",
        "authorization": "Bearer " + user.token.jwt
      }
    }).then(res => res.json())
      .then(res => setPets(res))
      .catch(error => console.log(error));

  }, [user.token.jwt]);

  return (
    <div className={styles.container}>
      {pets.map(pet => <Pet key={pet.id} user={user} pet={pet} />)}
    </div>
  )
}