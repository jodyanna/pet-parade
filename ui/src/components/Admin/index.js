import React, {useState} from "react";
import styles from "./index.module.css";
import DbEditor from "../DbEditor";
import ContentManager from "../ContentManager";

export default function Admin({user}) {
  const [isDbEditorVisible, setIsDbEditorVisible] = useState(true);
  const [isContentManagerVisible, setIsContentManagerVisible] = useState(false);

  const handleDbEditorClick = () => {
    setIsDbEditorVisible(true);
    setIsContentManagerVisible(false);
  }

  const handleContentManagerClick = () => {
    setIsContentManagerVisible(true);
    setIsDbEditorVisible(false);
  }

  return (
    <div className={styles.container}>
      <nav className={styles.nav}>
        <button onClick={handleDbEditorClick} className={isDbEditorVisible ? styles.navItemSelected : styles.navItem}>
          Database Editor
        </button>

        <button onClick={handleContentManagerClick} className={isContentManagerVisible ? styles.navItemSelected : styles.navItem}>
          Content Manager
        </button>
      </nav>

      <div className={styles.display}>
        {
          isDbEditorVisible && <DbEditor user={user} />
        }
        {
          isContentManagerVisible && <ContentManager user={user} />
        }
      </div>

    </div>
  )
}