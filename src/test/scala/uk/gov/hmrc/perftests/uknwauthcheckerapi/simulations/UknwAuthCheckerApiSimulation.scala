/*
 * Copyright 2023 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.perftests.uknwauthcheckerapi.simulations

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.uknwauthcheckerapi.services.AuthService

class UknwAuthCheckerApiSimulation extends PerformanceTestRunner with AuthService with BaseSimulation {

  setup(
    "SingleEoriJourney",
    "Single EORI Auth request"
  ).withActions(getBearerToken)
    .withRequests(
      getHttpRequest(1)
    )

  setup(
    "100EoriJourney",
    "100 EORI Auth request"
  ).withActions(getBearerToken)
    .withRequests(
      getHttpRequest(100)
    )

  setup(
    "500EoriJourney",
    "500 EORI Auth request"
  ).withActions(getBearerToken)
    .withRequests(
      getHttpRequest(500)
    )

  setup(
    "1000EoriJourney",
    "1000 EORI Auth request"
  ).withActions(getBearerToken)
    .withRequests(
      getHttpRequest(1000)
    )

  setup(
    "3000EoriJourney",
    "3000 EORI Auth request"
  ).withActions(getBearerToken)
    .withRequests(
      getHttpRequest(3000)
    )

  runSimulation()
}
