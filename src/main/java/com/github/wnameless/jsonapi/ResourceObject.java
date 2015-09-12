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
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(NON_DEFAULT)
public class ResourceObject<T> {

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

  public ResourceObject() {}

  public ResourceObject(String type) {
    this.type = type;
  }

  public ResourceObject(String type, T attributes) {
    this.type = type;
    this.attributes = attributes;
  }

  public String getType() {
    return type;
  }

  public ResourceObject<T> setType(String type) {
    this.type = type;
    return this;
  }

  public String getId() {
    return id;
  }

  public ResourceObject<T> setId(String id) {
    this.id = id;
    return this;
  }

  public T getAttributes() {
    return attributes;
  }

  public ResourceObject<T> setAttributes(T attributes) {
    this.attributes = attributes;
    return this;
  }

  public Map<String, RelationshipObject<?>> getRelationships() {
    return relationships;
  }

  public ResourceObject<T> setRelationships(
      Map<String, RelationshipObject<?>> relationships) {
    this.relationships = relationships;
    return this;
  }

  public Map<String, LinkObject> getLinks() {
    return links;
  }

  public ResourceObject<T> setLinks(Map<String, LinkObject> links) {
    this.links = links;
    return this;
  }

  public List<ResourceObject<?>> getIncluded() {
    return included;
  }

  public ResourceObject<T> setIncluded(List<ResourceObject<?>> included) {
    this.included = included;
    return this;
  }

  public Object getMeta() {
    return meta;
  }

  public void setMeta(Object meta) {
    this.meta = meta;
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
    return Objects.hashCode(type, id, attributes, relationships, links, meta);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("type", type).add("id", id)
        .add("attributes", attributes).add("relationships", relationships)
        .add("links", links).add("included", included).add("meta", meta)
        .toString();
  }

}
