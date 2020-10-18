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
package com.github.wnameless.jsonapi.util;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class UnpackableList<E> extends AbstractList<E> {

  private final List<E> list = new ArrayList<>();

  private E element;
  private boolean singular = false;

  public boolean isSingular() {
    return singular;
  }

  public void addSingular(E element) {
    this.element = element;
    singular = true;
  }

  public void removeSingular() {
    element = null;
    singular = false;
  }

  public E getSingular() {
    return element;
  }

  @Override
  public E get(int index) {
    return list.get(index);
  }

  @Override
  public int size() {
    return list.size();
  }

  @Override
  public E set(int index, E element) {
    return list.set(index, element);
  }

  @Override
  public void add(int index, E element) {
    list.add(index, element);
  }

  @Override
  public E remove(int index) {
    return list.remove(index);
  }

}
