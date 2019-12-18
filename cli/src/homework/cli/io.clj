(ns homework.cli.io
  (:require [clojure.java.io       :as io]
            [homework.record.parse :as parse]))

(defn file->records
  "Convert lines to list of maps (records)"
  [filepath]
  (with-open [rd (io/reader (io/file filepath))]
    (let [lines (into [] (line-seq rd))
          separator (parse/separator-type (first lines))]
      (->> lines
        (map #(parse/line->fields % separator))
        (map #(parse/fields->record %))))))

(comment
  (parse/validate-record {:first-name "ad" :last-name "as" :gender "female" :favorite-color "asd" :date-of-birth "1/13/2020"})
  (def files ["resources/test.csv" "resources/test1.csv" "resources/test2.csv"])
  (count (reduce concat (map file->records files)))
  (file->records "resources/test.csv"))
