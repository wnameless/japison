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
package com.github.wnameless.json.japison.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.imifou.jsonschema.module.addon.AddonModule;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationModule;
import com.github.victools.jsonschema.module.javax.validation.JavaxValidationOption;

/**
 * 
 * {@link JapisonFactory} provides a Jackson {@link ObjectMapper} for the entire
 * Japison library to use. Users can set their own instance of
 * {@link ObjectMapper} to this factory, in doing so, the JSON deserialization
 * behavior will be changed globally within this library.
 *
 */
public final class JapisonFactory {

  private static ObjectMapper mapper = new ObjectMapper();
  private static SchemaGenerator schemaGenerator;
  static {
    JavaxValidationModule validationModule = new JavaxValidationModule(
        JavaxValidationOption.INCLUDE_PATTERN_EXPRESSIONS);
    JacksonModule jacksonModule = new JacksonModule();
    AddonModule addonModule = new AddonModule();

    SchemaGeneratorConfigBuilder configBuilder =
        new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2019_09,
            OptionPreset.PLAIN_JSON).with(jacksonModule).with(validationModule)
                .with(addonModule);
    SchemaGeneratorConfig config = configBuilder.build();
    schemaGenerator = new SchemaGenerator(config);
  }

  private JapisonFactory() {}

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

  public static SchemaGenerator getSchemaGenerator() {
    return schemaGenerator;
  }

  public static void setSchemaGenerator(SchemaGenerator schemaGenerator) {
    JapisonFactory.schemaGenerator = schemaGenerator;
  }

}
