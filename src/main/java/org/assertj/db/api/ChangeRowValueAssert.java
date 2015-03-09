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
package org.assertj.db.api;

import org.assertj.db.api.navigation.ValueAssert;
import org.assertj.db.api.navigation.WithValues;
import org.assertj.db.api.navigation.WithValuesFromRow;

/**
 * Assertion methods about a value of a {@code Row} of a {@code Change}.
 *
 * @author Régis Pouiller
 *
 */
public class ChangeRowValueAssert extends AbstractAssertWithValues<ChangeRowValueAssert, ChangeRowAssert>
        implements WithValues<ChangeRowValueAssert>, WithValuesFromRow<ChangeRowValueAssert> {

  /**
   * Constructor.
   *
   * @param originalAssert The original assert.
   * @param value The value on which are the assertions.
   */
  ChangeRowValueAssert(ChangeRowAssert originalAssert, Object value) {
    super(ChangeRowValueAssert.class, originalAssert, value);
  }

  /**
   * Returns assertion methods on the next value in the list of values.
   *
   * @return An object to make assertions on the next value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeRowValueAssert value() {
    return origin.value();
  }

  /**
   * Returns assertion methods on the value at the {@code index} in parameter.
   *
   * @param index The index corresponding to the value.
   * @return An object to make assertions on the value.
   * @throws org.assertj.db.exception.AssertJDBException If the {@code index} is out of the bounds.
   */
  public ChangeRowValueAssert value(int index) {
    return origin.value(index);
  }

  /**
   * Returns assertion methods on the value corresponding to the column name in parameter.
   *
   * @param columnName The column name.
   * @return An object to make assertions on the value.
   * @throws NullPointerException If the column name in parameter is null.
   * @throws org.assertj.db.exception.AssertJDBException If there is no column with this name.
   */
  public ChangeRowValueAssert value(String columnName) {
    return origin.value(columnName);
  }
}
