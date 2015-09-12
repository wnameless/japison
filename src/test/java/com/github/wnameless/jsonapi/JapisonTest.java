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
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class JapisonTest {

  ObjectMapper mapper = new ObjectMapper();

  JpaEntity<String> entity;

  @Before
  public void setUp() {
    entity = new JpaEntity<String>();
    entity.setData("hahaha");
  }

  @Test
  public void testWithJackson() throws Exception {
    ResourceDocument<JpaEntity<String>> req = JsonApi.resourceDocument(entity);

    String actual = mapper.writeValueAsString(req);
    assertEquals("{\"data\":{\"attributes\":{\"data\":\"hahaha\"}}}", actual);
  }

  private boolean toStringTestHelper(Object o) {
    String str = o.toString();
    if (!(str.contains(o.getClass().getSimpleName()))) {
      System.err.println(o.getClass().getSimpleName() + " not in the string");
      return false;
    }
    List<Field> fields = FieldUtils.getAllFieldsList(o.getClass());
    for (Field f : fields) {
      if (!(str.contains(f.getName()))) {
        System.err.println(f.getName() + " not in the string");
        return false;
      }
    }
    return true;
  }

  @Test
  public void testToString() {
    assertTrue(toStringTestHelper(JsonApi.error()));
    assertTrue(toStringTestHelper(JsonApi.errorsDocument()));
    assertTrue(toStringTestHelper(JsonApi.jsonApi()));
    assertTrue(toStringTestHelper(JsonApi.link()));
    assertTrue(toStringTestHelper(JsonApi.relationship(entity)));
    assertTrue(toStringTestHelper(JsonApi.resourceDocument(entity)));
    assertTrue(toStringTestHelper(JsonApi.resource(entity)));
    assertTrue(toStringTestHelper(JsonApi.resourcesDocument(entity)));
    assertTrue(toStringTestHelper(JsonApi.source()));
  }

  @Test
  public void equalsContract() {
    EqualsVerifier.forClass(Document.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ErrorObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ErrorsDocument.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(JsonApiObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(LinkObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(RelationshipObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ResourceDocument.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ResourceObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ResourcesDocument.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(SourceObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
  }

}
