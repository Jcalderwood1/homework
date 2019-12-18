## Usage

```clj
> lein repl
(start)
```

```
http POST localhost:3000/record "LastName | FirstName | Gender | FavoriteColor | DateOfBirth"
http POST localhost:3000/record "LastName, FirstName, Gender, FavoriteColor, DateOfBirth"
http POST localhost:3000/record "LastName FirstName Gender FavoriteColor DateOfBirth"

http GET  localhost:3000/records?sort=gender
http GET  localhost:3000/records?sort=gender
http GET  localhost:3000/records?sort=gender
```

## License

Copyright © 2019 Jordan Calderwood
