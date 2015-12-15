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
 * @see JSON API Specification (v1.0)
 *      <a href="http://jsonapi.org/format/#document-structure">Document
 *      Structure</a>
 *
 * @param <T>
 *          the type of document data
 */
public interface Document<T> {

  public T getData();

  public void setData(T data);

  public List<ErrorObject> getErrors();

  public void setErrors(List<ErrorObject> errors);

  public Object getMeta();

  public void setMeta(Object meta);

  public JsonApiObject getJsonapi();

  public void setJsonapi(JsonApiObject jsonapi);

  public Map<String, LinkObject> getLinks();

  public void setLinks(Map<String, LinkObject> links);

  public List<ResourceObject<?>> getIncluded();

  public void setIncluded(List<ResourceObject<?>> included);

}
