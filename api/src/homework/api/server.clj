(ns homework.api.server
  (:require [reitit.coercion.spec]
            [reitit.ring                       :as ring]
            [reitit.swagger                    :as swagger]
            [reitit.swagger-ui                 :as swagger-ui]
            [reitit.ring.coercion              :as coercion]
            [reitit.dev.pretty                 :as pretty]
            [reitit.ring.middleware.muuntaja   :as muuntaja]
            [reitit.ring.middleware.exception  :as exception]
            [reitit.ring.middleware.multipart  :as multipart]
            [reitit.ring.middleware.parameters :as parameters]
            [reitit.ring.middleware.dev        :as dev]
            [reitit.ring.spec                  :as spec]
            [ring.adapter.jetty                :as jetty]
            [muuntaja.core                     :as m]
            [clojure.spec.alpha                :as s]
            [clojure.java.io                   :as io]))

(s/def ::x string?)
(s/def ::y int?)
(s/def ::total int?)
(s/def ::record-request ::x)
(s/def ::fn  string?)
(s/def ::ln  string?)
(s/def ::g   string?)
(s/def ::fc  string?)
(s/def ::dob string?)
(s/def ::record (s/keys :req-un [::fn ::ln ::g ::fc ::dob]))
(s/def ::record-response (s/keys :req-un [::record]))

(defn my-handler [request]
  {:status 201
   :body {:record {:fn "fn" :ln "ln" :g "g" :fc "fc" :dob "dob"}}})

(def app
  (ring/ring-handler
    (ring/router
      [["/swagger.json"
        {:get {:no-doc true
               :swagger {:info {:title "my-api"}}
               :handler (swagger/create-swagger-handler)}}]

       ["/api"
        {:swagger {:tags ["records"]}}

        ["/records"
          {:post {:summary "Post a single record"
                  :parameters {:body ::record-request}
                  :responses {201 {:body ::record-response}}
                  :handler my-handler}}]]]

      {;:reitit.middleware/transform dev/print-request-diffs ;; pretty diffs
       :exception pretty/exception
       :data {:coercion reitit.coercion.spec/coercion
              :muuntaja m/instance
              :middleware [;; swagger feature
                           swagger/swagger-feature
                           ;; query-params & form-params
                           parameters/parameters-middleware
                           ;; content-negotiation
                           muuntaja/format-negotiate-middleware
                           ;; encoding response body
                           muuntaja/format-response-middleware
                           ;; exception handling
                           exception/exception-middleware
                           ;; decoding request body
                           muuntaja/format-request-middleware
                           ;; coercing response bodys
                           coercion/coerce-response-middleware
                           ;; coercing request parameters
                           coercion/coerce-request-middleware
                           ;; multipart
                           multipart/multipart-middleware]}})
    (ring/routes
      (swagger-ui/create-swagger-ui-handler
        {:path "/"
         :config {:validatorUrl nil
                  :operationsSorter "alpha"}})
      (ring/create-default-handler))))

(defonce server (jetty/run-jetty #'app {:port 3000 :join? false}))

(defn start []
  (.start server)
  (println "server running in port 3000"))

(defn stop []
  (.stop server)
  (println "stopping server on port 3000"))

(comment
  (start)
  (stop))
