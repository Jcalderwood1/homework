(ns homework.cli.io
  (:require [clojure.data.csv     :as csv]
            [clojure.java.io      :as io]
            [clojure.string       :as str]
            [clojure.pprint       :as pprint]
            [clojure.spec.alpha   :as s]
            [homework.record.spec :as spec]
            [expound.alpha        :as expound]))

(defn parse-file
  "Parse file by <separator> and trim whitespace"
  [filepath separator]
  (with-open [rd (io/reader (io/file filepath))]
    (->> (line-seq rd)
         (map #(str/split % (re-pattern (str "\\" separator))))
         (map #(map str/trim %))
         (mapv vec))))

(defn csv-data->maps
  "Pretty sure Rich Hickey wrote this."
  [csv-data]
  (map zipmap
     (->> (first csv-data)
          (map keyword)
          repeat)
    (rest csv-data)))

(defn separator-type
  [filepath]
  "Scan just the first line (lazy seq) for separator type"
  (with-open [rdr (clojure.java.io/reader filepath)]
    (let [line (first (line-seq rdr))]
      (cond
        (str/includes? line "|") \|
        (str/includes? line ",")  \,
        :else                  \space))))

(defn file->record
  "Read the file, convert to list of maps (records)"
  [filepath]
  (let [separator (separator-type filepath)]
    (csv-data->maps (parse-file filepath separator))))

(defn validate-record [record]
  "Validate the record against the record spec and explain on failure"
  (if-not (s/valid? ::spec/record record)
          (do
            (binding [s/*explain-out* (expound/custom-printer {:show-valid-values? true :print-specs? false})]
              (s/explain ::spec/record record)))
          record))

(comment
  (validate-record {:first-name "ad" :last-name "as" :gender "female" :favorite-color "asd" :date-of-birth "13/13/2020"})
  (def files ["resources/test.csv" "resources/test1.csv" "resources/test2.csv"])
  (count (reduce concat (map file->record files)))
  (parse-file "resources/test.csv" (separator-type "resources/test.csv"))
  (file->record "resources/test.csv"))
