(ns homework.api.server
  (:require [clojure.spec.alpha                :as s]
            [reitit.ring                       :as ring]
            [reitit.swagger                    :as swagger]
            [reitit.swagger-ui                 :as swagger-ui]
            [ring.adapter.jetty                :as jetty]
            [homework.api.core                 :as api]
            [homework.record.spec              :as recspec]
            [homework.record.parse             :as parse]
            [reitit.ring.middleware.muuntaja   :as muuntaja]
            [muuntaja.core                     :as m]))

(def routes
  [["/swagger.json"
     {:get {:no-doc true
            :swagger {:info {:title "my-api"}}
            :handler (swagger/create-swagger-handler)}}]

   ["/records"
    {:post {:handler api/create-record
            :middleware [api/wrap-parse-csv]}}]

   ["/records/gender"
      {:get {:handler api/records-by-gender}}]

   ["/records/birthdate"
      {:get {:handler api/records-by-birthdate}}]

   ["/records/name"
      {:get {:handler api/records-by-lastname}}]])

(def app
  (ring/ring-handler
    (ring/router routes
      {:data {:muuntaja m/instance
              :middleware [swagger/swagger-feature
                           muuntaja/format-negotiate-middleware
                           muuntaja/format-response-middleware]}})

    (ring/routes
         (swagger-ui/create-swagger-ui-handler
           {:path "/"
            :config {:validatorUrl nil
                     :operationsSorter "alpha"}})
         (ring/create-default-handler))))

(defn start []
  (jetty/run-jetty #'app {:port 3000, :join? false}))

(comment
  (def server (start))
  (.stop server)

  (def body-string "Kirkland | Abigail | female | pink | 1/2/1910")
  (parse/separator-type body-string)

  (app {:request-method :post
        :uri "/records"
        :body (java.io.ByteArrayInputStream. (.getBytes body-string))})

  (deref api/db))
