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

import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(Include.NON_DEFAULT)
public class RelationshipObject<T> {

  @Valid
  private Map<String, LinkObject> links;

  @Valid
  private ResourceObject<T> data;

  @Valid
  private Object meta;

  @Valid
  public Map<String, LinkObject> getLinks() {
    return links;
  }

  public void setLinks(Map<String, LinkObject> links) {
    this.links = links;
  }

  public ResourceObject<T> getData() {
    return data;
  }

  public void setData(ResourceObject<T> data) {
    this.data = data;
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
    if (!(other instanceof RelationshipObject)) return false;
    RelationshipObject<?> castOther = (RelationshipObject<?>) other;
    return Objects.equal(links, castOther.links)
        && Objects.equal(data, castOther.data)
        && Objects.equal(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(links, data, meta);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("links", links)
        .add("data", data).add("meta", meta).toString();
  }

}
