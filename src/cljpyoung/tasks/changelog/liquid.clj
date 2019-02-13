(ns cljpyoung.tasks.changelog.liquid
  (:import [liqp Template]))

(defn liquid [{:keys [template-content changelog extended-variables]}]
  (let [template (Template/parse template-content)]
    (.render template
             true
             "changelog"
             changelog
             (into-array Object []))))
