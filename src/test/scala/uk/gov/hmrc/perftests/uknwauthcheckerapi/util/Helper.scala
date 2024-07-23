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

package uk.gov.hmrc.perftests.uknwauthcheckerapi.util

import scala.io.Source

object Helper {

  def withFileAsString(fileName: String): String = {
    val source = Source.fromResource(fileName)
    try
      source.mkString
    finally
      source.close()
  }

  val singleEoriJsonBody:        String = withFileAsString("1Eori.json")
  val hundredEoriJsonBody:       String = withFileAsString("100Eori.json")
  val fiveHundredEoriJsonBody:   String = withFileAsString("500Eori.json")
  val thousandEoriJsonBody:      String = withFileAsString("1000Eori.json")
  val threeThousandEoriJsonBody: String = withFileAsString("3000Eori.json")
}
