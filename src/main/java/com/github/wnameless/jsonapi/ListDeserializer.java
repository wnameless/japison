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

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ListDeserializer<T> extends StdDeserializer<List<T>> {

  private static final long serialVersionUID = 1L;

  public ListDeserializer() {
    super(List.class);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    JsonNode node = p.getCodec().readTree(p);
    if (node.isNull()) return null;
    if (!node.isArray())
      return (List<T>) ObjectMapperFactory.getObjectMapper()
          .readValue("[" + node.asText() + "]", List.class);
    else
      return (List<T>) ObjectMapperFactory.getObjectMapper()
          .readValue(node.asText(), List.class);
  }

}
