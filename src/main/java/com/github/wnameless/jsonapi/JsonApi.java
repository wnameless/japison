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

import com.google.common.base.Function;

public final class JsonApi {

  public static final String MEDIA_TYPE = "application/vnd.api+json";

  private JsonApi() {}

  public static <T> ResourceDocument<T> resourceDocument(T attributes,
      String type, String id) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    ResourceObject<T> resource = resource(attributes, type);
    if (id != null) resource.setId(id);
    document.setData(resource);
    return document;
  }

  public static <T> ResourceDocument<T> resourceDocument(T attributes,
      String type) {
    return resourceDocument(attributes, type, null);
  }

  public static <T> ResourcesDocument<T> resourcesDocument(Iterable<T> attrList,
      String type, Function<T, String> idProvider) {
    ResourcesDocument<T> document = new ResourcesDocument<T>();
    for (T attributes : attrList) {
      ResourceObject<T> resource = resource(attributes, type);
      if (idProvider != null) resource.setId(idProvider.apply(attributes));
      document.getData().add(resource);
    }
    return document;
  }

  public static <T> ResourcesDocument<T> resourcesDocument(Iterable<T> attrList,
      String type) {
    return resourcesDocument(attrList, type, null);
  }

  public static ErrorsDocument errorsDocument() {
    return new ErrorsDocument();
  }

  public static ErrorObject error() {
    return new ErrorObject();
  }

  public static <T> ResourceObject<T> resource(T attributes, String type,
      String id) {
    return new ResourceObject<T>().withAttributes(attributes).withType(type)
        .withId(id);
  }

  public static <T> ResourceObject<T> resource(T attributes, String type) {
    return new ResourceObject<T>().withAttributes(attributes).withType(type);
  }

  public static <T> RelationshipObject<T> relationship(T attributes,
      String type, String id) {
    return new RelationshipObject<T>().withData(resource(attributes, type, id));
  }

  public static <T> RelationshipObject<T> relationship(T attributes,
      String type) {
    return new RelationshipObject<T>().withData(resource(attributes, type));
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
