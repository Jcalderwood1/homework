## Usage

```clj
> lein repl
(start)
```
You can also populate the db with sample data to test out sorting:
```clj
(test-db)
```
This will replace the db with the test data, so make sure to use this command before posting data you want to test.

## Swagger UI
Head over to localhost:3000 to play around with the server through the Swagger UI!

## Postman, cURL etc:
```
http POST localhost:3000/records "LastName | FirstName | Gender | FavoriteColor | DateOfBirth"
http POST localhost:3000/records "LastName, FirstName, Gender, FavoriteColor, DateOfBirth"
http POST localhost:3000/records "LastName FirstName Gender FavoriteColor DateOfBirth"

http GET  localhost:3000/records/gender
http GET  localhost:3000/records/birthdate
http GET  localhost:3000/records/name
```

## License

Copyright © 2019 Jordan Calderwood
