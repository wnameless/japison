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

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import java.io.IOException;

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

@JsonInclude(NON_NULL)
public class JsonApiObject implements Jsonable<JsonApiObject> {

  private String version = "1.0";

  @Valid
  private Object meta;

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public JsonApiObject withVersion(String version) {
    setVersion(version);
    return this;
  }

  public Object getMeta() {
    return meta;
  }

  public void setMeta(Object meta) {
    this.meta = meta;
  }

  public JsonApiObject withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof JsonApiObject)) return false;
    JsonApiObject castOther = (JsonApiObject) other;
    return Objects.equal(version, castOther.version)
        && Objects.equal(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(version, meta);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("version", version)
        .add("meta", meta).toString();
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
  public JsonApiObject fromJson(String json) {
    JsonApiObject obj = null;
    try {
      obj = new ObjectMapper().readValue(json,
          new TypeReference<JsonApiObject>() {});
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
