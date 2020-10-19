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
package com.github.wnameless.json.japison.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

public final class PrimaryData<E> implements List<E> {

  private final List<E> collection = new ArrayList<>();

  private E resource;
  private boolean singular = false;

  public boolean isSingular() {
    return singular;
  }

  public void setSingular(E element) {
    this.resource = element;
    singular = true;
  }

  public void removeSingular() {
    resource = null;
    singular = false;
  }

  public E getSingular() {
    return resource;
  }

  @Override
  public int size() {
    return collection.size();
  }

  @Override
  public boolean isEmpty() {
    return collection.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return collection.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return collection.iterator();
  }

  @Override
  public Object[] toArray() {
    return collection.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return collection.toArray(a);
  }

  @Override
  public boolean add(E e) {
    return collection.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return collection.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return collection.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return collection.addAll(c);
  }

  @Override
  public boolean addAll(int index, Collection<? extends E> c) {
    return collection.addAll(index, c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return collection.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return collection.retainAll(c);
  }

  @Override
  public void clear() {
    collection.clear();
  }

  @Override
  public E get(int index) {
    return collection.get(index);
  }

  @Override
  public E set(int index, E element) {
    return collection.set(index, element);
  }

  @Override
  public void add(int index, E element) {
    collection.add(index, element);
  }

  @Override
  public E remove(int index) {
    return collection.remove(index);
  }

  @Override
  public int indexOf(Object o) {
    return collection.indexOf(o);
  }

  @Override
  public int lastIndexOf(Object o) {
    return collection.lastIndexOf(o);
  }

  @Override
  public ListIterator<E> listIterator() {
    return collection.listIterator();
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    return collection.listIterator();
  }

  @Override
  public List<E> subList(int fromIndex, int toIndex) {
    return collection.subList(fromIndex, toIndex);
  }

  @Override
  public int hashCode() {
    if (singular) {
      return Objects.hash(resource);
    } else {
      return Objects.hash(collection);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof PrimaryData)) return false;
    PrimaryData<?> other = (PrimaryData<?>) obj;
    if (singular) {
      return Objects.equals(resource, other.resource);
    } else {
      return Objects.equals(collection, other.collection);
    }
  }

  @Override
  public String toString() {
    if (singular) {
      return new ToStringBuilder(this).append("resource", resource).toString();
    } else {
      return new ToStringBuilder(this).append("collection", collection)
          .toString();
    }
  }

}
