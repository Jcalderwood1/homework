(ns homework.cli.core
    (:gen-class)
    (:require [homework.record.sort :as sort]
              [homework.cli.io      :as io]
              [clojure.pprint       :as pprint]))

(def files ["resources/test.csv"])
(def records (reduce concat (map io/file->record files)))
(def db {:records (map io/validate-record records)
         :sort-by :last-name})

(defn -main
  "Entrypoint to cli interface"
  [& args]
  (pprint/print-table (sort/sort-records db)))
