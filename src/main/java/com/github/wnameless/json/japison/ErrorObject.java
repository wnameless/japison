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
package com.github.wnameless.json.japison;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.wnameless.json.base.Jsonable;
import com.github.wnameless.json.japison.jackson.JapisonFactory;

/**
 * 
 * {@link ErrorObject} is designed by the error object in JSON API
 * specification.
 * 
 * @see <a href="http://jsonapi.org/format/#error-objects">JSON API
 *      Specification (v1.0) Error Objects</a>
 *
 */
@JsonInclude(NON_DEFAULT)
public class ErrorObject implements Jsonable {

  private String id;

  @Valid
  private Map<String, LinkObject> links = new LinkedHashMap<>();

  private String status;

  private String code;

  private String title;

  private String detail;

  private SourceObject source;

  @Valid
  private Object meta;

  /**
   * Returns a unique identifier for this particular occurrence of the problem.
   * 
   * @return a unique identifier
   */
  public String getId() {
    return id;
  }

  /**
   * Sets a unique identifier for this particular occurrence of the problem.
   * 
   * @param id
   *          a unique identifier
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * A chaining method for {@link #setId}.
   * 
   * @param id
   *          a unique identifier
   * @return this {@link ErrorObject}
   */
  public ErrorObject withId(String id) {
    setId(id);
    return this;
  }

  /**
   * Returns a map of link names and {@link LinkObject}s.
   * 
   * @return a map of link names and {@link LinkObject}s.
   */
  public Map<String, LinkObject> getLinks() {
    return links;
  }

  /**
   * Sets a map of link names and {@link LinkObject}s.
   * 
   * @param links
   *          a map of link names and {@link LinkObject}s
   */
  public void setLinks(Map<String, LinkObject> links) {
    this.links = links;
  }

  /**
   * A chaining method for {@link #setLinks}.
   * 
   * @param links
   *          a map of link names and {@link LinkObject}s
   * @return this {@link ErrorObject}
   */
  public ErrorObject withLinks(Map<String, LinkObject> links) {
    setLinks(links);
    return this;
  }

  /**
   * Returns the HTTP status code applicable to this problem, expressed as a
   * string value.
   * 
   * @return the HTTP status code
   */
  public String getStatus() {
    return status;
  }

  /**
   * Sets the HTTP status code applicable to this problem, expressed as a string
   * value.
   * 
   * @param status
   *          the HTTP status code
   */
  public void setStatus(String status) {
    this.status = status;
  }

  /**
   * A chaining method for {@link #setStatus}.
   * 
   * @param status
   *          the HTTP status code
   * @return this {@link ErrorObject}
   */
  public ErrorObject withStatus(String status) {
    setStatus(status);
    return this;
  }

  /**
   * Returns an application-specific error code, expressed as a string value.
   * 
   * @return an application-specific error code
   */
  public String getCode() {
    return code;
  }

  /**
   * Sets an application-specific error code, expressed as a string value.
   * 
   * @param code
   *          an application-specific error code
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * A chaining method for {@link #setCode}.
   * 
   * @param code
   *          an application-specific error code
   * @return this {@link ErrorObject}
   */
  public ErrorObject withCode(String code) {
    setCode(code);
    return this;
  }

  /**
   * Returns a short, human-readable summary of the problem that SHOULD NOT
   * change from occurrence to occurrence of the problem, except for purposes of
   * localization.
   * 
   * @return a short, human-readable summary of the problem
   */
  public String getTitle() {
    return title;
  }

  /**
   * Returns a short, human-readable summary of the problem that SHOULD NOT
   * change from occurrence to occurrence of the problem, except for purposes of
   * localization.
   * 
   * @param title
   *          a short, human-readable summary of the problem
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * A chaining method for {@link #setTitle}.
   * 
   * @param title
   *          a short, human-readable summary of the problem
   * @return this {@link ErrorObject}
   */
  public ErrorObject withTitle(String title) {
    setTitle(title);
    return this;
  }

  /**
   * Returns a human-readable explanation specific to this occurrence of the
   * problem.
   * 
   * @return a human-readable explanation specific to this occurrence of the
   *         problem.
   */
  public String getDetail() {
    return detail;
  }

  /**
   * Sets a human-readable explanation specific to this occurrence of the
   * problem.
   * 
   * @param detail
   *          a human-readable explanation specific to this occurrence of the
   *          problem.
   */
  public void setDetail(String detail) {
    this.detail = detail;
  }

  /**
   * A chaining method for {@link setDetail}.
   * 
   * @param detail
   *          a human-readable explanation specific to this occurrence of the
   *          problem.
   * @return this {@link ErrorObject}
   */
  public ErrorObject withDetail(String detail) {
    setDetail(detail);
    return this;
  }

  /**
   * Returns a {@link SourceObject} containing references to the source of the
   * error.
   * 
   * @return a {@link SourceObject}
   */
  public SourceObject getSource() {
    return source;
  }

  /**
   * Sets a {@link SourceObject} containing references to the source of the
   * error.
   * 
   * @param source
   *          a {@link SourceObject}
   */
  public void setSource(SourceObject source) {
    this.source = source;
  }

  /**
   * A chaining method for {@link #setSource}.
   * 
   * @param source
   *          a {@link SourceObject}
   * @return this {@link ErrorObject}
   */
  public ErrorObject withSource(SourceObject source) {
    setSource(source);
    return this;
  }

  /**
   * Returns a meta object containing non-standard meta-information about the
   * error.
   * 
   * @return a meta object
   */
  public Object getMeta() {
    return meta;
  }

  /**
   * Sets a meta object containing non-standard meta-information about the
   * error.
   * 
   * @param meta
   *          a meta object
   */
  public void setMeta(Object meta) {
    this.meta = meta;
  }

  /**
   * A chaining method for {@link #setMeta}.
   * 
   * @param meta
   *          a meta object
   * @return this {@link ErrorObject}
   */
  public ErrorObject withMeta(Object meta) {
    setMeta(meta);
    return this;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof ErrorObject)) return false;
    ErrorObject castOther = (ErrorObject) other;
    return Objects.equals(id, castOther.id)
        && Objects.equals(links, castOther.links)
        && Objects.equals(status, castOther.status)
        && Objects.equals(code, castOther.code)
        && Objects.equals(title, castOther.title)
        && Objects.equals(detail, castOther.detail)
        && Objects.equals(source, castOther.source)
        && Objects.equals(meta, castOther.meta);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, links, status, code, title, detail, source, meta);
  }

  @Override
  public String toString() {
    return toJson();
  }

  @Override
  public String toJson() {
    String json = null;
    try {
      json = JapisonFactory.getObjectMapper().writeValueAsString(this);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

}
