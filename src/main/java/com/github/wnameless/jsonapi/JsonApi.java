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

import static com.github.wnameless.jsonapi.annotation.AnnotatedValueType.ID;
import static com.github.wnameless.jsonapi.annotation.AnnotatedValueType.TYPE;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newEnumMap;
import static com.google.common.collect.Sets.newHashSet;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.github.wnameless.jsonapi.annotation.AnnotatedValueType;
import com.github.wnameless.jsonapi.annotation.JsonApiId;
import com.github.wnameless.jsonapi.annotation.JsonApiType;

/**
 * 
 * {@link JsonApi} is a helper class which provides convenient methods to build
 * up JSON API requests or responses.
 *
 */
public final class JsonApi {

  public static final String MEDIA_TYPE = "application/vnd.api+json";

  private JsonApi() {}

  /**
   * Creates a {@link ResourceDocument}.
   * 
   * @param <T>
   *          the type of attributes
   * @return a {@link ResourceDocument}
   */
  public static <T> ResourceDocument<T> resourceDocument() {
    return new ResourceDocument<T>();
  }

  /**
   * Creates a {@link ResourceDocument} by given data attributes with its type
   * and id.
   * 
   * @param <T>
   *          the type of attributes
   * @param attributes
   *          the data object
   * @param type
   *          the type of data
   * @param id
   *          the id of data
   * @return a {@link ResourceDocument}
   */
  public static <T> ResourceDocument<T> resourceDocument(
      Collection<T> attributes) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    List<ResourceObject<T>> resources = newArrayList();
    for (T attr : attributes) {
      ResourceObject<T> resource = resource(attr);
      resources.add(resource);
    }
    document.setData(resources);
    return document;
  }

  public static <T> ResourceDocument<T> resourceDocument(
      Collection<T> attributes, ResourceSetting<T> setting) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    List<ResourceObject<T>> resources = newArrayList();
    for (T attr : attributes) {
      ResourceObject<T> resource = resource(attr);
      if (setting != null) setting.apply(resource);
      resources.add(resource);
    }
    document.setData(resources);
    return document;
  }

  public static <T> ResourceDocument<T> resourceDocument(T attributes) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    List<ResourceObject<T>> resources = newArrayList();
    ResourceObject<T> resource = resource(attributes);
    resources.add(resource);
    document.setData(resources);
    return document;
  }

  public static <T> ResourceDocument<T> resourceDocument(T attributes,
      ResourceSetting<T> setting) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    List<ResourceObject<T>> resources = newArrayList();
    ResourceObject<T> resource = resource(attributes);
    if (setting != null) setting.apply(resource);
    resources.add(resource);
    document.setData(resources);
    return document;
  }

  /**
   * Creates a {@link ResourceDocument} by given data attributes with its type.
   * 
   * @param <T>
   *          the type of attributes
   * @param attributes
   *          the data object
   * @return a {@link ResourceDocument}
   */
  @SafeVarargs
  public static <T> ResourceDocument<T> resourceDocument(T... attributes) {
    ResourceDocument<T> document = new ResourceDocument<T>();
    List<ResourceObject<T>> resources = newArrayList();
    for (T attr : attributes) {
      ResourceObject<T> resource = resource(attr);
      resources.add(resource);
    }
    document.setData(resources);
    return document;
  }

  /**
   * Creates a {@link ErrorsDocument}.
   * 
   * @return a {@link ErrorsDocument}
   */
  public static ErrorsDocument errorsDocument() {
    return new ErrorsDocument();
  }

  /**
   * Creates a {@link ErrorObject}.
   * 
   * @return a {@link ErrorObject}
   */
  public static ErrorObject error() {
    return new ErrorObject();
  }

  /**
   * Creates a {@link ResourceObject}.
   * 
   * @param <T>
   *          the type of attributes
   * @return a {@link ResourceObject}
   */
  public static <T> ResourceObject<T> resource() {
    return new ResourceObject<T>();
  }

  /**
   * Creates a {@link ResourceObject} by given data attributes.
   * 
   * @param <T>
   *          the type of attributes
   * @param attributes
   *          the data object
   * @return a {@link ResourceObject}
   */
  public static <T> ResourceObject<T> resource(T attributes) {
    return new ResourceObject<T>().withAttributes(attributes);
  }

  public static ResourceIdentifierObject resourceIdentifier() {
    return new ResourceIdentifierObject();
  }

  public static ResourceIdentifierObject resourceIdentifier(String type,
      String id) {
    return new ResourceIdentifierObject().withType(type).withId(id);
  }

  /**
   * Creates a {@link RelationshipObject}.
   * 
   * @param <T>
   *          the type of attributes
   * @return a {@link RelationshipObject}
   */
  public static RelationshipObject relationship() {
    return new RelationshipObject();
  }

  /**
   * Creates a {@link RelationshipObject} by given {@link ResourceIdentifier}s.
   * 
   * @param <T>
   *          the type of attributes
   * @param data
   *          an array of {@link ResourceIdentifier}s
   * @return a {@link RelationshipObject}
   */
  public static <T> RelationshipObject relationship(
      Collection<ResourceIdentifier> data) {
    return new RelationshipObject().withData(newArrayList(data));
  }

  /**
   * Creates a {@link RelationshipObject} by given {@link ResourceIdentifier}s.
   * 
   * @param <T>
   *          the type of attributes
   * @param data
   *          an array of {@link ResourceIdentifier}s
   * @return a {@link RelationshipObject}
   */
  public static <T> RelationshipObject relationship(
      ResourceIdentifier... data) {
    return new RelationshipObject().withData(newArrayList(data));
  }

  /**
   * Creates a {@link LinkObject}.
   * 
   * @return a {@link LinkObject}
   */
  public static LinkObject link() {
    return new LinkObject();
  }

  /**
   * Creates a {@link SourceObject}.
   * 
   * @return a {@link SourceObject}
   */
  public static SourceObject source() {
    return new SourceObject();
  }

  /**
   * Creates a {@link JsonApiObject}.
   * 
   * @return a {@link JsonApiObject}
   */
  public static JsonApiObject jsonApi() {
    return new JsonApiObject();
  }

  /**
   * 
   * {@link JsonApi.Utils} provides methods to access included resources
   * information within JSON API objects in a type-safe way.
   * 
   * @author Wei-Ming Wu
   *
   */
  public static final class Utils {

    private Utils() {}

    /**
     * Returns all types in the included resources of given {@link Document}.
     * 
     * @param document
     *          any {@link Document}
     * @return a set of types
     */
    public Set<Class<?>> listAllIncludedTypes(Document<?> document) {
      Set<Class<?>> types = newHashSet();

      if (document.getIncluded() != null) {
        for (ResourceObject<?> i : document.getIncluded()) {
          if (i != null) types.add(i.getAttributes().getClass());
        }
      }

      return types;
    }

    /**
     * Finds all resources of certain type in the included of given
     * {@link Document}.
     * 
     * @param klass
     *          target type
     * @param document
     *          any {@link Document}
     * @return a list of resources of given type
     */
    public <T> List<ResourceObject<T>> findIncludedByType(Class<T> klass,
        Document<?> document) {
      List<ResourceObject<T>> typedIncluded = newArrayList();
      List<ResourceObject<?>> included = document.getIncluded();

      if (included != null) {
        for (ResourceObject<?> i : included) {
          if (i != null
              && klass.isAssignableFrom(i.getAttributes().getClass())) {
            @SuppressWarnings("unchecked")
            ResourceObject<T> ro = (ResourceObject<T>) i;
            typedIncluded.add(ro);
          }
        }
      }

      return typedIncluded;
    }

    public static EnumMap<AnnotatedValueType, String> getAllAnnotatedValues(
        Object obj) {
      EnumMap<AnnotatedValueType, String> annotatedValues =
          newEnumMap(AnnotatedValueType.class);

      if (obj != null) {
        JsonApiType jsonApiType =
            obj.getClass().getAnnotation(JsonApiType.class);
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
            Logger.getLogger(JsonApi.Utils.class.getName()).log(Level.SEVERE,
                null, e);
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

    public static <T> T getTypedMetaObject(Class<T> klass, Object meta) {
      return getTypedMetaObject(klass, meta, false);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getTypedMetaObject(Class<T> klass, Object meta,
        boolean forcedConverting) {
      if (meta == null) return null;

      T typedObj;
      try {
        typedObj = klass.newInstance();
      } catch (InstantiationException e) {
        throw new RuntimeException(e);
      } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
      }

      BeanMap typedBean = new BeanMap(typedObj);
      BeanMap metaBean = new BeanMap(meta);

      if (!forcedConverting
          && !typedBean.keySet().containsAll(metaBean.keySet())) {
        throw new RuntimeException("Unmatched properties found");
      }

      typedBean.putAllWriteable(metaBean);

      return (T) typedBean.getBean();
    }

  }

}
