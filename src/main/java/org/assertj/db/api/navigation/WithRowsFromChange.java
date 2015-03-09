/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2014 the original author or authors.
 */
package org.assertj.db.api.navigation;

import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangeRowAssert;

/**
 * Interface that represents a assert with {@link org.assertj.db.type.Row}.
 *
 * @author Régis Pouiller
 *
 * @param <R> The class of the equivalent row assert (an sub-class of {@link org.assertj.db.api.navigation.RowAssert}).
 */
public interface WithRowsFromChange<R extends RowAssert> {

  /**
   * Returns the assert on the row at start point.
   *
   * @return The assert on the row at start point.
   */
  public R rowAtStartPoint();

  /**
   * Returns the assert on the row at end point.
   *
   * @return The assert on the row at end point.
   */
  public R rowAtEndPoint();
}
