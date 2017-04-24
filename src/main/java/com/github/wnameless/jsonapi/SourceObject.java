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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.Jsonable;
import com.google.common.base.Objects;

/**
 * 
 * {@link SourceObject} is designed by the source object in JSON API
 * specification.
 * 
 * @see <a href= "http://jsonapi.org/format/#error-objects">JSON API
 *      Specification (v1.0) Error Objects</a>
 *
 */
@JsonInclude(NON_DEFAULT)
public class SourceObject implements Jsonable<SourceObject> {

  private String pointer;

  private String parameter;

  /**
   * Returns a JSON Pointer to the associated entity in the request document.
   * 
   * @return a JSON Pointer
   */
  public String getPointer() {
    return pointer;
  }

  /**
   * Sets a JSON Pointer
   * 
   * @param pointer
   *          a JSON Pointer
   */
  public void setPointer(String pointer) {
    this.pointer = pointer;
  }

  /**
   * A chaining method for {@link #setPointer}.
   * 
   * @param pointer
   *          a JSON Pointer
   * @return this {@link SourceObject}
   */
  public SourceObject withPointer(String pointer) {
    setPointer(pointer);
    return this;
  }

  /**
   * Returns a string indicating which URI query parameter caused the error.
   * 
   * @return an error string
   */
  public String getParameter() {
    return parameter;
  }

  /**
   * Set a string indicating which URI query parameter caused the error.
   * 
   * @param parameter
   *          an error string
   */
  public void setParameter(String parameter) {
    this.parameter = parameter;
  }

  /**
   * A chaining method for {@link #setParameter}.
   * 
   * @param parameter
   *          an error string
   * @return this {@link SourceObject}
   */
  public SourceObject withParameter(String parameter) {
    setParameter(parameter);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof SourceObject)) return false;
    SourceObject castOther = (SourceObject) other;
    return Objects.equal(pointer, castOther.pointer)
        && Objects.equal(parameter, castOther.parameter);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(pointer, parameter);
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
