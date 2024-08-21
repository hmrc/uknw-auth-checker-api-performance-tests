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

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.netty.handler.codec.http.HttpResponseStatus
import play.api.http.MimeTypes
import play.api.libs.json.Json
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import uk.gov.hmrc.perftests.uknwauthcheckerapi.requests.AuthorisationRequest
import uk.gov.hmrc.perftests.uknwauthcheckerapi.util.generators.EoriGenerator

object BaseSimulation extends ServicesConfiguration with EoriGenerator {

  val baseUrl: String = baseUrlFor("uknw-auth-checker-api")
  val route:   String = "/authorisations"
  private val acceptType = "application/vnd.hmrc.1.0+json"
  private val bearerToken: String = s"$${accessToken}"

  def getHttpRequest(load: Int): HttpRequestBuilder = {
    val request: AuthorisationRequest = AuthorisationRequest(useEoriGenerator(load, Some(load)))
    val payload = StringBody(Json.toJsObject(request).toString())
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(payload)
      .headers(
        Map(
          HttpHeaderNames.Authorization -> bearerToken,
          HttpHeaderNames.Accept -> acceptType,
          HttpHeaderNames.ContentType -> MimeTypes.JSON
        )
      )
      .check(status.is(HttpResponseStatus.OK.code()))
  }
}
