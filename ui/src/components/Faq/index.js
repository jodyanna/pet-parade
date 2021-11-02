import React from "react";
import styles from "./index.module.css";

export default function Faq() {
  return (
    <section>
      <h1 className={styles.heading}>Frequently Asked Questions</h1>

      <div className={styles.faqItem}>
        <h3>What is Pet Parade?</h3>
        <p>
          Pet Parade is a social media platform based around pets and their owners. Users can make profile cards about
          thier pets for other users to rate and/or like. You can think of Pet Parade as a virtual 24/7 pet pagent where all
          pets are welcome and everyone can be a judge!
        </p>
      </div>

      <div className={styles.faqItem}>
        <h3>My pet has a bad rating, is my pet bad?</h3>
        <p>
          Not at all! The important thing is that YOU love your pet, friend.
        </p>
      </div>

      <div className={styles.faqItem}>
        <h3>Can I rate/like pets without an account?</h3>
        <p>
          No, all ratings and likes on pets are from registered users. If you do not have an account, you can register
          for free by navigating to the sign up page and filling out the simple form.
        </p>
      </div>

      <div className={styles.faqItem}>
        <h3>I can't rate a pet, what do I do?</h3>
        <p>
          There might be a couple of things happening here. First, an owner of a pet cannot rate/like thier own pets.
          Second, make sure you are locating the correct button to bring up the rating form. On a pet profile card,
          there is a button in the top right that will reveal a form to enter your rating, make sure to hit submit
          when you are done. Lastly, if you have already rated a pet, the aforementioned button will be replaced with
          the rating you have given the pet.
        </p>
      </div>

    </section>
  )
}