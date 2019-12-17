(ns homework.record.sort-test
  (:require [clojure.test         :refer :all]
            [homework.record.sort :refer :all]
            [clojure.edn          :as    edn]))

(def resource-path "test/homework/record/resources/")

(deftest sort-records-gender🤓
  (let [test-data (edn/read-string (slurp (str resource-path "test-data.edn")))
        expected  (edn/read-string (slurp (str resource-path "by-gender.edn")))
        db {:records test-data :sort-by :gender}]
    (is (= expected (sort-records db)))))

(deftest sort-records-date-of-birth🤓
  (let [test-data (edn/read-string (slurp (str resource-path "test-data.edn")))
        expected  (edn/read-string (slurp (str resource-path "by-date-of-birth.edn")))
        db {:records test-data :sort-by :date-of-birth}]
    (is (= expected (sort-records db)))))

(deftest sort-records-last-name🤓
  (let [test-data (edn/read-string (slurp (str resource-path "test-data.edn")))
        expected  (edn/read-string (slurp (str resource-path "by-last-name.edn")))
        db {:records test-data :sort-by :last-name}]
    (is (= expected (sort-records db)))))
