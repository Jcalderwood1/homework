(ns homework.cli.io
  (:require [clojure.java.io       :as io]
            [homework.record.parse :as parse]))

(defn file-exists?
  [file]
  (let [f (io/file file)]
    (when-not (and (.exists f) (not (.isDirectory f)))
      (str file " does not exist or is not a valid file."))))

(defn invalid-record-abort [record]
  (if (some nil? record)
    (print "Error while parsing. Invalid record. Aborting...")
    (System/exit 1)))

(defn file->records
  "Convert lines to list of maps (records)"
  [filepath]
  (with-open [rd (io/reader (io/file filepath))]
    (let [lines (into [] (line-seq rd))
          separator (parse/separator-type (first lines))]
      (->> lines
        (map #(parse/line->fields % separator))
        (map #(parse/fields->record %))
        (map parse/validate-record)))))

(comment
  (def files ["resources/test.csv" "resources/test1.csv" "resources/test2.csv"])
  (count (reduce concat (map file->records files)))
  (file->records "resources/test.csv")
  (file-exists? "resources/test.csvd"))
