/*
 *
 * Copyright 2017 Wei-Ming Wu
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

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.Jsonable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(NON_DEFAULT)
public class CompoundResource<T> implements Jsonable<CompoundResource<T>> {

  @Valid
  private List<ResourceObject<T>> data;

  @Valid
  private List<ResourceObject<?>> included;

  public List<ResourceObject<T>> getData() {
    return data;
  }

  public void setData(List<ResourceObject<T>> data) {
    this.data = data;
  }

  public CompoundResource<T> withData(List<ResourceObject<T>> data) {
    this.data = data;
    return this;
  }

  public List<ResourceObject<?>> getIncluded() {
    return included;
  }

  public void setIncluded(List<ResourceObject<?>> included) {
    this.included = included;
  }

  public CompoundResource<T> withIncluded(List<ResourceObject<?>> included) {
    this.included = included;
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof CompoundResource)) return false;
    CompoundResource<?> castOther = (CompoundResource<?>) other;
    return Objects.equal(data, castOther.data)
        && Objects.equal(included, castOther.included);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(data, included);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("data", data)
        .add("included", included).toString();
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
