(ns homework.record.db
  (:require [homework.record.data-gen :as gen]))

(defonce db (atom (gen/generate-test-db)))
(defn test-db [] (reset! db (gen/generate-test-db)))

(comment
  (create-record {:body {:data "Kirkland | Abigail | female | pink | 1/2/1910"}})
  (reset! db (gen/generate-test-db))
  (reset! db '()))
