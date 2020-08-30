(defproject homework.api "0.1.0-SNAPSHOT"
  :description "Reitit Ring App with Swagger"
  :dependencies [[org.clojure/clojure        "1.10.0"]
                 [ring/ring-jetty-adapter    "1.8.0"]
                 [metosin/reitit             "0.5.5"]
                 [com.walmartlabs/lacinia    "0.37.0"]
                 [camel-snake-kebab          "0.4.1"]
                 [threatgrid/ring-graphql-ui "0.1.3"]]
  :repl-options {:init-ns homework.api.server}
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths   ["dev" "src" "test" "../record/src"]
                   :resource-paths ["resources"]
                   :dependencies   [[org.clojure/tools.namespace "0.2.11"]
                                    [org.clojure/test.check      "0.10.0"]
                                    [org.clojure/spec.alpha      "0.2.176"]
                                    [org.clojure/data.generators "0.1.2"]
                                    [org.clojure/data.csv        "0.1.4"]
                                    [talltale                    "0.4.3"]
                                    [clj-time                    "0.15.2"]]}})
                                  
