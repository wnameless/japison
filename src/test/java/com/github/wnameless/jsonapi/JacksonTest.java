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
package com.github.wnameless.jsonapi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonTest {

  ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

  JapisonAnnotatedEntity typeAndId;
  JapisonAnnotatedEntity typeAndId2;

  @Before
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

    ResourceDocument<JapisonAnnotatedEntity> rd = mapper.readValue(
        "{\"data\":[{\"type\":\"jae\",\"id\":\"123\",\"attributes\":{\"id\":123,\"content\":\"ABC\"}},{\"type\":\"jae\",\"id\":\"456\",\"attributes\":{\"id\":456,\"content\":\"DEF\"}}]}",
        new TypeReference<ResourceDocument<JapisonAnnotatedEntity>>() {});

    ResourceObject<JapisonAnnotatedEntity> r1 =
        JsonApi.resourceDocument(typeAndId, typeAndId2).getData().get(0);
    ResourceObject<JapisonAnnotatedEntity> r2 = rd.getData().get(0);
    assertEquals(r1.getType(), r2.getType());

    assertEquals(r1.getAttributes().getId(), r2.getAttributes().getId());
    assertEquals(r1.getAttributes().getContent(),
        r2.getAttributes().getContent());
    assertEquals(r1.getAttributes(), r2.getAttributes());

    assertEquals(JsonApi.resourceDocument(typeAndId, typeAndId2).getData(),
        rd.getData());
    assertEquals(JsonApi.resourceDocument(typeAndId, typeAndId2), rd);

    rd = mapper.readValue(
        "{\"data\":{\"type\":\"jae\",\"id\":\"123\",\"attributes\":{\"id\":123,\"content\":\"ABC\"}}}",
        new TypeReference<ResourceDocument<JapisonAnnotatedEntity>>() {});

    assertEquals(
        JsonApi.resourceDocument(typeAndId).getData().get(0).getAttributes(),
        JsonApi.resourceDocument(typeAndId).getData().get(0).getAttributes());

    rd.getData().get(0).getId();
    rd.getData().get(0).getIncluded();
    rd.getData().get(0).getLinks();
    assertEquals(rd.getData().get(0).getAttributes(),
        rd.getData().get(0).getAttributes());

    assertEquals(
        JsonApi.resourceDocument(typeAndId).getData().get(0).getAttributes(),
        rd.getData().get(0).getAttributes());

    assertEquals(JsonApi.resourceDocument(typeAndId).getData().get(0),
        rd.getData().get(0));
    assertEquals(JsonApi.resourceDocument(typeAndId).getData(), rd.getData());

    assertEquals(JsonApi.resourceDocument(typeAndId), rd);
  }

}
