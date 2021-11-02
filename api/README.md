# Pet Parade API
The 24/7 virtual pet pageant's REST API

## Technologies
#### Source Code
- Java 1.8
- Spring Boot
- Hibernate
#### Database
- MariaDB
#### Security
- Spring Security
- JWT
#### Testing
- JUnit
- Mockito
#### Utility
- Lombok

## Installation
1. Download source code from the git repository using this command:

```
git clone https://github.com/jodyanna/pet-parade-api.git
```

2. Start up an instance of MariaDB and run all statements (in order, top to bottom) from this file: `src/resources/db-init.sql`

- Adjust the following default properties in `src/resources/application.properties` to suit your database configuration.
- The default port for the database is `3306`
- The default username is `root`
- The default password is `root`


3. In the root directory of the project, run this command to build and launch the application:

```
./mvnw spring-boot:run
```

4. Run this command to check the API is working:

```
curl localhost:8080
Hi! Welcome to the Pet Parade API.
```

## Features

### Index Controller

`/`

- GET: returns a welcome string: `Hi! Welcome to the Pet Parade API.`

### User Controller

`/users`

- GET: returns a list of all users
- PUT: updates a user, requires a UserDTO in request body

`/users/{id}`

- GET: returns a user matching given id
- DELETE: removes a user with given id

`/users/login`

- POST: returns user with matching email and password, requires a UserRequestDTO in request body

`/users/signup`

- POST: adds a new user, requires a UserDTO in request body

### Pet Controller

`/pets`

- GET: returns a list of all pets
- POST: adds a new pet, requires a PetDTO in request body
- PUT: updates a pet, requires a PetDTO in request body

`/pets/{id}`

- GET: returns a pet with matching id
- DELETE: removes a pet with matching id

`/pets/recent`

- GET: returns a list (length:2) of recently added pets

`/pets/flagged`

- GET: returns a list of all pets with "isFlagged" value marked as true

`/pets/species`

- POST: returns a list of all pets with matching species id, requires LeaderboardRequestDTO in request body

### Rating Controller

`/ratings`

- POST: adds a new rating, requires a RatingRequestDTO in request body

### Like Controller

`/likes`

- POST: adds a new like, requires a LikeRequestDTO in request body
- DELETE: removes an existing like, requires a LikeRequestDTO in request body

### Species Controller

`/species`

- GET: returns a list of all species

`/species/save`

- POST: adds a new species, requires a SpeciesDTO in request body

`/species/update`

- PUT: updates an existing species, requires a SpeciesDTO in request body