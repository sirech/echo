#!/bin/bash

goal_build() {
  ./gradlew assemble
}

goal_containerize() {
  docker build . -t echo
}

goal_run() {
  ./gradlew bootRun
}

goal_outdated() {
  ./gradlew dependencyUpdates
}

goal_test-unit() {
  ./gradlew clean test
}

goal_test-container() {
  bundle install
  bundle exec rubocop
  bundle exec rspec spec
}

goal_help() {
  echo "usage: $0 <goal>

    goal:

    build                    -- Build the deployable artifacts
    containerize             -- Build the docker container for the app

    run                      -- Start the backend application

    outdated                 -- Check which dependencies are outdated

    test-unit                -- Run unit tests
    test-container           -- Run container tests
    "
  exit 1
}

main() {
  TARGET=${1:-}
  if [ -n "${TARGET}" ] && type -t "goal_$TARGET" &>/dev/null; then
    "goal_$TARGET" "${@:2}"
  else
    goal_help
  fi
}

main "$@"
