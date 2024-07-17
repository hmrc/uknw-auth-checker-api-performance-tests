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
import org.scalacheck.Gen
import uk.gov.hmrc.performance.conf.ServicesConfiguration
import wolfendale.scalacheck.regexp.RegexpGen

object UknwAuthCheckerApiRequests extends ServicesConfiguration {

  val baseUrl: String             = baseUrlFor("uknw-auth-checker-api")
  val route: String               = "/authorisations"
  private val bearerToken: String = if (runLocal) s"$${accessToken}" else s"Bearer $${accessToken}"
  val eoriGen: Gen[String]        = RegexpGen.from("^(GB|XI)[0-9]{12}|(GB|XI)[0-9]{15}$")
  val eorisGen: Gen[Seq[String]]  = Gen.chooseNum(1, 3000).flatMap(n => Gen.listOfN(n, "\"" + eoriGen.sample.get + "\""))

  val postAuthorisations: HttpRequestBuilder =
    http("Post Authorisations")
      .post(s"$baseUrl$route": String)
      .body(StringBody(s"""{
                         |  "date":"2024-02-08",
                         |  "authType": "UKNW",
                         |  "eoris" : [${eorisGen.sample.get.mkString(",")}]
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
