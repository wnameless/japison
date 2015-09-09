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

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@JsonInclude(Include.NON_DEFAULT)
public class ErrorObject {

  private String id;

  @Valid
  private Map<String, LinkObject> links =
      new LinkedHashMap<String, LinkObject>();

  private String status;

  private String code;

  private String title;

  private String detail;

  private SourceObject source;

  @Valid
  private Object meta;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, LinkObject> getLinks() {
    return links;
  }

  public void setLinks(Map<String, LinkObject> links) {
    this.links = links;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public SourceObject getSource() {
    return source;
  }

  public void setSource(SourceObject source) {
    this.source = source;
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
    if (!(other instanceof ErrorObject)) return false;
    ErrorObject castOther = (ErrorObject) other;
    return Objects.equal(id, castOther.id)
        && Objects.equal(links, castOther.links)
        && Objects.equal(status, castOther.status)
        && Objects.equal(code, castOther.code)
        && Objects.equal(title, castOther.title)
        && Objects.equal(detail, castOther.detail)
        && Objects.equal(source, castOther.source)
        && Objects.equal(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, links, status, code, title, detail, source,
        meta);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("id", id).add("links", links)
        .add("status", status).add("code", code).add("title", title)
        .add("detail", detail).add("source", source).add("meta", meta)
        .toString();
  }

}
