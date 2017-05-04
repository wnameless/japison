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
package com.github.wnameless.jsonapi.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.jonpeterson.jackson.module.interceptor.JsonInterceptor;

public class SingularDataObjectifyInterceptor implements JsonInterceptor {

  @Override
  public JsonNode intercept(JsonNode jsonNode,
      JsonNodeFactory jsonNodeFactory) {
    if (jsonNode.isObject()) {
      JsonNode dataNode = jsonNode.get("data");
      if (dataNode.isArray() && dataNode.size() == 1) {
        ObjectNode objectNode = (ObjectNode) jsonNode;
        objectNode.replace("data", dataNode.get(0));
      }
    }
    return jsonNode;
  }

}