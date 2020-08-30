(ns homework.record.sort
  (:require [homework.record.date   :as date]
            [homework.record.db     :refer [db]]
            [clj-time.core          :as t]
            [camel-snake-kebab.core :as csk]))

(defn sort-records
  [criteria]
  (condp = (csk/->kebab-case criteria)
    :gender        (sort-by (juxt :gender :last-name) #(compare %1 %2) @db)
    :last-name     (sort-by :last-name #(compare %2 %1) @db)
    :date-of-birth (sort-by #(-> % :date-of-birth (date/dob->inst))
                            (comparator #(t/before? %1 %2))
                            @db)))

