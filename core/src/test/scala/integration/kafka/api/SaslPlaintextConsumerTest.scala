/**
  * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
  * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
  * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
  * License. You may obtain a copy of the License at
  *
  * http://www.apache.org/licenses/LICENSE-2.0
  *
  * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
  * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
  * specific language governing permissions and limitations under the License.
  */
package kafka.api

import kafka.security.JaasTestUtils
import org.apache.kafka.common.security.auth.SecurityProtocol
import org.junit.jupiter.api.{AfterEach, BeforeEach, TestInfo, Timeout}

@Timeout(600)
class SaslPlaintextConsumerTest extends BaseConsumerTest with SaslSetup {
  override protected def securityProtocol = SecurityProtocol.SASL_PLAINTEXT

  @BeforeEach
  override def setUp(testInfo: TestInfo): Unit = {
    startSasl(jaasSections(Seq("GSSAPI"), Some("GSSAPI"), JaasTestUtils.KAFKA_SERVER_CONTEXT_NAME))
    super.setUp(testInfo)
  }

  @AfterEach
  override def tearDown(): Unit = {
    super.tearDown()
    closeSasl()
  }

}
