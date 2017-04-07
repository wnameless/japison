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

import static com.github.wnameless.jsonapi.annotation.AnnotatedValueType.ID;
import static com.github.wnameless.jsonapi.annotation.AnnotatedValueType.TYPE;
import static com.google.common.collect.Maps.newEnumMap;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.github.wnameless.jsonapi.annotation.AnnotatedValueType;
import com.github.wnameless.jsonapi.annotation.JsonApiId;
import com.github.wnameless.jsonapi.annotation.JsonApiType;

public final class JsonApiUtils {

  private JsonApiUtils() {}

  public static EnumMap<AnnotatedValueType, String> getAllAnnotatedValues(
      Object obj) {
    EnumMap<AnnotatedValueType, String> annotatedValues =
        newEnumMap(AnnotatedValueType.class);

    if (obj != null) {
      JsonApiType jsonApiType = obj.getClass().getAnnotation(JsonApiType.class);
      annotatedValues.put(TYPE, jsonApiType != null ? jsonApiType.value()
          : obj.getClass().getSimpleName());
    }
    if (obj != null) {
      Field[] idFields =
          FieldUtils.getFieldsWithAnnotation(obj.getClass(), JsonApiId.class);
      if (idFields.length != 0) {
        Field idField = idFields[0];
        JsonApiId jsonApiId = idField.getAnnotation(JsonApiId.class);
        idField.setAccessible(true);
        try {
          Object idObj = idField.get(obj);
          if (jsonApiId.getterName().isEmpty()) {
            annotatedValues.put(ID, stringify(idObj));
          } else {
            Object idVal =
                MethodUtils.invokeMethod(idObj, jsonApiId.getterName());
            annotatedValues.put(ID, stringify(idVal));
          }
        } catch (Exception e) {
          Logger.getLogger(JsonApiUtils.class.getName()).log(Level.SEVERE, null,
              e);
        }
      }
    }

    return annotatedValues;
  }

  public static String stringify(Object obj) {
    if (obj == null) return null;
    if (obj instanceof String) return (String) obj;
    if (obj instanceof CharSequence)
      return new StringBuilder((CharSequence) obj).toString();

    return obj.toString();
  }

}
