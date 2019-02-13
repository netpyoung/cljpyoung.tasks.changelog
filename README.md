https://github.com/tomasbjerre/git-changelog-lib
https://github.com/spullara/mustache.java

https://github.com/bkiers/Liqp
https://shopify.github.io/liquid/

* context - https://github.com/tomasbjerre/git-changelog-lib#context


* changelog.edn
- https://github.com/edn-format/edn

1. `<current>/.changelog.edn`
2. `--config <config-fpath>`
  - ignore `<current>/.changelog.edn`
3. `--other-option` merge with
  - `<current>/.changelog.edn` or `--config <config-fpath>`



packing with
https://github.com/juxt/pack.alpha
https://juxt.pro/blog/posts/pack-maven.html


``` clojure
{:aliases
 {:changelog
  {:main-opts ["-m cljpyoung.task-changelog"]
   :extra-deps
   {cljpyoung.task-changelog
    {:git/url "https://github.com/netpyoung/cljpyoung.task-changelog.git"
     :sha "498baa963e914fd817dbf33ea251729efd0c8f95"}}}}}
```

# TODO
* support - extended-variables
* native image
* clojar
