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
package com.github.wnameless.json;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.wnameless.jsonapi.ObjectMapperFactory;
import com.google.common.base.Objects;

@JsonSerialize(using = AutoArrayifyValueSerializer.class)
public final class AutoArrayifyValue<T>
    implements Jsonable<AutoArrayifyValue<T>> {

  @Valid
  private final List<T> values = newArrayList();
  private final boolean arrayify;

  public AutoArrayifyValue(T value) {
    arrayify = false;
    values.add(value);
  }

  public AutoArrayifyValue(T value, boolean arrayify) {
    this.arrayify = arrayify;
    values.add(value);
  }

  public AutoArrayifyValue(Collection<T> values) {
    arrayify = true;
    this.values.addAll(values);
  }

  public T getValue() {
    return values.isEmpty() ? null : values.get(0);
  }

  public List<T> getValues() {
    return values;
  }

  public boolean isArrayify() {
    return arrayify;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(arrayify, values);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof AutoArrayifyValue)) return false;
    AutoArrayifyValue<?> other = (AutoArrayifyValue<?>) obj;
    return Objects.equal(arrayify, other.arrayify)
        && Objects.equal(values, other.values);
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
