import React, {useState, useEffect} from "react";
import styles from "./index.module.css";
import Pet from "../Pet";

export default function Home({user}) {
  const [recentPets, setRecentPets] = useState([]);
  const [isFetchingRecent, setIsFetchingRecent] = useState(true);

  useEffect(() => {
    // Request recently created pets
    fetch("http://localhost:8080/pets/recent", {
      method: "GET",
      headers: {
        "content-type": "application/json"
      }
    }).then(res => res.json())
      .then(res => {
        setRecentPets(res);
        setIsFetchingRecent(false);
      })
      .catch(error => console.log(error))
  }, [])

  return (
    <div className={styles.container}>
      <div className={styles.greeting}>
        {
          user !== null && <h2 className={styles.userGreeting}>Welcome {user.username}</h2>
        }
      </div>

      <div className={styles.content}>
        <div className={styles.recentPets}>
          <h3 className={styles.recentPetsHeading}>Recently created pets, show them some love!</h3>
          {
            isFetchingRecent || recentPets.map(pet => <Pet key={pet.id} pet={pet} user={user} />)
          }
        </div>

        <section className={styles.announcements}>
          <h3 className={styles.announcementHeading}><i>Announcements</i></h3>
          <p>
            This is an example of a site wide announcement to display for all visitors. Maybe include some useful
            information about site changes or whatever.
          </p>
        </section>
      </div>


    </div>
  )
}
