(defproject homework.api "0.1.0-SNAPSHOT"
  :description "Reitit Ring App with Swagger"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [ring/ring-jetty-adapter "1.8.0"]
                 [metosin/reitit "0.3.10"]]
  :repl-options {:init-ns homework.api.server}
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["dev" "src" "test" "../record/src"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [clj-time "0.15.2"]
                                  [org.clojure/test.check "0.10.0"]
                                  [org.clojure/spec.alpha "0.2.176"]
                                  [talltale "0.4.3"]
                                  [org.clojure/data.generators "0.1.2"]
                                  [proto-repl "0.3.1"]
                                  [org.clojure/data.csv "0.1.4"]]}})
                                  
