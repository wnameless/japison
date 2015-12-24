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

/**
 * 
 * {@link JsonApi} is a helper class which provides convenient methods to build
 * up JSON API requests or responses.
 *
 */
public final class JsonApi {

  public static final String MEDIA_TYPE = "application/vnd.api+json";

  private JsonApi() {}

  /**
   * Creates a {@link ResourceDocument}.
   * 
   * @return a {@link ResourceDocument}
   */
  public static <T> ResourceDocument<T> resourceDocument() {
    return new ResourceDocument<T>();
  }

  /**
   * Creates a {@link ResourceDocument} by given data attributes with its type
   * and id.
   * 
   * @param attributes
   *          the data object
   * @param type
   *          the type of data
   * @param id
   *          the id of data
   * @return a {@link ResourceDocument}
   */
  public static <T> ResourceDocument<T> resourceDocument(T attributes,
      String type, String id) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    ResourceObject<T> resource = resource(attributes, type);
    if (id != null) resource.setId(id);
    document.setData(resource);
    return document;
  }

  /**
   * Creates a {@link ResourceDocument} by given data attributes with its type.
   * 
   * @param attributes
   *          the data object
   * @param type
   *          the type of data
   * @return a {@link ResourceDocument}
   */
  public static <T> ResourceDocument<T> resourceDocument(T attributes,
      String type) {
    return resourceDocument(attributes, type, null);
  }

  /**
   * Creates a {@link ResourcesDocument}.
   * 
   * @return a {@link ResourcesDocument}
   */
  public static <T> ResourcesDocument<T> resourcesDocument() {
    return new ResourcesDocument<T>();
  }

  /**
   * Creates a {@link ResourceDocument} by given list of data attributes with
   * its type and ids.
   * 
   * @param attrList
   *          an {@link Iterable} of data
   * @param type
   *          of data
   * @param idProvider
   *          function to compute the id of data
   * @return a {@link ResourcesDocument}
   */
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

  /**
   * Creates a {@link ResourceDocument} by given list of data attributes with
   * its type.
   * 
   * @param attrList
   *          an {@link Iterable} of data
   * @param type
   *          of data
   * @return a {@link ResourcesDocument}
   */
  public static <T> ResourcesDocument<T> resourcesDocument(Iterable<T> attrList,
      String type) {
    return resourcesDocument(attrList, type, null);
  }

  /**
   * Creates a {@link ErrorsDocument}.
   * 
   * @return a {@link ErrorsDocument}
   */
  public static ErrorsDocument errorsDocument() {
    return new ErrorsDocument();
  }

  /**
   * Creates a {@link ErrorObject}.
   * 
   * @return a {@link ErrorObject}
   */
  public static ErrorObject error() {
    return new ErrorObject();
  }

  /**
   * Creates a {@link ResourceObject}.
   * 
   * @return a {@link ResourceObject}
   */
  public static <T> ResourceObject<T> resource() {
    return new ResourceObject<T>();
  }

  /**
   * Creates a {@link ResourceObject} by given data attributes with its type and
   * id.
   * 
   * @param attributes
   *          the data object
   * @param type
   *          the type of data
   * @param id
   *          the id of data
   * @return a {@link ResourceObject}
   */
  public static <T> ResourceObject<T> resource(T attributes, String type,
      String id) {
    return new ResourceObject<T>().withAttributes(attributes).withType(type)
        .withId(id);
  }

  /**
   * Creates a {@link ResourceObject} by given data attributes with its type.
   * 
   * @param attributes
   *          the data object
   * @param type
   *          the type of data
   * @return a {@link ResourceObject}
   */
  public static <T> ResourceObject<T> resource(T attributes, String type) {
    return new ResourceObject<T>().withAttributes(attributes).withType(type);
  }

  /**
   * Creates a {@link RelationshipObject}.
   * 
   * @return a {@link RelationshipObject}
   */
  public static <T> RelationshipObject<T> relationship() {
    return new RelationshipObject<T>();
  }

  /**
   * Creates a {@link RelationshipObject} by given data attributes and its type
   * and id.
   * 
   * @param attributes
   *          the data object
   * @param type
   *          the type of data
   * @param id
   *          the id of data
   * @return a {@link RelationshipObject}
   */
  public static <T> RelationshipObject<T> relationship(T attributes,
      String type, String id) {
    return new RelationshipObject<T>().withData(resource(attributes, type, id));
  }

  /**
   * Creates a {@link RelationshipObject} by given data attributes and its type.
   * 
   * 
   * @param attributes
   *          the data object
   * @param type
   *          the type of data
   * @return a {@link RelationshipObject}
   */
  public static <T> RelationshipObject<T> relationship(T attributes,
      String type) {
    return new RelationshipObject<T>().withData(resource(attributes, type));
  }

  /**
   * Creates a {@link LinkObject}.
   * 
   * @return a {@link LinkObject}
   */
  public static LinkObject link() {
    return new LinkObject();
  }

  /**
   * Creates a {@link SourceObject}.
   * 
   * @return a {@link SourceObject}
   */
  public static SourceObject source() {
    return new SourceObject();
  }

  /**
   * Creates a {@link JsonApiObject}.
   * 
   * @return a {@link JsonApiObject}
   */
  public static JsonApiObject jsonApi() {
    return new JsonApiObject();
  }

}
