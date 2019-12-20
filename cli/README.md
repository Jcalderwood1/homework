# homework CLI

The homework cli parses records from three file types, sorts them
according to a given field name, and prints them to the console.

The supported file types are:

1.	The pipe-delimited
```
LastName | FirstName | Gender | FavoriteColor | DateOfBirth
```
2.	The comma-delimited
```
LastName, FirstName, Gender, FavoriteColor, DateOfBirth
```
3.	The space-delimited
```
LastName FirstName Gender FavoriteColor DateOfBirth
```

The records can be sorted by passing in one of the following field names
after the -s or --sort flags:
```
last-name
gender
date-of-birth
```

## Usage

```
lein run file1 file2 file3 etc... -s last-name
```

## Options

For usage info run:
```
lein run -- -h
```
Options:
  -s, --sort   :gender  accepts one of [:gender :last-name :date-of-birth]
  -h, --help


## License

Copyright © 2019 Jordan Calderwood
