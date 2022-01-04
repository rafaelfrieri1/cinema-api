# cinema-api

## Next steps to deploy

For this codebase to be a deployable code to production there is some stuff left to be done. First of them would be to hash the ids of the database so they cannot be seen cleanly through the endpoints for more security. Also it would be needed to implement an unhasher to access the values of the hashes through the different layers of the project. The second requirement would be to implement at least a basic authentication (preferably a user based authentication which would be better) system with an API user and an API key to access the endpoints of the API. Ideally but not necessarily, the third one would be having 100% coverage regarding unit testing. The documentation of the API is in the swagger.yaml file and the documentation regarding the paths is in the routes file in swagger2.0 format, another important thing for deployment would be getting the documentation from the routes file to the swagger.yaml file. It would also be nice to find an easier way to pass environment variables to Play! At last, it would also be nice to implement a linter to have readable and standard coding style through the codebase.

## Assumptions

Some initial assumptions were made to develop the API. First of them, as initially this is not a multiplex but a cinema, we only have one theater available thus the API does not allow to set 2 shows at the same time. The second is that we are assuming that the person that uses the API respects the duration of the movies to set new show times, then if we set one show time at 8:05 then it is technically possible to add another show time at 8:06. This is something additional that would be better for production, adding the verification of the new show times regarding the duration of the previous ones. We also assume that the only movies that will be presented are the fast and furious ones initially, which are in the database by default. As a second step, we could create the functionality to add new movies. We also assume that we want to preserve historical data for metrics and for other matters, therefore all deletes through endpoints are soft deletes. At last, we assume that the platform will have all the CRUD functionalities in the frontend regarding the show times, prices and ratings, so all of those endpoints are implemented.

## Installation

The project uses Scala version 2.13.7 and needs sbt to be run. Also, the database runs in a MySQL Docker container, so to run the project we will also need Docker.

First, to set up the key to access the OMDB API key, it is needed to access the `conf/application.conf` file in the project and change the value `"OMDB_API_KEY"` for the value of the key in a string.

Then to set up the database run the following command in the root folder of the project:

```bash
docker-compose up -d
```

And the database container should be running in the port 3306.

Then to run the project after setting up the database use the following command:

```bash
sbt run
```

Then, the project should be running in `http://localhost:9000`. Access the application homepage `http://localhost:9000` through the browser to run the evolutions and a welcoming message from the API should be shown. After that the application should be running successfully and the endpoints should be accessible.

To stop running the application use:

```bash
Ctrl/Cmd + D
```

To run the tests use the following command:

```bash
sbt test
```
