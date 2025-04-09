
# uknw-auth-checker-api-performance-tests

Performance test suite for the `uknw-auth-checker-api`, using [performance-test-runner](https://github.com/hmrc/performance-test-runner) under the hood.

## Pre-requisites

### Services

Start Mongo Docker container as follows:
(please use latest version as per MDTP best practices, this is just an example)

```bash
docker run --restart unless-stopped --name mongodb -p 27017:27017 -d percona/percona-server-mongodb:6.0 --replSet rs0
```

Start API, API Stub, and other dependant services such as AUTH

```bash
sm2 --start NOTIFICATION_OF_PRESENTATION_ALL
```

# Simulations

| Journey         | Description                                                                                           |
|-----------------|-------------------------------------------------------------------------------------------------------|
| 100EoriJourney  | A journey where 100 EORIs are checked for NOP Waiver authorisation using the `uknw-auth-checker-api`  |
| 500EoriJourney  | A journey where 500 EORIs are checked for NOP Waiver authorisation using the `uknw-auth-checker-api`  |
| 1000EoriJourney | A journey where 1000 EORIs are checked for NOP Waiver authorisation using the `uknw-auth-checker-api` |
| 3000EoriJourney | A journey where 3000 EORIs are checked for NOP Waiver authorisation using the `uknw-auth-checker-api` |

## Running the tests

### Testing Approach

This repository makes the use of a dynamic testing approach to follow that of the
[Stub](https://github.com/hmrc/uknw-auth-checker-api-stub)
and [performance tests](https://github.com/hmrc/uknw-auth-checker-api-performance-tests).
This works through having a [pre-determined set of authorised EORIs](src/test/scala/uk/gov/hmrc/perftests/uknwauthcheckerapi/util/Eoris.scala)
and a [custom EORI generator](src/test/scala/uk/gov/hmrc/perftests/uknwauthcheckerapi/util/generators/EoriGenerator.scala).

The EORI generator allows a chosen number of EORIs and a chosen number of valid EORIs (up to the number of EORIs in the
predetermined list) to be generated. The generator will output a sequence with the correct combination based on the
input.

### Local smoke test

Run smoke test (locally) as follows:

```bash
./run_smoke.sh
```

or by using sbt:

```bash
sbt -Dperftest.runSmokeTest=true Gatling/test
```

### Local performance test

Run full performance test (locally) as follows:

```bash
./run_tests.sh
```

or by using sbt:

```bash
sbt Gatling/test
```

### Staging smoke test

Run smoke test on staging as follows:

```bash
sbt -Dperftest.runSmokeTest=true -DrunLocal=false Gatling/test
```

#### WARNING:

Do **NOT** run a full performance test against staging from your local machine. Please and execute the `uknw-auth-checker-api-performance-tests` job from the dashboard in [Performance Jenkins](https://performance.tools.staging.tax.service.gov.uk/job/uknw-auth-checker-api-performance-tests/).

## Custom commands

### Pre-Commit

This is a sbt command alias specific to this project. It will run a scala format, run a scala fix

> `sbt preCommit`

### Format all

This is a sbt command alias specific to this project. It will run a scala format
check in the app, tests, and integration tests

> `sbt fmtAll`

### Fix

This sbt command will run the scala fix linter/reformatter in the project

> `sbt scalafixAll`

## Logging

The default log level for all HTTP requests is set to `WARN`. Configure [logback.xml](src/test/resources/logback.xml) to update this if required.

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
