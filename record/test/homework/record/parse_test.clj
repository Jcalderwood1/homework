(ns homework.record.parse-test
  (:require [clojure.test             :refer :all]
            [homework.record.parse    :refer :all]
            [clojure.spec.alpha       :as    s]
            [homework.record.spec     :as    spec]
            [homework.record.data-gen :as    gen]))

(deftest line->fields🤓
  (let [data-line (gen/gen-data-line)]
    (is (= 5 (count (line->fields data-line \|))))))

(deftest fields->record🤓
  (let [fields (line->fields (gen/gen-data-line) \|)]
    (is (s/valid? :homework.record.spec/record (fields->record fields)))))

(deftest separator-type🤓
  (is (= \|     (separator-type "asdfasf|")))
  (is (= \,     (separator-type "asdfasdf,asdf,asdf")))
  (is (= \space (separator-type "asfda asfd asdf sadf "))))

(deftest validate-record🤓
  (is (= true (boolean (validate-record {:last-name "Stevenson"
                                         :first-name "Jordan"
                                         :gender "female"
                                         :favorite-color "maroon"
                                         :date-of-birth "9/2/1910"})))))
