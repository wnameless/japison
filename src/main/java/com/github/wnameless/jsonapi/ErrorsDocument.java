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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.Jsonable;
import com.github.wnameless.jsonapi.jackson.ObjectMapperFactory;
import com.google.common.base.Objects;

/**
 * 
 * {@link ErrorsDocument} is designed by the errors document in JSON API
 * specification.
 * 
 * @see <a href="http://jsonapi.org/format/#document-structure">JSON API
 *      Specification (v1.0) Document Structure</a>
 *
 */
@JsonInclude(NON_DEFAULT)
public class ErrorsDocument
    implements Document<Void>, Jsonable<ErrorsDocument> {

  @JsonInclude(ALWAYS)
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

  @Override
  public List<ResourceObject<Void>> getData() {
    return null;
  }

  @Override
  public void setData(List<ResourceObject<Void>> data) {}

  @Override
  public List<ErrorObject> getErrors() {
    return errors;
  }

  @Override
  public void setErrors(List<ErrorObject> errors) {
    this.errors = errors;
  }

  /**
   * A chaining method for {@link setErrors}.
   * 
   * @param errors
   *          a list of error objects
   * @return this {@link ErrorsDocument}
   */
  public ErrorsDocument withErrors(List<ErrorObject> errors) {
    setErrors(errors);
    return this;
  }

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
  public ErrorsDocument withMeta(Object meta) {
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
  public ErrorsDocument withJsonapi(JsonApiObject jsonapi) {
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
  public ErrorsDocument withLinks(Map<String, LinkObject> links) {
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
  public ErrorsDocument withIncluded(List<ResourceObject<?>> included) {
    setIncluded(included);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof ErrorsDocument)) return false;
    ErrorsDocument castOther = (ErrorsDocument) other;
    return Objects.equal(errors, castOther.getErrors())
        && Objects.equal(meta, castOther.getMeta())
        && Objects.equal(jsonapi, castOther.getJsonapi())
        && Objects.equal(links, castOther.getLinks())
        && Objects.equal(included, castOther.getIncluded());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(errors, meta, jsonapi, links, included);
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