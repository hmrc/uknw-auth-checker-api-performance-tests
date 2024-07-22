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

package uk.gov.hmrc.perftests.uknwauthcheckerapi.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.netty.handler.codec.http.HttpResponseStatus
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.uknwauthcheckerapi.util.Helper

object UknwAuthCheckerApiRequests extends ServicesConfiguration {

  val baseUrl: String             = baseUrlFor("uknw-auth-checker-api")
  val route: String               = "/authorisations"
  private val acceptType          = "application/vnd.hmrc.1.0+json"
  private val mimeType            = "application/json"
  private val bearerToken: String = if (runLocal) s"$${accessToken}" else s"Bearer $${accessToken}"

  val postAuthorisation: HttpRequestBuilder =
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(StringBody(Helper.singleEoriJsonBody))
      .headers(
        Map(
          HttpHeaderNames.Authorization -> bearerToken,
          HttpHeaderNames.Accept        -> acceptType,
          HttpHeaderNames.ContentType   -> mimeType
        )
      )
      .check(status.is(HttpResponseStatus.OK.code()))

  val post100EoriAuthorisation: HttpRequestBuilder =
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(StringBody(Helper.hundredEoriJsonBody))
      .headers(
        Map(
          HttpHeaderNames.Authorization -> bearerToken,
          HttpHeaderNames.Accept        -> acceptType,
          HttpHeaderNames.ContentType   -> mimeType
        )
      )
      .check(status.is(HttpResponseStatus.OK.code()))

  val post500EoriAuthorisation: HttpRequestBuilder =
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(StringBody(Helper.fiveHundredEoriJsonBody))
      .headers(
        Map(
          HttpHeaderNames.Authorization -> bearerToken,
          HttpHeaderNames.Accept        -> acceptType,
          HttpHeaderNames.ContentType   -> mimeType
        )
      )
      .check(status.is(HttpResponseStatus.OK.code()))

  val post1000EoriAuthorisation: HttpRequestBuilder =
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(StringBody(Helper.thousandEoriJsonBody))
      .headers(
        Map(
          HttpHeaderNames.Authorization -> bearerToken,
          HttpHeaderNames.Accept        -> acceptType,
          HttpHeaderNames.ContentType   -> mimeType
        )
      )
      .check(status.is(HttpResponseStatus.OK.code()))

  val post3000EoriAuthorisation: HttpRequestBuilder =
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(StringBody(Helper.threeThousandEoriJsonBody))
      .headers(
        Map(
          HttpHeaderNames.Authorization -> bearerToken,
          HttpHeaderNames.Accept        -> acceptType,
          HttpHeaderNames.ContentType   -> mimeType
        )
      )
      .check(status.is(HttpResponseStatus.OK.code()))
}
