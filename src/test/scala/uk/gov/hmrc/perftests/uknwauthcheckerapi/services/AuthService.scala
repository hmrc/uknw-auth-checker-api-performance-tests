/*
 * Copyright 2024 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.uknwauthcheckerapi.services

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder
import io.netty.handler.codec.http.HttpResponseStatus

import play.api.http.MimeTypes
import uk.gov.hmrc.performance.conf.ServicesConfiguration

trait AuthService extends ServicesConfiguration {

  private lazy val authLoginApiUrl: String = s"${baseUrlFor("auth-login-api")}/application/session/login"

  val authPayload: String =
    s"""
       |{
       |  "clientId": "753584d4-52c5-42fa-947c-d1523c1aa1a0",
       |  "authProvider": "StandardApplication",
       |  "applicationId":"05d1cc00-a3e2-480e-a7bc-1b7d4a47c674",
       |  "applicationName": "uknw-auth-checker-api",
       |  "enrolments": [],
       |  "ttl": 5000
       |}
     """.stripMargin

  def getBearerToken: HttpRequestBuilder =
    http("POST Auth Record")
      .post(authLoginApiUrl)
      .body(StringBody(authPayload))
      .headers(Map(HttpHeaderNames.ContentType -> MimeTypes.JSON))
      .check(header("Authorization").saveAs("accessToken"))
      .check(status.is(HttpResponseStatus.CREATED.code()))

}
