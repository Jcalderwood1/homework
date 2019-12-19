(ns homework.api.core
    (:require [clojure.spec.alpha       :as s]
              [homework.record.parse    :as parse]
              [homework.record.spec     :as spec]
              [homework.record.sort     :as sort]
              [homework.record.data-gen :as gen]))

(def db (atom []))

(defn create-record [request]
  (let [record (-> request :data)]
    (if (s/valid? ::spec/record record)
      (do
        (swap! db conj record)
        {:status 201
         :body {:record record}})
      {:status 400
       :body {:message (s/explain-data ::spec/record record)}})))

(defn records-by-gender [request]
  {:status 200
   :headers {}
   :body (sort/sort-records {:sort-by :gender
                             :records @db})})

(defn records-by-birthdate [request]
  {:status 200
   :headers {}
   :body (sort/sort-records {:sort-by :date-of-birth
                             :records @db})})

(defn records-by-lastname [request]
  {:status 200
   :headers {}
   :body (sort/sort-records {:sort-by :last-name
                             :records @db})})

(defn wrap-parse-csv [handler]
  (fn [{:keys [body] :as request}]
    (let [body-string (slurp body :encoding "UTF-8")
          separator   (parse/separator-type body-string)
          fields      (parse/line->fields   body-string separator)
          record      (parse/fields->record fields)]
      (handler (assoc request :data record)))))

(comment
  (create-record {:body {:data "Kirkland | Abigail | female | pink | 1/2/1910"}})
  (reset! db (gen/generate-test-db))
  (reset! db '()))
