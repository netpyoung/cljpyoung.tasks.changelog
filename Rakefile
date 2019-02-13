require 'json'
require 'optparse'
GITROOT = `git rev-parse --show-toplevel`.strip

task :default do
  sh "rake -T"
end

def get_version
  require 'edn'
  x = EDN::Reader.new(open("project.edn"))
  project = x.read
  return project[:version]
end

desc 'changelog'
task :changelog do
  sh "clojure -m cljpyoung.tasks.changelog --template sample/changelog.liquid --output CHANGELOG.md"
end

desc 'repl'
task :repl do
  sh "clojure -Acider-repl"
end

desc 'build'
task :build do
  sh "clojure -Auberjar"
end

desc 'check'
task :check do
  sh "clojure -Acheck"
end

desc 'test'
task :test do
  sh "clojure -Atest"
end

desc 'doc'
task :doc do
  sh "clojure -A:dev -m build doc"
  Dir.chdir('target/doc') do
    puts 'python -m SimpleHTTPServer 8080'
    `python -m SimpleHTTPServer 8080`
  end
end


desc 'release'
task :release do
  sh "clojure -Anative-image"
end


# ===========================
# git hook
# ===========================
desc "install githook"
task :githook_install do
  fpath_pre_commit = "#{GITROOT}/.git/hooks/pre-commit"
  File.open(fpath_pre_commit, 'w') do |f|
    f.puts(%{
#!/bin/sh
rake test
})
  end
  FileUtils.chmod(0777, fpath_pre_commit)

  fpath_pre_push = "#{GITROOT}/.git/hooks/pre-push"
  File.open(fpath_pre_push, 'w') do |f|
    f.puts(%{
#!/bin/sh
})
  end
  FileUtils.chmod(0777, fpath_pre_push)
end

desc "uninstall githook"
task :githook_uninstall do
  rm_f "#{GITROOT}/.git/hooks/pre-commit"
  rm_f "#{GITROOT}/.git/hooks/pre-push"
end
