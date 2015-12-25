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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.Jsonable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 
 * {@link ResourceObject} is designed by the resource object in JSON API
 * specification.
 * 
 * @see <a href= "http://jsonapi.org/format/#document-resource-objects">JSON API
 *      Specification (v1.0) Resource Objects</a>
 *
 */
@JsonInclude(NON_DEFAULT)
public class ResourceObject<T> implements Jsonable<ResourceObject<T>> {

  @JsonInclude(ALWAYS)
  @NotNull
  private String type;

  private String id;

  @Valid
  private T attributes;

  @Valid
  private Map<String, RelationshipObject<?>> relationships = newLinkedHashMap();

  @Valid
  private Map<String, LinkObject> links = newLinkedHashMap();

  @Valid
  private List<ResourceObject<?>> included = newArrayList();

  @Valid
  private Object meta;

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
  public ResourceObject<T> withType(String type) {
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
   */
  public ResourceObject<T> withId(String id) {
    setId(id);
    return this;
  }

  /**
   * Returns the data attributes.
   * 
   * @return the data attributes
   */
  public T getAttributes() {
    return attributes;
  }

  /**
   * Sets the data attributes.
   * 
   * @param attributes
   *          the data attributes
   */
  public void setAttributes(T attributes) {
    this.attributes = attributes;
  }

  /**
   * A chaining method for {@link #setAttributes}.
   * 
   * @param attributes
   *          the data attributes
   * @return this {@link ResourceObject}
   */
  public ResourceObject<T> withAttributes(T attributes) {
    setAttributes(attributes);
    return this;
  }

  /**
   * Returns {@link RelationshipObject}s describing relationships between the
   * resource and other JSON API resources.
   * 
   * @return {@link RelationshipObject}s
   */
  public Map<String, RelationshipObject<?>> getRelationships() {
    return relationships;
  }

  /**
   * Sets {@link RelationshipObject}s describing relationships between the
   * resource and other JSON API resources.
   * 
   * @param relationships
   *          {@link RelationshipObject}s
   */
  public void setRelationships(
      Map<String, RelationshipObject<?>> relationships) {
    this.relationships = relationships;
  }

  /**
   * A chaining method for {@link setRelationships}.
   * 
   * @param relationships
   *          {@link RelationshipObject}s
   * @return this {@link ResourceObject}
   */
  public ResourceObject<T> withRelationships(
      Map<String, RelationshipObject<?>> relationships) {
    setRelationships(relationships);
    return this;
  }

  /**
   * Returns {@link LinkObject}s link related to the resource.
   * 
   * @return a map between link name and {@link LinkObject}s
   */
  public Map<String, LinkObject> getLinks() {
    return links;
  }

  /**
   * Sets a chaining method for {@link getLinks}.
   * 
   * @param links
   *          {@link LinkObject}s
   */
  public void setLinks(Map<String, LinkObject> links) {
    this.links = links;
  }

  /**
   * A chaining method for {@link setLinks}.
   * 
   * @param {@link
   *          setLinks}
   */
  public ResourceObject<T> withLinks(Map<String, LinkObject> links) {
    setLinks(links);
    return this;
  }

  /**
   * Returns list of {@link ResourceObject}.
   * 
   * @return a list of {@link ResourceObject}
   */
  public List<ResourceObject<?>> getIncluded() {
    return included;
  }

  /**
   * Sets a list of {@link ResourceObject}.
   *
   * @param included
   *          a list of {@link ResourceObject}
   */
  public void setIncluded(List<ResourceObject<?>> included) {
    this.included = included;
  }

  /**
   * A chaining method for {@link #setIncluded}.
   * 
   * @param included
   *          a list of {@link ResourceObject}
   * @return this {#link ResourceObject}
   */
  public ResourceObject<T> withIncluded(List<ResourceObject<?>> included) {
    setIncluded(included);
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
  public ResourceObject<T> withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof ResourceObject)) return false;
    ResourceObject<?> castOther = (ResourceObject<?>) other;
    return Objects.equal(type, castOther.type)
        && Objects.equal(id, castOther.id)
        && Objects.equal(attributes, castOther.attributes)
        && Objects.equal(relationships, castOther.relationships)
        && Objects.equal(links, castOther.links)
        && Objects.equal(included, castOther.included)
        && Objects.equal(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(type, id, attributes, relationships, links,
        included, meta);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("type", type).add("id", id)
        .add("attributes", attributes).add("relationships", relationships)
        .add("links", links).add("included", included).add("meta", meta)
        .toString();
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
