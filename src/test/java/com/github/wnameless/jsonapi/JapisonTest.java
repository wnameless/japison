/*
 *
 * Copyright 2015 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.github.wnameless.jsonapi;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JapisonTest {

  ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testWithJackson() throws Exception {
    ResourceDocument<Map<String, String>> req =
        new ResourceDocument<Map<String, String>>();

    req.setData(new ResourceObject<Map<String, String>>("map",
        new HashMap<String, String>()));
    req.getData().getAttributes().put("dd", "gg");

    String actual = mapper
        .writerWithType(new TypeReference<Document<Map<String, String>>>() {})
        .writeValueAsString(req);
    assertEquals("{\"data\":{\"type\":\"map\",\"attributes\":{\"dd\":\"gg\"}}}",
        actual);
  }

}
