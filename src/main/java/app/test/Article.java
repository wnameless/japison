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
package app.test;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.github.imifou.jsonschema.module.addon.annotation.JsonSchema;
import com.github.wnameless.json.japison.util.JsonSchemaGenerable;
import com.github.wnameless.spring.boot.up.web.RestfulItem;

import lombok.Data;

@JsonAppend(attrs = { @JsonAppend.Attr(propName = "title", value = "title") })
@Data
public class Article implements RestfulItem<String>, JsonSchemaGenerable {

  @JsonIgnore
  @Id
  String id = "AAA";

  @JsonSchema(title = "TITLE")
  String title = "JSON:API paints my bikeshed!";
  String body = "The shortest article. Ever.";
  String created = "2015-05-22T14:56:29.000Z";
  String updated = "2015-05-22T14:56:28.000Z";
  List<Paragraph> paragraph = Arrays.asList(new Paragraph());

  @Value("${a.b.c}")
  int abc;

  @Override
  public String getIndexPath() {
    return "/articles";
  }

}
