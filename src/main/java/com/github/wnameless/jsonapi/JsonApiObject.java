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

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(NON_NULL)
public class JsonApiObject {

  private String version = "1.0";

  @Valid
  private Object meta;

  public String getVersion() {
    return version;
  }

  public JsonApiObject setVersion(String version) {
    this.version = version;
    return this;
  }

  public Object getMeta() {
    return meta;
  }

  public JsonApiObject setMeta(Object meta) {
    this.meta = meta;
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

}
