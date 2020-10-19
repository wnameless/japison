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
package com.github.wnameless.json.japison;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codebox.bean.JavaBeanTester;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class JapisonTest {

  ObjectMapper mapper = new ObjectMapper();

  JpaEntity<String> entity;

  @BeforeEach
  public void setUp() {
    entity = new JpaEntity<String>();
    entity.setData("hahaha");
  }

  @Test
  public void testResourceObjectSetAttributes() throws JsonProcessingException {
    ResourceObject<AnnotatedEntity> ro = new ResourceObject<AnnotatedEntity>();
    ro.setAttributes(new AnnotatedEntity("ABC"));

    String actual = mapper.writeValueAsString(ro);
    assertEquals("{\"type\":\"AE\",\"id\":\"ABC\",\"attributes\":{}}", actual);
  }

  @Test
  public void testWithJackson() throws Exception {
    ResourceDocument<JpaEntity<String>> req = JsonApi.resourceDocument(entity);

    String actual = mapper.writeValueAsString(req);
    assertEquals(
        "{\"data\":{\"type\":\"JpaEntity\",\"attributes\":{\"data\":\"hahaha\"}}}",
        actual);
  }

  @Test
  public void testBeans() {
    JavaBeanTester.builder(ErrorObject.class).loadData().test();
    JavaBeanTester.builder(ErrorsDocument.class).loadData().skip("data").test();
    JavaBeanTester.builder(JsonApiObject.class).loadData().test();
    JavaBeanTester.builder(LinkObject.class).loadData().test();
    JavaBeanTester.builder(RelationshipObject.class).loadData().test();
    JavaBeanTester.builder(ResourceDocument.class).loadData().skip("errors")
        .test();
    JavaBeanTester.builder(ResourceIdentifierObject.class).loadData().test();
    JavaBeanTester.builder(ResourceObject.class).loadData().test();
    JavaBeanTester.builder(SourceObject.class).loadData().test();

    EqualsVerifier.forClass(ErrorObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ErrorsDocument.class)
        .withPrefabValues(ResourceObject.class, new ResourceObject<String>(),
            JsonApi.resource(new JapisonAnnotatedEntity()))
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(JsonApiObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(LinkObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(RelationshipObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ResourceDocument.class)
        .withPrefabValues(ResourceObject.class, new ResourceObject<String>(),
            JsonApi.resource(new JapisonAnnotatedEntity()))
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ResourceIdentifierObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(ResourceObject.class)
        .withPrefabValues(ResourceObject.class, new ResourceObject<String>(),
            JsonApi.resource(new JapisonAnnotatedEntity()))
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
    EqualsVerifier.forClass(SourceObject.class)
        .suppress(Warning.NONFINAL_FIELDS, Warning.STRICT_INHERITANCE).verify();
  }

  @Test
  public void testFluentMethods() {
    new ResourceDocument<Void>().withData((ResourceObject<Void>) null)
        .withMeta(null).withJsonapi(null).withLinks(null).withIncluded(null);
    new ResourceIdentifierObject().withType(null).withId(null).withMeta(null);
    new ErrorsDocument().withErrors(null).withMeta(null).withJsonapi(null)
        .withLinks(null).withIncluded(null);
    new ErrorObject().withId(null).withLinks(null).withStatus(null)
        .withCode(null).withTitle(null).withDetail(null).withSource(null)
        .withMeta(null);
    new JsonApiObject().withVersion(null).withMeta(null);
    new LinkObject().withHref(null).withMeta(null);
    new RelationshipObject().withLinks(null).withData((ResourceIdentifier) null)
        .withMeta(null);
    new ResourceObject<Void>().withType(null).withId(null).withAttributes(null)
        .withRelationships(null).withLinks(null).withIncluded(null)
        .withMeta(null);
    new SourceObject().withPointer(null).withParameter(null);
  }

  @SuppressWarnings({ "unused" })
  @Test
  public void testStaticMethods() {
    ResourceDocument<JpaEntity<String>> rd;
    rd = JsonApi.resourceDocument(new JpaEntity<String>(12L));
    assertEquals("JpaEntity", rd.getData().getSingular().getType());
    assertEquals("12", rd.getData().getSingular().getId());
    rd = JsonApi.resourceDocument(new JpaEntity<String>());

    ResourceObject<JpaEntity<String>> ro;
    ro = JsonApi.resource(new JpaEntity<String>());
    assertEquals("JpaEntity", ro.getType());
    assertEquals(null, ro.getId());
    ro = JsonApi.resource(new JpaEntity<String>());

    RelationshipObject rel;
    rel = JsonApi.relationship(
        JsonApi.resourceIdentifier().withType("entities").withId("78"));
    assertEquals("entities", rel.getData().get(0).getType());
    assertEquals("78", rel.getData().get(0).getId());

    rd = JsonApi.resourceDocument();
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
    JavaBeanTester.builder(JsonApi.class).testPrivateConstructor();
  }

  @Test
  public void testJsonable() throws Exception {
    ResourceDocument<JpaEntity<Long>> rd;
    ResourceIdentifierObject rio;
    ErrorsDocument ed;
    ErrorObject er;
    JsonApiObject jao;
    LinkObject lo;
    RelationshipObject relo;
    ResourceObject<JpaEntity<Long>> ro;
    SourceObject so;

    rd = new ResourceDocument<JpaEntity<Long>>();
    assertEquals(rd.toJson(), mapper.writeValueAsString(rd));
    rd.withData(JsonApi.resource(new JpaEntity<Long>()));

    rio = new ResourceIdentifierObject();
    assertEquals(rio.toJson(), mapper.writeValueAsString(rio));

    ed = new ErrorsDocument();
    assertEquals(ed.toJson(), mapper.writeValueAsString(ed));

    er = new ErrorObject();
    assertEquals(er.toJson(), mapper.writeValueAsString(er));

    jao = new JsonApiObject();
    assertEquals(jao.toJson(), mapper.writeValueAsString(jao));

    lo = new LinkObject();
    assertEquals(lo.toJson(), mapper.writeValueAsString(lo));

    relo = new RelationshipObject();
    assertEquals(relo.toJson(), mapper.writeValueAsString(relo));

    ro = new ResourceObject<JpaEntity<Long>>();
    assertEquals(ro.toJson(), mapper.writeValueAsString(ro));

    so = new SourceObject();
    assertEquals(so.toJson(), mapper.writeValueAsString(so));
  }

}
