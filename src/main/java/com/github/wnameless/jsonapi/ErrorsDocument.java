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

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wnameless.json.Jsonable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

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
  public Void getData() {
    return null;
  }

  @Override
  public void setData(Void data) {}

  @Override
  public List<ErrorObject> getErrors() {
    return errors;
  }

  @Override
  public void setErrors(List<ErrorObject> errors) {
    this.errors = errors;
  }

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
    return MoreObjects.toStringHelper(this).add("errors", errors)
        .add("meta", meta).add("jsonapi", jsonapi).add("links", links)
        .add("included", included).toString();
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

  @Override
  public ErrorsDocument fromJson(String json) {
    ErrorsDocument obj = null;
    try {
      obj = new ObjectMapper().readValue(json,
          new TypeReference<ErrorsDocument>() {});
    } catch (JsonParseException e) {
      throw new RuntimeException(e);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return obj;
  }

}