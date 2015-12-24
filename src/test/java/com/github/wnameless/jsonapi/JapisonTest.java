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

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;

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
    ResourceDocument<JpaEntity<String>> req =
        JsonApi.resourceDocument(entity, "entities");

    String actual = mapper.writeValueAsString(req);
    assertEquals(
        "{\"data\":{\"type\":\"entities\",\"attributes\":{\"data\":\"hahaha\"}}}",
        actual);
  }

  @Test
  public void testBeans() {
    BeanTester beanTester = new BeanTester();
    Configuration config1 =
        new ConfigurationBuilder().ignoreProperty("data").build();
    beanTester.addCustomConfiguration(ErrorsDocument.class, config1);
    Configuration config2 =
        new ConfigurationBuilder().ignoreProperty("errors").build();
    beanTester.addCustomConfiguration(ResourceDocument.class, config2);
    beanTester.addCustomConfiguration(ResourcesDocument.class, config2);
    beanTester.testBean(ErrorObject.class);
    beanTester.testBean(ErrorsDocument.class);
    beanTester.testBean(JsonApiObject.class);
    beanTester.testBean(LinkObject.class);
    beanTester.testBean(RelationshipObject.class);
    beanTester.testBean(ResourceDocument.class);
    beanTester.testBean(ResourceObject.class);
    beanTester.testBean(ResourcesDocument.class);
    beanTester.testBean(SourceObject.class);

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

  @Test
  public void testFluentMethods() {
    new ResourceDocument<Void>().withData(null).withMeta(null).withJsonapi(null)
        .withLinks(null).withIncluded(null);
    new ResourcesDocument<Void>().withData(null).withMeta(null)
        .withJsonapi(null).withLinks(null).withIncluded(null);
    new ErrorsDocument().withErrors(null).withMeta(null).withJsonapi(null)
        .withLinks(null).withIncluded(null);
    new ErrorObject().withId(null).withLinks(null).withStatus(null)
        .withCode(null).withTitle(null).withDetail(null).withSource(null)
        .withMeta(null);
    new JsonApiObject().withVersion(null).withMeta(null);
    new LinkObject().withHref(null).withMeta(null);
    new RelationshipObject<Void>().withLinks(null).withData(null)
        .withMeta(null);
    new ResourceObject<Void>().withType(null).withId(null).withAttributes(null)
        .withRelationships(null).withLinks(null).withIncluded(null)
        .withMeta(null);
    new SourceObject().withPointer(null).withParameter(null);
  }

  @SuppressWarnings({ "unchecked", "unused" })
  @Test
  public void testStaticMethods() {
    ResourceDocument<JpaEntity<String>> rd;
    rd = JsonApi.resourceDocument(new JpaEntity<String>(), "entities", "12");
    assertEquals("entities", rd.getData().getType());
    assertEquals("12", rd.getData().getId());
    rd = JsonApi.resourceDocument(new JpaEntity<String>(), "entities");

    ResourcesDocument<JpaEntity<String>> rsd;
    rsd = JsonApi.resourcesDocument(Arrays.asList(new JpaEntity<String>()),
        "entities", new Function<JpaEntity<String>, String>() {

          @Override
          public String apply(JpaEntity<String> input) {
            return "34";
          }

        });
    for (ResourceObject<JpaEntity<String>> ro : rsd.getData()) {
      assertEquals("entities", ro.getType());
      assertEquals("34", ro.getId());
    }
    rsd = JsonApi.resourcesDocument(Arrays.asList(new JpaEntity<String>()),
        "entities");

    ResourceObject<JpaEntity<String>> ro;
    ro = JsonApi.resource(new JpaEntity<String>(), "entities", "56");
    assertEquals("entities", ro.getType());
    assertEquals("56", ro.getId());
    ro = JsonApi.resource(new JpaEntity<String>(), "entities");

    RelationshipObject<JpaEntity<String>> rel;
    rel = JsonApi.relationship(new JpaEntity<String>(), "entities", "78");
    assertEquals("entities", rel.getData().getType());
    assertEquals("78", rel.getData().getId());
    rel = JsonApi.relationship(new JpaEntity<String>(), "entities");

    rd = JsonApi.resourceDocument();
    rsd = JsonApi.resourcesDocument();
    ro = JsonApi.resource();
    rel = JsonApi.relationship();
    ErrorsDocument ed = JsonApi.errorsDocument();
    ErrorObject eo = JsonApi.error();
    LinkObject lo = JsonApi.link();
    SourceObject so = JsonApi.source();
    JsonApiObject jao = JsonApi.jsonApi();
  }

  @Test
  public void testPrivateConstruct() throws Exception {
    Constructor<JsonApi> c = JsonApi.class.getDeclaredConstructor();
    assertTrue(Modifier.isPrivate(c.getModifiers()));
    c.setAccessible(true);
    c.newInstance();
  }

  @Test
  public void testJsonable() throws Exception {
    ResourceDocument<JpaEntity<Long>> rd;
    ResourcesDocument<JpaEntity<Long>> rsd;
    ErrorsDocument ed;
    ErrorObject er;
    JsonApiObject jao;
    LinkObject lo;
    RelationshipObject<JpaEntity<Long>> relo;
    ResourceObject<JpaEntity<Long>> ro;
    SourceObject so;

    rd = new ResourceDocument<JpaEntity<Long>>();
    assertEquals(rd.toJson(), mapper.writeValueAsString(rd));

    rsd = new ResourcesDocument<JpaEntity<Long>>();
    assertEquals(rsd.toJson(), mapper.writeValueAsString(rsd));

    ed = new ErrorsDocument();
    assertEquals(ed.toJson(), mapper.writeValueAsString(ed));

    er = new ErrorObject();
    assertEquals(er.toJson(), mapper.writeValueAsString(er));

    jao = new JsonApiObject();
    assertEquals(jao.toJson(), mapper.writeValueAsString(jao));

    lo = new LinkObject();
    assertEquals(lo.toJson(), mapper.writeValueAsString(lo));

    relo = new RelationshipObject<JpaEntity<Long>>();
    assertEquals(relo.toJson(), mapper.writeValueAsString(relo));

    ro = new ResourceObject<JpaEntity<Long>>();
    assertEquals(ro.toJson(), mapper.writeValueAsString(ro));

    so = new SourceObject();
    assertEquals(so.toJson(), mapper.writeValueAsString(so));
  }

}
