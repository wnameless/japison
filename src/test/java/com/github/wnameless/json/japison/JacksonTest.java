/*
 *
 * Copyright 2017 Wei-Ming Wu
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
package com.github.wnameless.json.japison;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.japison.jackson.JapisonFactory;
import com.google.gson.Gson;

public class JacksonTest {

  Gson gson = new Gson();
  ObjectMapper mapper = JapisonFactory.getObjectMapper();

  JapisonAnnotatedEntity typeAndId;
  JapisonAnnotatedEntity typeAndId2;

  @BeforeEach
  public void setup() {
    typeAndId = new JapisonAnnotatedEntity(123L, "ABC");
    typeAndId2 = new JapisonAnnotatedEntity(456L, "DEF");
  }

  @Test
  public void resourceDocumentSerailizationTest() {
    assertEquals(
        "{\"data\":{\"type\":\"jae\",\"id\":\"123\",\"attributes\":{\"id\":123,\"content\":\"ABC\"}}}",
        JsonApi.resourceDocument(typeAndId).toJson());
    assertEquals(
        "{\"data\":[{\"type\":\"jae\",\"id\":\"123\",\"attributes\":{\"id\":123,\"content\":\"ABC\"}},{\"type\":\"jae\",\"id\":\"456\",\"attributes\":{\"id\":456,\"content\":\"DEF\"}}]}",
        JsonApi.resourceDocument(typeAndId, typeAndId2).toJson());
  }

  @Test
  public void resourceDocumentDeserailizationTest() throws Exception {
    JapisonAnnotatedEntity jae = mapper.readValue(
        "{\"id\":123,\"content\":\"ABC\"}", JapisonAnnotatedEntity.class);
    assertEquals(typeAndId, jae);

    ResourceDocument<JapisonAnnotatedEntity> r =
        JsonApi.resourceDocument(typeAndId, typeAndId2);
    ResourceDocument<JapisonAnnotatedEntity> rd = mapper.readValue("{\"data\":["
        + "{\"type\":\"jae\",\"id\":\"123\",\"attributes\":{\"id\":123,\"content\":\"ABC\"}},"
        + "{\"type\":\"jae\",\"id\":\"456\",\"attributes\":{\"id\":456,\"content\":\"DEF\"}}"
        + "]}",
        new TypeReference<ResourceDocument<JapisonAnnotatedEntity>>() {});

    assertEquals(r.getData().get(0).getType(), rd.getData().get(0).getType());

    assertEquals(r.getData().toString(), rd.getData().toString());

    assertEquals(
        JsonApi.resourceDocument(typeAndId, typeAndId2).getData().toString(),
        rd.getData().toString());
    assertEquals(JsonApi.resourceDocument(typeAndId, typeAndId2).toString(),
        rd.toString());

    rd = mapper.readValue(
        "{\"data\":{\"type\":\"jae\",\"id\":\"123\",\"attributes\":{\"id\":123,\"content\":\"ABC\"}}}",
        new TypeReference<ResourceDocument<JapisonAnnotatedEntity>>() {});

    assertEquals(
        JsonApi.resourceDocument(typeAndId).getData().getSingular()
            .getAttributes(),
        JsonApi.resourceDocument(typeAndId).getData().getSingular()
            .getAttributes());

    assertEquals(rd.getData().getSingular().getAttributes(),
        rd.getData().getSingular().getAttributes());

    assertEquals(
        JsonApi.resourceDocument(typeAndId).getData().getSingular()
            .getAttributes().getId(),
        rd.getData().getSingular().getAttributes().getId());
    assertEquals(
        JsonApi.resourceDocument(typeAndId).getData().getSingular()
            .getAttributes().getContent(),
        rd.getData().getSingular().getAttributes().getContent());

    assertEquals(JsonApi.resourceDocument(typeAndId).getData().getSingular(),
        rd.getData().getSingular());
    assertEquals(JsonApi.resourceDocument(typeAndId).getData(), rd.getData());

    assertEquals(JsonApi.resourceDocument(typeAndId), rd);
  }

}
