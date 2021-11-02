import React from "react";
import styles from "./index.module.css";
import SpeciesForm from "../SpeciesForm";
import SpeciesUpdateForm from "../SpeciesUpdateForm";

export default function DbEditor({user}) {
  return (
    <div className={styles.container}>
      <SpeciesForm user={user} />
      <SpeciesUpdateForm user={user} />
    </div>
  )
}