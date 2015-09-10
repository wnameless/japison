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

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newLinkedHashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(Include.NON_DEFAULT)
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

  public ResourceObject(String type, T attributes) {
    this.type = type;
    this.attributes = attributes;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public T getAttributes() {
    return attributes;
  }

  public void setAttributes(T attributes) {
    this.attributes = attributes;
  }

  public Map<String, RelationshipObject<?>> getRelationships() {
    return relationships;
  }

  public void setRelationships(
      Map<String, RelationshipObject<?>> relationships) {
    this.relationships = relationships;
  }

  public Map<String, LinkObject> getLinks() {
    return links;
  }

  public void setLinks(Map<String, LinkObject> links) {
    this.links = links;
  }

  public List<ResourceObject<?>> getIncluded() {
    return included;
  }

  public void setIncluded(List<ResourceObject<?>> included) {
    this.included = included;
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
