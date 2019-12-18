(defproject homework.api "0.1.0-SNAPSHOT"
  :description "Reitit Ring App with Swagger"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [metosin/reitit "0.3.10"]]
  :repl-options {:init-ns homework.api.server})
