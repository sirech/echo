#!/bin/bash

goal_build() {
  ./gradlew assemble
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

goal_help() {
  echo "usage: $0 <goal>

    goal:

    build                    -- Build the deployable artifacts
    run                      -- Start the backend application

    outdated                 -- Check which dependencies are outdated

    test-unit                -- Run unit tests
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
