(ns homework.cli.data-gen
  (:require [clojure.data.csv        :as csv]
            [clojure.java.io         :as io]
            [clojure.spec.alpha      :as s]
            [clojure.spec.gen.alpha  :as gen]
            [homework.record.spec    :as spec]))

(defn generate-test-csv
  "Creates a .csv file of <quantity> example records delimited by <separator>"
  [filename separator quantity]
  (with-open [writer (io/writer (str "resources/" filename ".csv"))]
    (csv/write-csv writer
      [["last-name" "first-name" "gender" "favorite-color" "date-of-birth"]]
      :separator separator)
    (csv/write-csv writer
      (map vals (gen/sample (s/gen :homework.spec/person) quantity))
      :separator separator)))

(comment
  (do
    (generate-test-csv "test3"  \|     1000)
    (generate-test-csv "test4"  \,     1000)
    (generate-test-csv "test5"  \space 1000)))
