/*
 *
 * Copyright 2020 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.github.wnameless.json.japison.jackson;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.github.wnameless.json.japison.util.UnpackableList;

public class UnpackableListDeserializer<T> extends
    JsonDeserializer<UnpackableList<T>> implements ContextualDeserializer {

  private JavaType elementType;

  public UnpackableListDeserializer() {}

  public UnpackableListDeserializer(JavaType elementType) {
    this.elementType = elementType;
  }

  @Override
  public UnpackableListDeserializer<T> createContextual(
      DeserializationContext ctxt, BeanProperty property)
      throws JsonMappingException {
    return new UnpackableListDeserializer<T>(
        property.getType().containedType(0));
  }

  @Override
  public UnpackableList<T> deserialize(JsonParser p,
      DeserializationContext ctxt) throws IOException, JsonProcessingException {
    UnpackableList<T> list = new UnpackableList<>();

    ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
    JsonNode node = p.readValueAsTree();
    if (node != null && node.isArray()) {
      @SuppressWarnings("unchecked")
      List<T> itemList = (List<T>) mapper.convertValue(node, mapper
          .getTypeFactory().constructCollectionType(List.class, elementType));
      list.addAll(itemList);
    } else {
      if (node == null) {
        T item = null;
        list.addSingular(item);
      } else {
        T item = mapper.convertValue(node, elementType);
        list.addSingular(item);
      }
    }
    return list;
  }

}
