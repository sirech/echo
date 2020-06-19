#!/bin/bash

set -e
set -o nounset
set -o pipefail

SCRIPT_DIR=$(cd "$(dirname "$0")" ; pwd -P)

# shellcheck source=./go.helpers
source "${SCRIPT_DIR}/go.helpers"

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

goal_linter-kt() {
  ./gradlew detekt
}

goal_test-unit() {
  ./gradlew clean test
}

goal_test-container() {
  bundle install
  bundle exec rubocop
  bundle exec rspec spec
}

goal_test-e2e() {
  setup-newman
  run-newman
}

validate-args() {
  acceptable_args="$(declare -F | sed -n "s/declare -f goal_//p" | tr '\n' ' ')"

  if [[ -z $1 ]]; then
    echo "usage: $0 <goal>"
    # shellcheck disable=SC2059
    printf "\n$(declare -F | sed -n "s/declare -f goal_/ - /p")"
    exit 1
  fi

  if [[ ! " $acceptable_args " =~ .*\ $1\ .* ]]; then
    echo "Invalid argument: $1"
    # shellcheck disable=SC2059
    printf "\n$(declare -F | sed -n "s/declare -f goal_/ - /p")"
    exit 1
  fi
}

CMD=${1:-}
shift || true
if validate-args "${CMD}"; then
  "goal_${CMD}"
  exit 0
fi
