# homework

This is a fun sample project I created as part of an interview process. The codebase contains three nested projects:

1. A command line app that takes in three file types (csv, pipe-delimited, space-delimited), parses records, sorts the records based on various fields, and displays the result to the console.  

2. A common library containing the record specs, sorting functions, and date-time conversion helper functions.

3. A RESTful API that takes in records and sorts them according to name, birthdate, or gender.

## Libraries

### CLI

For the CLI I used clojure.tools.cli, which provides an simple, intuitive API for configuring a program quickly and easily.

### Record Parsing and Sorting

I used clojure.spec.alpha to validate my data and clojure.spec.gen.alpha to generate test data. Spec is awesome and when combined with bhb/expound, creates beautiful and powerful error messages.

If you try to pass an improperly formatted record, spec+expound will yell at you:

```
-- Spec failed --------------------

  {:last-name "Craig",
   :first-name "Bryson",
   :gender "female",
   :favorite-color "gray",
   :date-of-birth "2/31/1910"}
                  ^^^^^^^^^^^

Should be a valid date of format M/D/YYYY between 1900-2099

-------------------------
Detected 1 error
```
Thats not a real date, silly.

### RESTful API

reitit!!!

The folks over at Metosin are constantly blowing my mind with their amazing open-source Clojure libraries. Reitit is a super fast, simple, data-driven router (web framework too!).

I used metosin/reitit for routing, middleware, content-negotiation, and Swagger UI. Reitit allows you to choose ring-style middlewares, or pedestal interceptor model, and others.

I went for the ring flavor of reitit, which I found to be a pleasure to work with.

See the api/README.md for usage info.

## Testing and Code Coverage

To run tests
```
lein test
```

I use lein-cloverage plugin to generate code-coverage. Add it to .lein profile.clj.

```clojure_projects
[lein-cloverage "1.1.2"]
```

## Usage
```
lein cloverage
```

## License

Copyright © 2019 Jordan Calderwood
