(ns homework.record.parse
  (:require [clojure.java.io       :as io]
            [clojure.string        :as str]
            [clojure.pprint        :as pprint]
            [clojure.spec.alpha    :as s]
            [homework.record.spec  :as spec]
            [expound.alpha         :as expound]))

(defn line->fields
  "Split line by <separator> into fields, trim whitespace from each field."
  [line separator]
  (vec (map str/trim (str/split line (re-pattern (str "\\" separator))))))

(defn fields->record
  "Zip the field values with their associated keys into a map"
  [fields]
  (zipmap [:last-name :first-name :gender :favorite-color :date-of-birth] fields))

(defn separator-type
  "Scan the line for a separator and return the first one you see."
  [line]
  (cond
    (str/includes? line "|")  \|
    (str/includes? line ",")  \,
    :else                     \space))

(defn validate-record [record]
  "Validate the record against the record spec and explain on failure"
  (if-not (s/valid? ::spec/record record)
          (do
            (binding [s/*explain-out* (expound/custom-printer {:show-valid-values? true :print-specs? false})]
              (s/explain ::spec/record record)))
          record))
