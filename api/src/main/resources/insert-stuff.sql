
-- Fake species
INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('DOG');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('CAT');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('BIRD');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('FISH');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('GOAT');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('PIG');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('SHEEP');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('COW');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('DONKEY');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('HORSE');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('FERRET');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('RABBIT');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('RODENT');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('INSECT');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('REPTILE');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('FOX');

INSERT INTO `PetParade`.`Species`
(`name`)
VALUES ('OTHER');

-- Insert fake user data
-- Fake user id=1
INSERT INTO `PetParade`.`Users`
(`username`, `email`, `password`)
VALUES ('admin', 'admin@email.com', "password");

INSERT INTO `PetParade`.`Roles`
(`role`, `FK_Roles_Users`)
VALUES ('ROLE_USER', 1);

INSERT INTO `PetParade`.`Roles`
(`role`, `FK_Roles_Users`)
VALUES ('ROLE_ADMIN', 1);

INSERT INTO `PetParade`.`Pets`
(`name`, `bio`, `birthday`, `isFlagged`, `FK_Pets_Users`, `FK_Pets_Species`)
VALUES ('Gus', 'Itchy dog that loves scratching!', '2009-09-09', 0, 1, 1);

INSERT INTO `PetParade`.`Pets`
(`name`, `bio`, `birthday`, `isFlagged`, `FK_Pets_Users`, `FK_Pets_Species`)
VALUES ('Tag', 'Example dog bio would go here', '2004-04-04', 0, 1, 1);

-- Fake user id=2
INSERT INTO `PetParade`.`Users`
(`username`, `email`, `password`)
VALUES ('test', 'test@email.com', 'password');

INSERT INTO `PetParade`.`Roles`
(`role`, `FK_Roles_Users`)
VALUES ('ROLE_USER', 2);

INSERT INTO `PetParade`.`Pets`
(`name`, `bio`, `birthday`, `isFlagged`, `FK_Pets_Users`, `FK_Pets_Species`)
VALUES ('Cat', 'Meow meow meow meow meow', '2009-09-09', 0, 2, 2);

-- Fake user id=3
INSERT INTO `PetParade`.`Users`
(`username`, `email`, `password`)
VALUES ('test2', 'test2@email.com', 'password');

INSERT INTO `PetParade`.`Roles`
(`role`, `FK_Roles_Users`)
VALUES ('ROLE_USER', 3);

INSERT INTO `PetParade`.`Pets`
(`name`, `bio`, `birthday`, `isFlagged`, `FK_Pets_Users`, `FK_Pets_Species`)
VALUES ('Sebastian', 'I am actually a crab', '2009-09-09', 0, 3, 2);

-- Fake likes
INSERT INTO `PetParade`.`Likes`
(`FK_Likes_Users`, `FK_Likes_Pets`)
VALUES (1, 3);

INSERT INTO `PetParade`.`Likes`
(`FK_Likes_Users`, `FK_Likes_Pets`)
VALUES (3, 3);

INSERT INTO `PetParade`.`Likes`
(`FK_Likes_Users`, `FK_Likes_Pets`)
VALUES (2, 1);

INSERT INTO `PetParade`.`Likes`
(`FK_Likes_Users`, `FK_Likes_Pets`)
VALUES (2, 2);

-- Fake ratings
INSERT INTO `PetParade`.`Ratings`
(`rating`, `FK_Ratings_Users`, `FK_Ratings_Pets`)
VALUES (3, 1, 3);

INSERT INTO `PetParade`.`Ratings`
(`rating`, `FK_Ratings_Users`, `FK_Ratings_Pets`)
VALUES (4, 2, 1);

INSERT INTO `PetParade`.`Ratings`
(`rating`, `FK_Ratings_Users`, `FK_Ratings_Pets`)
VALUES (4, 2, 2);

INSERT INTO `PetParade`.`Ratings`
(`rating`, `FK_Ratings_Users`, `FK_Ratings_Pets`)
VALUES (4, 1, 3);
