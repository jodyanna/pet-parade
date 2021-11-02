import React, {useState, useEffect} from "react";
import styles from "./index.module.css";
import Pet from "../Pet";
import PetForm from "../PetForm";

export default function Pets({user, login}) {
  const [pets, setPets] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [isFormVisible, setIsFormVisible] = useState(false);

  const handleClick = () => setIsFormVisible(!isFormVisible)

  useEffect(() => {
    const fetchAllPets = async ids => {
      const allPets = [];

      for (let i = 0; i < ids.length; i++) {
        const response = await fetch(process.env.REACT_APP_API_URI + "/pets/" + ids[i], {
          method: "GET",
          headers: {
            "content-type": "application/json",
            "authentication": "Bearer " + user.token.jwt
          }
        }).catch(error => console.log(error));

        const data = await response.json();

        allPets.push(data);
      }

      return Promise.all(allPets);
    }

    if (user.pets !== null) {
      fetchAllPets(user.pets)
        .then(res => {
          setPets(res);
          setIsLoading(false);
        })
        .catch(error => console.error(error));
    }
  }, [user.pets, user.token.jwt]);

  return (
    <div className={styles.container}>
      {
        !isLoading && pets.map(pet => <Pet key={pet.id} user={user} pet={pet} login={login} />)
      }

      <div>
        <button className={styles.button} onClick={handleClick}>
          Create new pet
        </button>
      </div>

      {
        isFormVisible && <PetForm user={user}
                                  login={login}
                                  handleClick={handleClick}
        />
      }
    </div>
  )
}
