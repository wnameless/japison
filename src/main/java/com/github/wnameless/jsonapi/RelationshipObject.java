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

import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.Jsonable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 
 * {@link RelationshipObject} is designed by the relationship object in JSON API
 * specification.
 * 
 * @see JSON API Specification (v1.0) <a href=
 *      "http://jsonapi.org/format/#document-resource-object-relationships">
 *      Relationships</a>
 *
 */
@JsonInclude(NON_DEFAULT)
public class RelationshipObject<T> implements Jsonable<RelationshipObject<T>> {

  @Valid
  private Map<String, LinkObject> links;

  @Valid
  private ResourceObject<T> data;

  @Valid
  private Object meta;

  /**
   * Returns {@link LinkObject}s.
   * 
   * @return {@link LinkObject}s, keys are the names of links
   */
  @Valid
  public Map<String, LinkObject> getLinks() {
    return links;
  }

  /**
   * Sets {@link LinkObject}s.
   * 
   * @param links
   *          {@link LinkObject}s, keys are the names of links
   */
  public void setLinks(Map<String, LinkObject> links) {
    this.links = links;
  }

  /**
   * A chaining method for {@link setLinks}.
   * 
   * @param links
   *          {@link LinkObject}s, keys are the names of links
   * @return this {@link RelationshipObject}
   */
  public RelationshipObject<T> withLinks(Map<String, LinkObject> links) {
    setLinks(links);
    return this;
  }

  /**
   * Returns a {@link ResourceObject}.
   * 
   * @return a {@link ResourceObject}
   */
  public ResourceObject<T> getData() {
    return data;
  }

  /**
   * Sets a {@link ResourceObject}.
   * 
   * @param data
   *          a {@link ResourceObject}
   */
  public void setData(ResourceObject<T> data) {
    this.data = data;
  }

  /**
   * A chaining method for {@link setData}.
   * 
   * @param data
   *          a {@link ResourceObject}
   * @return this {@link RelationshipObject}
   */
  public RelationshipObject<T> withData(ResourceObject<T> data) {
    setData(data);
    return this;
  }

  /**
   * Returns a meta object that contains non-standard meta-information about the
   * relationship.
   * 
   * @return a meta object
   */
  public Object getMeta() {
    return meta;
  }

  /**
   * Sets a meta object that contains non-standard meta-information about the
   * relationship.
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
   * @return this {@link RelationshipObject}
   */
  public RelationshipObject<T> withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof RelationshipObject)) return false;
    RelationshipObject<?> castOther = (RelationshipObject<?>) other;
    return Objects.equal(links, castOther.links)
        && Objects.equal(data, castOther.data)
        && Objects.equal(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(links, data, meta);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("links", links)
        .add("data", data).add("meta", meta).toString();
  }

  @Override
  public String toJson() {
    String json = null;
    try {
      json = new ObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

}
