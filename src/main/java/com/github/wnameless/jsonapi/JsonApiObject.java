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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.Jsonable;
import com.github.wnameless.jsonapi.jackson.ObjectMapperFactory;

/**
 * 
 * {@link JsonApiObject} is designed by the JSON API object in JSON API
 * specification.
 * 
 * @see <a href="http://jsonapi.org/format/#document-jsonapi-object">JSON API
 *      Specification (v1.0) JSON API Object</a>
 *
 */
@JsonInclude(NON_NULL)
public class JsonApiObject implements Jsonable<JsonApiObject> {

  private String version = "1.0";

  @Valid
  private Object meta;

  /**
   * Returns a string indicating the highest JSON API version supported.
   * 
   * @return a string indicating the highest JSON API version supported.
   */
  public String getVersion() {
    return version;
  }

  /**
   * Sets a string indicating the highest JSON API version supported.
   * 
   * @param version
   *          the highest JSON API version
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * A chaining method for {@link #setVersion}.
   * 
   * @param version
   *          the highest JSON API version
   * @return this {@link JsonApiObject}
   */
  public JsonApiObject withVersion(String version) {
    setVersion(version);
    return this;
  }

  /**
   * Returns a meta object that contains non-standard meta-information.
   * 
   * @return a meta object
   */
  public Object getMeta() {
    return meta;
  }

  /**
   * Sets a meta object that contains non-standard meta-information.
   * 
   * @param meta
   *          a meta object
   */
  public void setMeta(Object meta) {
    this.meta = meta;
  }

  /**
   * A chaining method for {@link #setMeta}.
   * 
   * @param meta
   *          a meta object
   * @return this {@link JsonApiObject}
   */
  public JsonApiObject withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof JsonApiObject)) return false;
    JsonApiObject castOther = (JsonApiObject) other;
    return Objects.equals(version, castOther.version)
        && Objects.equals(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, meta);
  }

  @Override
  public String toString() {
    return toJson();
  }

  @Override
  public String toJson() {
    String json = null;
    try {
      json = ObjectMapperFactory.getObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

}
