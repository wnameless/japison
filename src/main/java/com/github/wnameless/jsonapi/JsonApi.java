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

import java.util.Arrays;

import com.fasterxml.jackson.core.type.TypeReference;

public final class JsonApi {

  private JsonApi() {}

  public static <T> ResourceDocument<T> resourceDocument(T attributes) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    ResourceObject<T> resource = resource(attributes);
    document.setData(resource);
    return document;
  }

  public static <T> ResourceDocument<T> resourceDocument(Class<T> klass) {
    return new ResourceDocument<T>();
  }

  public static <T> ResourceDocument<T> resourceDocument(
      TypeReference<T> typeRef) {
    return new ResourceDocument<T>();
  }

  public static <T> ResourcesDocument<T> resourcesDocument(
      Iterable<T> attrList) {
    ResourcesDocument<T> document = new ResourcesDocument<T>();
    for (T attributes : attrList) {
      ResourceObject<T> resource = resource(attributes);
      document.getData().add(resource);
    }
    return document;
  }

  public static <T> ResourcesDocument<T> resourcesDocument(T[] attrAry) {
    return resourcesDocument(Arrays.asList(attrAry));
  }

  public static <T> ResourcesDocument<T> resourcesDocument(T attributes) {
    ResourcesDocument<T> document = new ResourcesDocument<T>();
    ResourceObject<T> resource = resource(attributes);
    document.getData().add(resource);
    return document;
  }

  public static <T> ResourcesDocument<T> resourcesDocument(Class<T> klass) {
    return new ResourcesDocument<T>();
  }

  public static <T> ResourcesDocument<T> resourcesDocument(
      TypeReference<T> typeRef) {
    return new ResourcesDocument<T>();
  }

  public static ErrorsDocument errorsDocument() {
    return new ErrorsDocument();
  }

  public static ErrorObject error() {
    return new ErrorObject();
  }

  public static <T> ResourceObject<T> resource(T attributes) {
    return new ResourceObject<T>().setAttributes(attributes);
  }

  public static <T> ResourceObject<T> resource(Class<T> klass) {
    return new ResourceObject<T>();
  }

  public static <T> ResourceObject<T> resource(TypeReference<T> typeRef) {
    return new ResourceObject<T>();
  }

  public static <T> RelationshipObject<T> relationship(T attributes) {
    return new RelationshipObject<T>().setData(resource(attributes));
  }

  public static <T> RelationshipObject<T> relationship(Class<T> klass) {
    return new RelationshipObject<T>();
  }

  public static <T> RelationshipObject<T> relationship(
      TypeReference<T> typeRef) {
    return new RelationshipObject<T>();
  }

  public static LinkObject link() {
    return new LinkObject();
  }

  public static SourceObject source() {
    return new SourceObject();
  }

  public static JsonApiObject jsonApi() {
    return new JsonApiObject();
  }

}
