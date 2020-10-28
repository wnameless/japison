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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import java.util.EnumMap;
import java.util.Objects;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.base.Jsonable;
import com.github.wnameless.json.japison.annotation.AnnotatedValueType;
import com.github.wnameless.json.japison.jackson.JapisonFactory;

@JsonInclude(NON_DEFAULT)
public class ResourceIdentifierObject implements ResourceIdentifier, Jsonable {

  @JsonInclude(ALWAYS)
  @NotNull
  private String type;

  @JsonInclude(ALWAYS)
  @NotNull
  private String id;

  @Valid
  private Object meta;

  public ResourceIdentifierObject() {}

  public ResourceIdentifierObject(Object resourceIdObj) {
    EnumMap<AnnotatedValueType, String> annotatedVals =
        JsonApi.Utils.getAllAnnotatedValues(resourceIdObj);
    type = annotatedVals.get(AnnotatedValueType.TYPE);
    id = annotatedVals.get(AnnotatedValueType.ID);
  }

  /**
   * Returns the type of resource.
   * 
   * @return the type of resource
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type of resource.
   * 
   * @param type
   *          the type of resource
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * A chaining method for {@link setType}.
   * 
   * @param type
   *          the type of resource
   * @return this {@link ResourceObject}
   */
  public ResourceIdentifierObject withType(String type) {
    setType(type);
    return this;
  }

  /**
   * Returns the id of resource.
   * 
   * @return the id of resource
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id of resource.
   * 
   * @param id
   *          the id of resource
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * A chaining method for {@link setId}.
   * 
   * @param id
   *          the id of resource
   * @return this {@link ResourceObject}
   */
  public ResourceIdentifierObject withId(String id) {
    setId(id);
    return this;
  }

  /**
   * Returns a meta object.
   * 
   * @return a meta object
   */
  public Object getMeta() {
    return meta;
  }

  /**
   * Sets a meta object.
   * 
   * @param meta
   *          a meta object
   */
  public void setMeta(Object meta) {
    this.meta = meta;
  }

  /**
   * A chaining object for {@link #setMeta}.
   * 
   * @param meta
   *          a meta object
   * @return this {@link ResourceObject}
   */
  public ResourceIdentifierObject withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof ResourceIdentifierObject)) return false;
    ResourceIdentifierObject castOther = (ResourceIdentifierObject) other;
    return Objects.equals(type, castOther.type)
        && Objects.equals(id, castOther.id)
        && Objects.equals(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, meta);
  }

  @Override
  public String toString() {
    return toJson();
  }

  @Override
  public String toJson() {
    String json = null;
    try {
      json = JapisonFactory.getObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

}
