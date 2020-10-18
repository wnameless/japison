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

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.Jsonable;
import com.github.wnameless.jsonapi.jackson.ObjectMapperFactory;
import com.github.wnameless.jsonapi.util.UnpackableList;

/**
 * 
 * {@link RelationshipObject} is designed by the relationship object in JSON API
 * specification.
 * 
 * @see <a href=
 *      "http://jsonapi.org/format/#document-resource-object-relationships">JSON
 *      API Specification (v1.0) Relationships</a>
 *
 */
// @JsonInterceptors(beforeDeserialization = DataArrayifyInterceptor.class,
// afterSerialization = SingularDataObjectifyInterceptor.class)
@JsonInclude(NON_DEFAULT)
public class RelationshipObject implements Jsonable<RelationshipObject> {

  @Valid
  private Map<String, LinkObject> links;

  @Valid
  private UnpackableList<ResourceIdentifier> data;

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
  public RelationshipObject withLinks(Map<String, LinkObject> links) {
    setLinks(links);
    return this;
  }

  /**
   * Returns a {@link ResourceObject}.
   * 
   * @return a {@link ResourceObject}
   */
  public UnpackableList<ResourceIdentifier> getData() {
    return data;
  }

  /**
   * Sets a {@link ResourceObject}.
   * 
   * @param data
   *          a {@link ResourceObject}
   */
  public void setData(UnpackableList<ResourceIdentifier> data) {
    this.data = data;
  }

  /**
   * A chaining method for {@link #setData}.
   * 
   * @param data
   *          a {@link ResourceIdentifier}
   * @return this {@link RelationshipObject}
   */
  public RelationshipObject withData(ResourceIdentifier data) {
    UnpackableList<ResourceIdentifier> list = new UnpackableList<>();
    list.addSingular(data);
    setData(list);
    return this;
  }

  /**
   * A chaining method for {@link #setData}.
   * 
   * @param data
   *          a collection of {@link ResourceIdentifier}
   * @return this {@link RelationshipObject}
   */
  public RelationshipObject withData(Collection<ResourceIdentifier> data) {
    UnpackableList<ResourceIdentifier> list = new UnpackableList<>();
    list.addAll(data);
    setData(list);
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
  public RelationshipObject withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof RelationshipObject)) return false;
    RelationshipObject castOther = (RelationshipObject) other;
    return Objects.equals(links, castOther.links)
        && Objects.equals(data, castOther.data)
        && Objects.equals(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(links, data, meta);
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
