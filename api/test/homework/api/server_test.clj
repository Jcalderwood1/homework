(ns homework.api.server-test
  (:require [clojure.test             :refer :all]
            [homework.api.server      :refer :all]
            [clojure.edn              :as    edn]
            [homework.record.data-gen :as    gen]))

(def valid-post-record-request
   {:request-method :post
    :uri "/records"
    :body (java.io.ByteArrayInputStream. (.getBytes (gen/gen-data-line)))})

(def invalid-post-record-request
   {:request-method :post
    :uri "/records"
    :body (java.io.ByteArrayInputStream. (.getBytes "Im a bad string!"))})

(deftest invalid-route🤓
  (let [response (app {:request-method :post
                       :uri "/record"})
        {:keys [body headers status]} response]
    (is (= (type body)
           java.lang.String))
    (is (= (get headers {})))
    (is (= status 404))))

(deftest valid-post-route-test🤓
  (let [response (app valid-post-record-request)
        {:keys [body headers status]} response]
    (is (= (type body)
           java.io.ByteArrayInputStream))
    (is (= (get headers "Content-Type")
           "application/json; charset=utf-8"))
    (is (= status 201))))

(deftest invalid-post-route-test🤓
  (let [response (app invalid-post-record-request)
        {:keys [body headers status]} response]
    (is (= (type body)
           java.io.ByteArrayInputStream))
    (is (= (get headers "Content-Type")
           "application/json; charset=utf-8"))
    (is (= status 400))))

(deftest get-route-gender-test🤓
  (let [response (app {:request-method :get
                       :uri "/records/gender"})
        {:keys [body headers status]} response]
    (is (= (type body)
           java.io.ByteArrayInputStream))
    (is (= (get headers "Content-Type")
           "application/json; charset=utf-8"))
    (is (= status 200))))

(deftest get-route-name-test🤓
  (let [response (app {:request-method :get
                       :uri "/records/name"})
        {:keys [body headers status]} response]
    (is (= (type body)
           java.io.ByteArrayInputStream))
    (is (= (get headers "Content-Type")
           "application/json; charset=utf-8"))
    (is (= status 200))))

(deftest get-route-birthdate-test🤓
  (let [response (app {:request-method :get
                       :uri "/records/birthdate"})
        {:keys [body headers status]} response]
    (is (= (type body)
           java.io.ByteArrayInputStream))
    (is (= (get headers "Content-Type")
           "application/json; charset=utf-8"))
    (is (= status 200))))
