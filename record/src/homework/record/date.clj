(ns homework.record.date
  (:require [clj-time.core    :as time]
            [clj-time.format  :as f]))

(def dob-formatter (f/formatter "M/d/YYYY"))

(defn valid-date [date]
  (try (apply time/date-time date)
       (catch Exception _ false)))

(defn tuple->dob [date]
  (when-let [dob (valid-date date)]
    (f/unparse dob-formatter dob)))

(defn dob->inst [date]
  (try (f/parse dob-formatter date)
       (catch Exception _ false)))
