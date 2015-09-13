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

import com.google.common.base.Function;
import com.google.common.reflect.TypeToken;

public final class JsonApi {

  public static final String MEDIA_TYPE = "application/vnd.api+json";

  private JsonApi() {}

  public static <T> ResourceDocument<T> resourceDocument(T attributes,
      String type, Function<T, String> idProvider) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    ResourceObject<T> resource = resource(attributes);
    if (type != null) resource.setType(type);
    if (idProvider != null) resource.setId(idProvider.apply(attributes));
    document.setData(resource);
    return document;
  }

  public static <T> ResourceDocument<T> resourceDocument(T attributes,
      String type) {
    return resourceDocument(attributes, type, null);
  }

  public static <T> ResourceDocument<T> resourceDocument(T attributes) {
    return resourceDocument(attributes, null, null);
  }

  public static <T> ResourceDocument<T> resourceDocument(Class<T> klass) {
    return new ResourceDocument<T>();
  }

  public static <T> ResourceDocument<T> resourceDocument(TypeToken<T> typeRef) {
    return new ResourceDocument<T>();
  }

  public static <T> ResourcesDocument<T> resourcesDocument(Iterable<T> attrList,
      String type, Function<T, String> idProvider) {
    ResourcesDocument<T> document = new ResourcesDocument<T>();
    for (T attributes : attrList) {
      ResourceObject<T> resource = resource(attributes);
      if (type != null) resource.setType(type);
      if (idProvider != null) resource.setId(idProvider.apply(attributes));
      document.getData().add(resource);
    }
    return document;
  }

  public static <T> ResourcesDocument<T> resourcesDocument(Iterable<T> attrList,
      String type) {
    return resourcesDocument(attrList, type, null);
  }

  public static <T> ResourcesDocument<T> resourcesDocument(T[] attrAry,
      String type, Function<T, String> idProvider) {
    return resourcesDocument(Arrays.asList(attrAry), type, idProvider);
  }

  public static <T> ResourcesDocument<T> resourcesDocument(T[] attrAry,
      String type) {
    return resourcesDocument(attrAry, type, null);
  }

  public static <T> ResourcesDocument<T> resourcesDocument(
      Iterable<T> attrList) {
    return resourcesDocument(attrList, null, null);
  }

  public static <T> ResourcesDocument<T> resourcesDocument(T[] attrAry) {
    return resourcesDocument(Arrays.asList(attrAry));
  }

  @SuppressWarnings("unchecked")
  public static <T> ResourcesDocument<T> resourcesDocument(T attributes) {
    return resourcesDocument(Arrays.asList(attributes));
  }

  public static <T> ResourcesDocument<T> resourcesDocument(Class<T> klass) {
    return new ResourcesDocument<T>();
  }

  public static <T> ResourcesDocument<T> resourcesDocument(
      TypeToken<T> typeRef) {
    return new ResourcesDocument<T>();
  }

  public static ErrorsDocument errorsDocument() {
    return new ErrorsDocument();
  }

  public static ErrorObject error() {
    return new ErrorObject();
  }

  public static <T> ResourceObject<T> resource(T attributes) {
    return new ResourceObject<T>().withAttributes(attributes);
  }

  public static <T> ResourceObject<T> resource(Class<T> klass) {
    return new ResourceObject<T>();
  }

  public static <T> ResourceObject<T> resource(TypeToken<T> typeRef) {
    return new ResourceObject<T>();
  }

  public static <T> RelationshipObject<T> relationship(T attributes) {
    return new RelationshipObject<T>().withData(resource(attributes));
  }

  public static <T> RelationshipObject<T> relationship(Class<T> klass) {
    return new RelationshipObject<T>();
  }

  public static <T> RelationshipObject<T> relationship(TypeToken<T> typeRef) {
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
