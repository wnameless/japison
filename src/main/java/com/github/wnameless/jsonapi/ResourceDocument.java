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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.jonpeterson.jackson.module.interceptor.JsonInterceptors;
import com.github.wnameless.json.Jsonable;
import com.github.wnameless.jsonapi.jackson.DataArrayifyInterceptor;
import com.github.wnameless.jsonapi.jackson.ObjectMapperFactory;
import com.github.wnameless.jsonapi.jackson.SingularDataObjectifyInterceptor;

/**
 * 
 * {@link ResourceDocument} is designed by the resource document in JSON API
 * specification.
 * 
 * @see <a href="http://jsonapi.org/format/#document-structure">JSON API
 *      Specification (v1.0) Document Structure</a>
 *
 */
@JsonInterceptors(beforeDeserialization = DataArrayifyInterceptor.class,
    afterSerialization = SingularDataObjectifyInterceptor.class)
@JsonInclude(NON_DEFAULT)
public class ResourceDocument<T>
    implements Document<T>, Jsonable<ResourceDocument<T>> {

  @JsonInclude(ALWAYS)
  @Valid
  private List<ResourceObject<T>> data = new ArrayList<>();

  @Valid
  private Object meta;

  @Valid
  private JsonApiObject jsonapi;

  @Valid
  private Map<String, LinkObject> links = new LinkedHashMap<>();

  @Valid
  private List<ResourceObject<?>> included = new ArrayList<>();

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
    List<ResourceObject<T>> list = new ArrayList<>();
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
    setData(new ArrayList<>(data));
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
    return Objects.equals(data, castOther.getData())
        && Objects.equals(meta, castOther.getMeta())
        && Objects.equals(jsonapi, castOther.getJsonapi())
        && Objects.equals(links, castOther.getLinks())
        && Objects.equals(included, castOther.getIncluded());
  }

  @Override
  public int hashCode() {
    return Objects.hash(data, meta, jsonapi, links, included);
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
