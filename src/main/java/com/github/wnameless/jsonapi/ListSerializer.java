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

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ListSerializer<T> extends StdSerializer<List<T>> {

  private static final long serialVersionUID = 1L;

  public ListSerializer() {
    super(List.class, true);
  }

  @Override
  public void serialize(List<T> value, JsonGenerator gen,
      SerializerProvider provider) throws IOException {
    if (value != null && value.size() == 1) {
      gen.writeRawValue(ObjectMapperFactory.getObjectMapper()
          .writeValueAsString(value.get(0)));
    } else {
      gen.writeRawValue(
          ObjectMapperFactory.getObjectMapper().writeValueAsString(value));
    }
  }

}
