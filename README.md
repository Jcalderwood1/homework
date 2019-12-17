# homework

This is a fun sample project I created as part of an interview process. The codebase contains three nested projects:

1. A command line app that takes three file types (csv, pipe-delimited, space-delimited), parses records, sorts the records based on various fields, and displays the result to the console.  

2. A common library containing the record specs, sorting functions, and date-time conversion helper functions.

3. A RESTful API that takes in records and sorts them in various ways.

## Installation

I use lein-cloverage plugin to generate code-coverage. Add it to .lein profile.clj.

```clojure_projects
[lein-cloverage "1.1.2"]
```

## Usage

cd into cli, record, or api and run
```
lein cloverage
```
to generate code coverage for sources files in that sub-project.

cd into the cli directory and execute
```
lein run arg1 arg2 arg3
```
## Options

FIXME: listing of options this app accepts.

## License

Copyright © 2019 Jordan Calderwood

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
