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

package uk.gov.hmrc.perftests.example

import io.gatling.http.request.builder.HttpRequestBuilder
import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.BearerTokenGenerator.getBearerToken
import uk.gov.hmrc.perftests.example.UknwAuthCheckerApiRequests.postAuthorisations

class UknwAuthCheckerApiSimulation extends PerformanceTestRunner {

  private val authStep: Seq[HttpRequestBuilder] = if (runLocal) {
    Seq(getBearerToken)
  } else {
    Seq(
      //Need to implement to run in production
    )
  }

  private val apiStep: Seq[HttpRequestBuilder] = Seq(
    postAuthorisations
  )

  setup("AuthJourney", "Bearer Token Journey").withRequests(
    authStep: _*
  )

  setup("UknwAuthCheckerApiJourney", "Uknw Auth Checker Api Journey").withRequests(
    apiStep: _*
  )

  runSimulation()
}
