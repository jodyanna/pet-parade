import React from "react";
import styles from "./index.module.css";

export default function UserStats({stats}) {
  return (
    <div className={styles.container}>
      <div className={styles.row}>
        Pets<span className={styles.field}>{stats.petCount}</span>
      </div>

      <div className={styles.row}>
        Pet Likes Received<span className={styles.field}>{stats.petLikesCount}</span>
      </div>

      <div className={styles.row}>
        Pet Likes Given<span className={styles.field}>{stats.likesGivenCount}</span>
      </div>

      <div className={styles.row}>
        Ratings Given<span className={styles.field}>{stats.ratingsGivenCount}</span>
      </div>

      <div className={styles.row}>
        Ratings Recieved<span className={styles.field}>{stats.petRatingsCount}</span>
      </div>

    </div>
  )
}