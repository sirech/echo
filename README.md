## Echo

[![CircleCI](https://circleci.com/gh/sirech/echo.svg?style=svg)](https://circleci.com/gh/sirech/echo)

This is an echo service that can be used as a test JSON Api

### Run

The `go` script provides all the targets required. Run it without arguments to get the documentation.

### Routes

#### Headers

under the `/headers` endpoint, it mirrors the headers that are passed to the request

### Client

There is a client CLI built in [Rust](https://www.rust-lang.org/). It can be installed by running `./go install-cli`. Then just running `recho` will give the information about the command.
