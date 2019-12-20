(ns homework.cli.io-test
  (:require [clojure.test             :refer :all]
            [homework.cli.io          :refer :all]
            [clojure.spec.alpha       :as    s]
            [homework.record.spec     :as    spec]
            [homework.record.data-gen :as    gen]))

(deftest file-exists?🤓
  (is (= true (boolean (file-exists? "resources/test.pizza"))))
  (is (= false  (boolean (file-exists? "resources/test.csv")))))

(deftest file->records🤓
  (is (= true  (s/valid? :homework.record.spec/db (file->records "resources/test.csv"))))
  (is (= false (s/valid? :homework.record.spec/db (file->records "resources/test-broken.csv")))))
