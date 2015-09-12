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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(NON_DEFAULT)
public class Document<T> {

  @Valid
  private T data;

  @Valid
  private List<ErrorObject> errors = newArrayList();

  @Valid
  private Object meta;

  @Valid
  private JsonApiObject jsonapi;

  @Valid
  private Map<String, LinkObject> links = newLinkedHashMap();

  @Valid
  private List<ResourceObject<?>> included = newArrayList();

  public T getData() {
    return data;
  }

  public Document<T> setData(T data) {
    this.data = data;
    return this;
  }

  public List<ErrorObject> getErrors() {
    return errors;
  }

  public Document<T> setErrors(List<ErrorObject> errors) {
    this.errors = errors;
    return this;
  }

  public Object getMeta() {
    return meta;
  }

  public Document<T> setMeta(Object meta) {
    this.meta = meta;
    return this;
  }

  public JsonApiObject getJsonapi() {
    return jsonapi;
  }

  public Document<T> setJsonapi(JsonApiObject jsonapi) {
    this.jsonapi = jsonapi;
    return this;
  }

  public Map<String, LinkObject> getLinks() {
    return links;
  }

  public Document<T> setLinks(Map<String, LinkObject> links) {
    this.links = links;
    return this;
  }

  public List<ResourceObject<?>> getIncluded() {
    return included;
  }

  public Document<T> setIncluded(List<ResourceObject<?>> included) {
    this.included = included;
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof Document)) return false;
    Document<?> castOther = (Document<?>) other;
    return Objects.equal(data, castOther.data)
        && Objects.equal(meta, castOther.meta)
        && Objects.equal(jsonapi, castOther.jsonapi)
        && Objects.equal(links, castOther.links)
        && Objects.equal(included, castOther.included);
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

}
