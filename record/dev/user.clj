(ns user
  (:require [clojure.tools.namespace.repl :as tnr]
            [clojure.repl]
            [proto-repl.saved-values]
            [homework.record.sort :refer :all]
            [homework.record.spec :refer :all]
            [homework.record.date :refer :all]
            [clojure.pprint       :as pprint]))


(defn start
  []
  (println "Start completed"))

(defn reset
  []
  (tnr/refresh :after 'user/start))

(require '[clojure.spec.alpha      :as s]
         '[clojure.spec.gen.alpha  :as gen]
         '[clojure.spec.test.alpha :as test]
         '[clj-time.core           :as time]
         '[java-time               :as java-time]
         '[clj-time.format         :as f]
         '[talltale.core           :as tt]
         '[clojure.data.generators :as data]
         '[clojure.pprint          :as pprint])

(println "proto-repl dev/user.clj loaded")
