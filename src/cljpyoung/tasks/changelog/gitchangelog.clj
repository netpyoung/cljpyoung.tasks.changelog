(ns cljpyoung.tasks.changelog.gitchangelog
  (:require
   [cljpyoung.tasks.changelog.liquid :as liquid])
  (:import
   [se.bjurr.gitchangelog.api GitChangelogApiConstants]
   [se.bjurr.gitchangelog.api GitChangelogApi]))

(defn gitchangelog
  [{:keys [from
           to
           template
           renderer]
    :or {renderer :default}}]
  (let [builder (-> (GitChangelogApi/gitChangelogApiBuilder)
                    (.withFromCommit from)
                    (.withToRef to)
                    (.withTemplatePath template))]
    (case renderer
      :default (.render builder)
      :mustache (.render builder)
      :liquid (liquid/liquid {:changelog (.getChangelog builder true)
                              :template-content (slurp template)
                              :extended-variables (.getExtendedVariables (.getSettings builder))})
      nil)))
