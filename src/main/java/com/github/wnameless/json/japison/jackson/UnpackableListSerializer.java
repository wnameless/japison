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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.github.wnameless.json.japison.ResourceObject;
import com.github.wnameless.json.japison.util.UnpackableList;

public class UnpackableListSerializer
    extends StdSerializer<UnpackableList<ResourceObject<?>>> {

  private static final long serialVersionUID = 1L;

  public UnpackableListSerializer() {
    this(null);
  }

  public UnpackableListSerializer(Class<UnpackableList<ResourceObject<?>>> t) {
    super(t);
  }

  @Override
  public void serialize(UnpackableList<ResourceObject<?>> value,
      JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
    if (value == null) jgen.writeNull();

    if (value.isSingular()) {
      jgen.writeObject(value.getSingular());
    } else {
      jgen.writeStartArray();
      for (ResourceObject<?> val : value) {
        jgen.writeObject(val);
      }
      jgen.writeEndArray();
    }
  }

}
