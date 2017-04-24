/*
 *
 * Copyright 2016 Wei-Ming Wu
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
package com.github.wnameless.jsonapi.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jonpeterson.jackson.module.interceptor.JsonInterceptorModule;

/**
 * 
 * {@link ObjectMapperFactory} provides a Jackson {@link ObjectMapper} for the
 * entire Japison library to use. Users can set their own instance of
 * {@link ObjectMapper} to this factory, in doing so, the JSON deserialization
 * behavior will be changed globally within this library.
 *
 */
public final class ObjectMapperFactory {

  private static ObjectMapper mapper;
  static {
    mapper = new ObjectMapper();
    mapper.registerModule(new JsonInterceptorModule());
  }

  private ObjectMapperFactory() {}

  /**
   * Returns the {@link ObjectMapper} for Japison library to use.
   * 
   * @return a {@link ObjectMapper}
   */
  public static ObjectMapper getObjectMapper() {
    return mapper;
  }

  /**
   * Sets the {@link ObjectMapper} for Japison library to use.
   * 
   * @param objectMapper
   *          a {@link ObjectMapper}
   */
  public static void setObjectMapper(ObjectMapper objectMapper) {
    mapper = objectMapper;
  }

}
