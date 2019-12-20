(ns homework.record.sort
  (:require [homework.record.date :as date]
            [clj-time.core        :as t]))

(defmulti sort-records (fn [db] (:sort-by db)))

(defmethod sort-records :gender
  [db]
  (let [records (:records db)]
    (sort-by (juxt :gender :last-name)
             #(compare %1 %2)
             records)))

(defmethod sort-records :last-name
  [db]
  (let [records (:records db)]
    (sort-by :last-name
             #(compare %2 %1)
             records)))

(defmethod sort-records :date-of-birth
  [db]
  (let [records (:records db)]
    (sort-by #(-> % :date-of-birth (date/dob->inst))
             (comparator #(t/before? %1 %2))
             records)))
