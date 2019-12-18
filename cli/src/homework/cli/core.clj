(ns homework.cli.core
    (:gen-class)
    (:require [homework.record.sort :as     sort]
              [homework.cli.io      :as     io]
              [clojure.pprint       :as     pprint]
              [clojure.string       :as     str]
              [clojure.tools.cli    :refer  [parse-opts]]))

(def cli-options
  [["-s" "--sort " "Sort-by criteria"
    :default :gender
    :parse-fn #(keyword %)
    :validate [#(contains? #{:gender :last-name :date-of-birth} %) "Options are gender last-name date-of-birth"]]
   ["-h" "--help"]])

(defn usage [options-summary]
  (->> ["A simple cli to parse and sort records."
        ""
        "Usage: lein run file1 file2 file3 [options]"
        ""
        "Options:"
        options-summary
        ""]
       (str/join \newline)))

(defn error-msg [errors]
  (str "The following errors occurred while parsing your command:\n\n"
       (str/join \newline errors)))

(defn validate-args
  "Validate command line arguments. Either return a map indicating the program
  should exit (with a error message, and optional ok status), or a map
  indicating the action the program should take and the options provided."
  [args]
  (let [{:keys [arguments options errors summary]} (parse-opts args cli-options)]
    (cond
      (:help options) ; help => exit OK with usage summary
      {:exit-message (usage summary) :ok? true}
      errors ; errors => exit with description of errors
      {:exit-message (error-msg errors)}
      ;; custom validation on arguments
      (<= 1 (count arguments))
      {:arguments arguments :options options}
      :else ; failed custom validation => exit with usage summary
      {:exit-message (usage summary)})))

(defn exit [status msg]
  (println msg)
  (System/exit status))

(defn -main
  "Entrypoint to cli interface"
  [& args]
  (let [{:keys [arguments options exit-message ok?]} (validate-args args)
        records  (reduce concat (map io/file->records arguments))
        sort-key (:sort options)]
    (pprint/pprint arguments)
    (pprint/pprint options)
    (pprint/pprint exit-message)
    (if exit-message
      (exit (if ok? 0 1) exit-message))
    (pprint/print-table (sort/sort-records {:sort-by sort-key :records records}))))

(comment
  (-main "resources/test.csv" "resources/test1.csv" "resources/test2.csv" "-s" "date-of-birth")
  (-main "resources/test.csv" "resources/test1.csv" "resources/test2.csv" "-s" "gender")
  (-main "resources/test.csv" "resources/test1.csv" "resources/test2.csv" "-s" "last-name"))
