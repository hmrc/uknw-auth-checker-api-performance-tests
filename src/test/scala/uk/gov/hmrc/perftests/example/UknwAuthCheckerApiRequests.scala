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

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.netty.handler.codec.http.HttpResponseStatus
import uk.gov.hmrc.performance.conf.ServicesConfiguration

object UknwAuthCheckerApiRequests extends ServicesConfiguration {

  val baseUrl: String             = baseUrlFor("uknw-auth-checker-api")
  val route: String               = "/authorisations"
  private val bearerToken: String = if (runLocal) s"$${accessToken}" else s"Bearer $${accessToken}"

  val navigateToHomePage: HttpRequestBuilder =
    http("Navigate to Home Page")
      .get(s"$baseUrl$route/vat-return-period")
      .check(status.is(200))
      .check(css("input[name=csrfToken]", "value").saveAs("csrfToken"))

  val postAuthorisations: HttpRequestBuilder =
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(StringBody("""{
                         |  "date":"2024-02-08",
                         |  "authType": "UKNW",
                         |  "eoris" : ["GB000000000200"]
                         |}""".stripMargin))
      .headers(
        Map(
          HttpHeaderNames.Authorization -> bearerToken,
          HttpHeaderNames.Accept        -> "application/vnd.hmrc.1.0+json",
          HttpHeaderNames.ContentType   -> "application/json"
        )
      )
      .check(status.is(HttpResponseStatus.OK.code()))

}
