(ns cljpyoung.tasks.changelog
  (:require
   [clojure.tools.cli :as cli]
   [clojure.tools.reader.edn :as edn]
   [clojure.string :as string]
   [cljpyoung.tasks.changelog.gitchangelog :as gitchangelog]))

(def ^:private cli-options
  ;; ref: https://github.com/clojure/tools.cli
  [[nil "--config CONFIG" "fpath config"
    :default ".changelog.edn"
    :validate [#(.exists (clojure.java.io/as-file %))
               "config file does not exist"]
    :default-desc ".changelog.edn"]
   ["-o" "--output OUT" "fpath output"
    :default "CHANGELOG.md"
    :default-desc "CHANGELOG.md"]

   ;; opt
   ["-t" "--template TEMPLATE" "fpath template"
    :default "changelog.liquid"
    :validate [#(.exists (clojure.java.io/as-file %))
               "template file does not exist"]
    :default-desc "changelog.liquid"]
   [nil "--renderer RENDERER" "specify renderer"
    :default :liquid
    :validate [#(contains? #{:liquid :mustache} %)
               "renderer must be liquid or mustache"]
    :parse-fn keyword
    :default-desc "mustache | liquid"]
   [nil "--from FROM" "specify initial commit"
    :default "0000000000000000000000000000000000000000"
    :default-desc "zero commit"]
   [nil "--to TO" "specify to reference"
    :default "HEAD"
    :default-desc "HEAD"]

   ;;
   ["-v" "--version" "version"]
   ["-h" "--help" "show this. help"]])

(defn- usage
  [summary]
  (->> ["Usage: clj -m cljpyoung.tasks.changelog [options]"
        ""
        "Options:"
        summary]
       (string/join \newline)))

(defn -main
  [& args]
  (let [{:keys [options summary errors]}
        (cli/parse-opts args cli-options)]
    (cond
      (:help options)
      (println (usage summary))
      (:version options)
      (println "0.1.1")
      errors
      (println (string/join \newline errors))
      :else
      (let [config (try
                     (edn/read-string (slurp (:config options)))
                     (catch Exception e
                       {}))
            options (merge options config)
            output (:output options)]
        (println options)
        (->> [:from :to :template :renderer]
             (select-keys options)
             (gitchangelog/gitchangelog)
             (spit output))))))
