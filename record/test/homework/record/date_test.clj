(ns homework.record.date-test
  (:require [clojure.test         :refer :all]
            [homework.record.date :refer :all]
            [clojure.edn          :as    edn]))

(deftest valid-date-from-tuple🤓
  (is (= true (boolean (valid-date [2000 1  1]))))
  (is (= true (boolean (valid-date [1902 12 10]))))
  (is (= true (boolean (valid-date [1935 4  6]))))
  (is (= true (boolean (valid-date [2000 7  30])))))

(deftest invalid-date-from-tuple🤓
  (is (= false (boolean (valid-date ""))))
  (is (= false (boolean (valid-date nil))))
  (is (= false (boolean (valid-date 4))))
  ;Leap year!!!
  (is (= false (boolean (valid-date [1985 2 29]))))
  (is (= false (boolean (valid-date [2093 13 1])))))

(deftest dob-formatter🤓
  (is (= (type dob-formatter) org.joda.time.format.DateTimeFormatter)))

(deftest valid-tuple->dob🤓
  (is (= "1/1/1900" (tuple->dob [1900 1 1])))
  (is (= "2/3/1989" (tuple->dob [1989 2 3]))))

(deftest invalid-tuple->dob🤓
  (is (= false (boolean (tuple->dob [1989 2 34]))))
  (is (= false (boolean (tuple->dob nil))))
  (is (= false (boolean (tuple->dob "")))))

(deftest valid-dob->inst🤓
  (is (= org.joda.time.DateTime (type (dob->inst "1/1/1990"))))
  (is (= org.joda.time.DateTime (type (dob->inst "5/24/2020")))))

(deftest invalid-dob->inst🤓
  (is (= false (dob->inst "13/1/1990")))
  (is (= false (dob->inst "13/1/19900")))
  (is (= false (dob->inst "2/29/1990"))))
