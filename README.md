# What is this?


# Fast Track
* Modify deps.edn

    ``` clojure
    {:aliases
        {:changelog
            {:main-opts ["-m cljpyoung.task-changelog"]
             :extra-deps
             {cljpyoung.task-changelog
                 {:git/url "https://github.com/netpyoung/cljpyoung.task-changelog.git"
                 :sha "1fe30c3eb693ad04f032eda8e45c276394b5218d"}}}}}
    ```

* Run!

    ``` bash
    $ clojure -Achangelog
    ```

# Slow Track

``` clojure
$ clojure -Achangelog --help
Usage: clj -m cljpyoung.tasks.changelog [options]

Options:
      --config CONFIG      .changelog.edn     fpath config
  -o, --output OUT         CHANGELOG.md       fpath output
  -t, --template TEMPLATE  changelog.liquid   fpath template
      --renderer RENDERER  mustache | liquid  specify renderer
      --from FROM          zero commit        specify initial commit
      --to TO              HEAD               specify to reference
  -v, --version                               version
  -h, --help                                  show this. help
```

* For genreate changelog, this library uses [tomasbjerre/git-changelog-lib][tomasbjerre/git-changelog-lib]
* [tomasbjerre/git-changelog-lib][tomasbjerre/git-changelog-lib] uses [spullara/mustache.java][spullara/mustache.java] as theirs template engine.
* This library also provide a way to render which using [bkiers/Liqp][bkiers/Liqp] uses [liuquid syntax](https://shopify.github.io/liquid/).


## changelog.edn
- it's syntax using [edn](https://github.com/edn-format/edn)
1. At first, looking `<current>/.changelog.edn`.
2. If `--config <config-fpath>` is exists, ignore `<current>/.changelog.edn`.
3. If `--other-option` is provided, merge with `<current>/.changelog.edn` or `--config <config-fpath>`.


# TODO
* support - extended-variables
* native image
* clojar
  - https://github.com/juxt/pack.alpha
  - https://juxt.pro/blog/posts/pack-maven.html
* doc

[tomasbjerre/git-changelog-lib]: https://github.com/tomasbjerre/git-changelog-lib
[spullara/mustache.java]: https://github.com/spullara/mustache.java
[bkiers/Liqp]: https://github.com/bkiers/Liqp
[tomasbjerre/git-changelog-lib#context]: https://github.com/tomasbjerre/git-changelog-lib#context
