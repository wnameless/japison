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

import java.util.List;
import java.util.Map;

/**
 * 
 * {@link Document} defines a super type of {@link ResourceDocument},
 * {@link ResourcesDocument} and {@link ErrorsDocument}. <br>
 * <br>
 * {@link Document} is designed by the document in JSON API specification.
 * 
 * @see <a href="http://jsonapi.org/format/#document-structure">JSON API
 *      Specification (v1.0) Document Structure</a>
 *
 * @param <T>
 *          the type of document data
 */
public interface Document<T> {

  /**
   * Returns the document's "primary data".
   * 
   * @return the document's "primary data"
   */
  public T getData();

  /**
   * Sets the document's "primary data".
   * 
   * @param data
   *          the document's "primary data".
   */
  public void setData(T data);

  /**
   * Returns a list of error objects
   * 
   * @return a list of error objects
   */
  public List<ErrorObject> getErrors();

  /**
   * Sets a list of error objects.
   * 
   * @param errors
   *          a list of error objects.
   */
  public void setErrors(List<ErrorObject> errors);

  /**
   * Returns a meta object that contains non-standard meta-information.
   * 
   * @return a meta object
   */
  public Object getMeta();

  /**
   * Sets a meta object that contains non-standard meta-information.
   * 
   * @param meta
   *          a meta object
   */
  public void setMeta(Object meta);

  /**
   * Returns a {@link JsonApiObject} describing the server's implementation.
   * 
   * @return a {@link JsonApiObject} describing the server's implementation
   */
  public JsonApiObject getJsonapi();

  /**
   * Sets a {@link JsonApiObject} describing the server's implementation.
   * 
   * @param jsonapi
   *          a {@link JsonApiObject} describing the server's implementation
   */
  public void setJsonapi(JsonApiObject jsonapi);

  /**
   * Returns {@link LinkObject}s related to the primary data.
   * 
   * @return {@link LinkObject}s
   */
  public Map<String, LinkObject> getLinks();

  /**
   * Sets {@link LinkObject}s related to the primary data.
   * 
   * @param links
   *          {@link LinkObject}s
   */
  public void setLinks(Map<String, LinkObject> links);

  /**
   * Returns a list of {@link ResourceObject}s that are related to the primary
   * data and/or each other.
   * 
   * @return a list of {@link ResourceObject}s
   */
  public List<ResourceObject<?>> getIncluded();

  /**
   * Sets a list of {@link ResourceObject}s that are related to the primary data
   * and/or each other.
   * 
   * @param included
   *          a list of {@link ResourceObject}s
   */
  public void setIncluded(List<ResourceObject<?>> included);

}
