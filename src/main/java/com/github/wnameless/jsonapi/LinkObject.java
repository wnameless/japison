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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.Jsonable;
import com.google.common.base.Objects;

/**
 * 
 * {@link LinkObject} is designed by the link object in JSON API specification.
 * 
 * @see <a href="http://jsonapi.org/format/#document-links">JSON API
 *      Specification (v1.0) Links</a>
 *
 */
@JsonInclude(NON_DEFAULT)
public class LinkObject implements Jsonable<LinkObject> {

  @NotNull
  private String href;

  @Valid
  private Object meta;

  public LinkObject() {}

  /**
   * Returns a string containing the link's URL.
   * 
   * @return a string containing the link's URL
   */
  public String getHref() {
    return href;
  }

  /**
   * Sets a string containing the link's URL.
   * 
   * @param href
   *          a string containing the link's URL
   */
  public void setHref(String href) {
    this.href = href;
  }

  /**
   * A chaining method for {@link #setHref}.
   * 
   * @param href
   *          a string containing the link's URL
   * @return this {@link LinkObject}
   */
  public LinkObject withHref(String href) {
    setHref(href);
    return this;
  }

  /**
   * Returns a meta object containing non-standard meta-information about the
   * link.
   * 
   * @return a meta object
   */
  public Object getMeta() {
    return meta;
  }

  /**
   * Sets a meta object containing non-standard meta-information about the link.
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
   * @return this {@link LinkObject}
   */
  public LinkObject withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof LinkObject)) return false;
    LinkObject castOther = (LinkObject) other;
    return Objects.equal(href, castOther.href)
        && Objects.equal(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(href, meta);
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
