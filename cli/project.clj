(defproject homework "0.1.0-SNAPSHOT"
  :description "A simple CLI that takes in records and sorts them on various fields."
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/spec.alpha "0.2.176"]
                 [org.clojure/data.csv "0.1.4"]
                 [clj-time "0.15.2"]
                 [expound "0.8.2"]]

  :main ^:skip-aot homework.cli.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:source-paths ["dev" "src" "test" "../record/src"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                  [clj-time "0.15.2"]
                                  [org.clojure/test.check "0.10.0"]
                                  [org.clojure/spec.alpha "0.2.176"]
                                  [talltale "0.4.3"]
                                  [org.clojure/data.generators "0.1.2"]
                                  [proto-repl "0.3.1"]]}})
