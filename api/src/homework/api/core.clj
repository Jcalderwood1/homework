(ns homework.api.core
  (:require [clojure.spec.alpha             :as    s]
            [clojure.edn                    :as    edn]
            [com.walmartlabs.lacinia.util   :refer [attach-resolvers]]
            [com.walmartlabs.lacinia        :refer [execute]]
            [com.walmartlabs.lacinia.schema :as    schema]
            [homework.record.parse          :as    parse]
            [homework.record.spec           :as    spec]
            [homework.record.sort           :as    sort]
            [homework.record.db             :refer [db]]
            [homework.record.data-gen       :as    gen]))

;; graphql
(def record-resolvers
  {:get-records (fn [ctx {:keys [sort_by]} val]
                  (sort/sort-records sort_by))})

(def record-schema
  (-> "schema.edn"
      clojure.java.io/resource
      slurp
      edn/read-string
      (attach-resolvers record-resolvers)
      (schema/compile
       {:default-field-resolver
        schema/hyphenating-default-field-resolver})))

(defn graphql [{:keys [data]}]
  {:status 200
   :body (execute record-schema data nil nil)})

;; REST api handlers
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
   :body (sort/sort-records :gender)})

(defn records-by-birthdate [request]
  {:status 200
   :headers {}
   :body (sort/sort-records :date-of-birth)})

(defn records-by-lastname [request]
  {:status 200
   :headers {}
   :body (sort/sort-records :last-name)})

(defn wrap-parse-csv [handler]
  (fn [{:keys [body] :as request}]
    (let [body-string (slurp body :encoding "UTF-8")
          separator   (parse/separator-type body-string)
          fields      (parse/line->fields   body-string separator)
          record      (parse/fields->record fields)]
      (handler (assoc request :data record)))))

(defn wrap-gql-string [handler]
  (fn [{:keys [body] :as request}]
    (handler (assoc request :data (slurp body :encoding "UTF-8")))))

(def test-db (reset! db (gen/generate-test-db)))
