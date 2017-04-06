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

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wnameless.json.Jsonable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * 
 * {@link ResourceDocument} is designed by the resource document in JSON API
 * specification.
 * 
 * @see <a href="http://jsonapi.org/format/#document-structure">JSON API
 *      Specification (v1.0) Document Structure</a>
 *
 */
@JsonInclude(NON_DEFAULT)
public class ResourceDocument<T>
    implements Document<T>, Jsonable<ResourceDocument<T>> {

  @JsonSerialize(using = CollectionSerializer.class)
  @JsonDeserialize(using = CollectionDeserializer.class)
  @JsonInclude(ALWAYS)
  @Valid
  private List<ResourceObject<T>> data = newArrayList();

  @Valid
  private Object meta;

  @Valid
  private JsonApiObject jsonapi;

  @Valid
  private Map<String, LinkObject> links = newLinkedHashMap();

  @Valid
  private List<ResourceObject<?>> included = newArrayList();

  @Override
  public List<ResourceObject<T>> getData() {
    return data;
  }

  @Override
  public void setData(List<ResourceObject<T>> data) {
    this.data = data;
  }

  /**
   * A chaining method for {@link #setData}.
   * 
   * @param data
   *          the document's "primary data".
   * @return this {@link ResourceDocument}
   */
  public ResourceDocument<T> withData(ResourceObject<T> data) {
    List<ResourceObject<T>> list = newArrayList();
    list.add(data);
    setData(list);
    return this;
  }

  /**
   * A chaining method for {@link #setData}.
   * 
   * @param data
   *          the document's "primary data".
   * @return this {@link ResourceDocument}
   */
  public ResourceDocument<T> withData(Collection<ResourceObject<T>> data) {
    setData(newArrayList(data));
    return this;
  }

  @Override
  public List<ErrorObject> getErrors() {
    return null;
  }

  @Override
  public void setErrors(List<ErrorObject> errors) {}

  @Override
  public Object getMeta() {
    return meta;
  }

  @Override
  public void setMeta(Object meta) {
    this.meta = meta;
  }

  /**
   * A chaining method for {@link #setMeta}.
   * 
   * @param meta
   *          a meta object
   * @return this {@link ErrorsDocument}
   */
  public ResourceDocument<T> withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public JsonApiObject getJsonapi() {
    return jsonapi;
  }

  @Override
  public void setJsonapi(JsonApiObject jsonapi) {
    this.jsonapi = jsonapi;
  }

  /**
   * A chaining method for {@link #setJsonapi}.
   * 
   * @param jsonapi
   *          a {@link JsonApiObject} describing the server's implementation
   * @return this {@link ErrorsDocument}
   */
  public ResourceDocument<T> withJsonapi(JsonApiObject jsonapi) {
    setJsonapi(jsonapi);
    return this;
  }

  @Override
  public Map<String, LinkObject> getLinks() {
    return links;
  }

  @Override
  public void setLinks(Map<String, LinkObject> links) {
    this.links = links;
  }

  /**
   * A chaining method for {@link #setLinks}.
   * 
   * @param links
   *          {@link LinkObject}s
   * @return this {@link ErrorsDocument}
   */
  public ResourceDocument<T> withLinks(Map<String, LinkObject> links) {
    setLinks(links);
    return this;
  }

  @Override
  public List<ResourceObject<?>> getIncluded() {
    return included;
  }

  @Override
  public void setIncluded(List<ResourceObject<?>> included) {
    this.included = included;
  }

  /**
   * A chaining method for {@link setIncluded}.
   * 
   * @param included
   *          a list of {@link ResourceObject}s
   * @return this {@link ErrorsDocument}
   */
  public ResourceDocument<T> withIncluded(List<ResourceObject<?>> included) {
    setIncluded(included);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof ResourceDocument)) return false;
    ResourceDocument<?> castOther = (ResourceDocument<?>) other;
    return Objects.equal(data, castOther.getData())
        && Objects.equal(meta, castOther.getMeta())
        && Objects.equal(jsonapi, castOther.getJsonapi())
        && Objects.equal(links, castOther.getLinks())
        && Objects.equal(included, castOther.getIncluded());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(data, meta, jsonapi, links, included);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("data", data).add("meta", meta)
        .add("jsonapi", jsonapi).add("links", links).add("included", included)
        .toString();
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
