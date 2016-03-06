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
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.db.navigation;

import org.assertj.core.api.Assertions;
import org.assertj.db.api.ChangeAssert;
import org.assertj.db.api.ChangeColumnAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.display.ChangeColumnDisplay;
import org.assertj.db.display.ChangeDisplay;
import org.assertj.db.display.ChangesDisplay;
import org.assertj.db.exception.AssertJDBException;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Value;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.display.Displaying.display;
import static org.junit.Assert.fail;

/**
 * Tests on {@link org.assertj.db.navigation.ToColumnFromChange} class :
 * {@link org.assertj.db.navigation.ToColumnFromChange#columnAmongTheModifiedOnes()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToColumnFromChange_ColumnAmongTheModifiedOnes_Integer_Test extends AbstractTest {

  /**
   * This method tests the {@code columnAmongTheModifiedOnes} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_among_the_modified_ones_with_index_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeAssert.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldIndex = PositionWithColumnsChange.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnAssert.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnAssert.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnAssert.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);
    ChangeAssert changeAssert = changesAssert.change(6);
    PositionWithColumnsChange position = (PositionWithColumnsChange) fieldPosition.get(changeAssert);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssert0 = changeAssert.columnAmongTheModifiedOnes(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssert1 = changeAssert.columnAmongTheModifiedOnes(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssert2 = changeAssert.columnAmongTheModifiedOnes(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssert3 = changeAssert.columnAmongTheModifiedOnes(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssert4 = changeAssert.columnAmongTheModifiedOnes(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      changeAssert.columnAmongTheModifiedOnes(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits of the modified columns [0, 5[");
    }
    try {
      changeAssert.columnAmongTheModifiedOnes(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits of the modified columns [0, 5[");
    }
    ChangeColumnAssert changeColumnAssertAgain0 = changeAssert.column(0);
    Assertions.assertThat(changeColumnAssert0).isSameAs(changeColumnAssertAgain0);

    ChangesAssert changesAssertBis = assertThat(changes);
    ChangeAssert changeAssertBis = changesAssertBis.change(6);
    PositionWithColumnsChange positionBis = (PositionWithColumnsChange) fieldPosition.get(changeAssertBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnAssert changeColumnAssertBis0 = changeAssertBis.columnAmongTheModifiedOnes(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnAssert changeColumnAssertBis1 = changeColumnAssertBis0.columnAmongTheModifiedOnes(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnAssert changeColumnAssertBis2 = changeColumnAssertBis1.columnAmongTheModifiedOnes(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnAssert changeColumnAssertBis3 = changeColumnAssertBis2.columnAmongTheModifiedOnes(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeColumnAssert changeColumnAssertBis4 = changeColumnAssertBis3.columnAmongTheModifiedOnes(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      changeColumnAssertBis4.columnAmongTheModifiedOnes(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits of the modified columns [0, 5[");
    }
    try {
      changeColumnAssertBis4.columnAmongTheModifiedOnes(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits of the modified columns [0, 5[");
    }
    ChangeColumnAssert changeColumnAssertBisAgain0 = changeColumnAssertBis4.column(0);
    Assertions.assertThat(changeColumnAssertBis0).isSameAs(changeColumnAssertBisAgain0);

    Assertions.assertThat(fieldColumnName.get(changeColumnAssert0)).isEqualTo(fieldColumnName.get(changeColumnAssertBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert1)).isEqualTo(fieldColumnName.get(changeColumnAssertBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert2)).isEqualTo(fieldColumnName.get(changeColumnAssertBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert3)).isEqualTo(
            fieldColumnName.get(changeColumnAssertBis3)).isEqualTo(
            "BIRTH");
    Assertions.assertThat(fieldColumnName.get(changeColumnAssert4)).isEqualTo(
            fieldColumnName.get(changeColumnAssertBis4)).isEqualTo(
            "ACTOR_IMDB");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert0)).getValue()).isEqualTo(
            ((Value) fieldValueAtStartPoint.get(changeColumnAssertBis0)).getValue()).isEqualTo(
            new BigDecimal("3"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert1)).getValue()).
    isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis1)).getValue()).
    isEqualTo("Worthington");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert2)).getValue()).
    isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis2)).getValue()).
    isEqualTo("Sam");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert3)).getValue()).
    isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis3)).getValue()).
    isEqualTo(
            Date.valueOf("1976-08-02"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert4)).getValue()).
    isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnAssertBis4)).getValue()).
    isEqualTo(
            UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));


    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert4)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssertBis4)).getValue()).isNull();

    ChangeColumnAssert changeColumnAssert = assertThat(changes).change(3).columnAmongTheModifiedOnes(0);
    try {
      changeColumnAssert.columnAmongTheModifiedOnes(1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits of the modified columns [0, 1[");
    }
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnAssert)).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnAssert)).getValue()).isEqualTo(
            "Susan Alexandra");
  }

  /**
   * This method tests the {@code columnAmongTheModifiedOnes} navigation method.
   */
  @Test
  @NeedReload
  public void test_column_among_the_modified_ones_with_index_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeDisplay.class.getDeclaredField("columnPosition");
    fieldPosition.setAccessible(true);
    Field fieldIndex = PositionWithColumnsChange.class.getDeclaredField("nextIndex");
    fieldIndex.setAccessible(true);
    Field fieldColumnName = ChangeColumnDisplay.class.getDeclaredField("columnName");
    fieldColumnName.setAccessible(true);
    Field fieldValueAtStartPoint = ChangeColumnDisplay.class.getDeclaredField("valueAtStartPoint");
    fieldValueAtStartPoint.setAccessible(true);
    Field fieldValueAtEndPoint = ChangeColumnDisplay.class.getDeclaredField("valueAtEndPoint");
    fieldValueAtEndPoint.setAccessible(true);

    ChangesDisplay changesDisplay = display(changes);
    ChangeDisplay changeDisplay = changesDisplay.change(6);
    PositionWithColumnsChange position = (PositionWithColumnsChange) fieldPosition.get(changeDisplay);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(0);
    ChangeColumnDisplay changeColumnDisplay0 = changeDisplay.columnAmongTheModifiedOnes(0);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(1);
    ChangeColumnDisplay changeColumnDisplay1 = changeDisplay.columnAmongTheModifiedOnes(1);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(2);
    ChangeColumnDisplay changeColumnDisplay2 = changeDisplay.columnAmongTheModifiedOnes(2);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(3);
    ChangeColumnDisplay changeColumnDisplay3 = changeDisplay.columnAmongTheModifiedOnes(3);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(4);
    ChangeColumnDisplay changeColumnDisplay4 = changeDisplay.columnAmongTheModifiedOnes(4);
    Assertions.assertThat(fieldIndex.get(position)).isEqualTo(5);
    try {
      changeDisplay.columnAmongTheModifiedOnes(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits of the modified columns [0, 5[");
    }
    try {
      changeDisplay.columnAmongTheModifiedOnes(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits of the modified columns [0, 5[");
    }
    ChangeColumnDisplay changeColumnDisplayAgain0 = changeDisplay.column(0);
    Assertions.assertThat(changeColumnDisplay0).isSameAs(changeColumnDisplayAgain0);

    ChangesDisplay changesDisplayBis = display(changes);
    ChangeDisplay changeDisplayBis = changesDisplayBis.change(6);
    PositionWithColumnsChange positionBis = (PositionWithColumnsChange) fieldPosition.get(changeDisplayBis);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(0);
    ChangeColumnDisplay changeColumnDisplayBis0 = changeDisplayBis.columnAmongTheModifiedOnes(0);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(1);
    ChangeColumnDisplay changeColumnDisplayBis1 = changeColumnDisplayBis0.columnAmongTheModifiedOnes(1);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(2);
    ChangeColumnDisplay changeColumnDisplayBis2 = changeColumnDisplayBis1.columnAmongTheModifiedOnes(2);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(3);
    ChangeColumnDisplay changeColumnDisplayBis3 = changeColumnDisplayBis2.columnAmongTheModifiedOnes(3);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(4);
    ChangeColumnDisplay changeColumnDisplayBis4 = changeColumnDisplayBis3.columnAmongTheModifiedOnes(4);
    Assertions.assertThat(fieldIndex.get(positionBis)).isEqualTo(5);
    try {
      changeColumnDisplayBis4.columnAmongTheModifiedOnes(5);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 5 out of the limits of the modified columns [0, 5[");
    }
    try {
      changeColumnDisplayBis4.columnAmongTheModifiedOnes(-1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index -1 out of the limits of the modified columns [0, 5[");
    }
    ChangeColumnDisplay changeColumnDisplayBisAgain0 = changeColumnDisplayBis4.column(0);
    Assertions.assertThat(changeColumnDisplayBis0).isSameAs(changeColumnDisplayBisAgain0);

    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay0)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis0)).isEqualTo(
            "ID");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay1)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis1)).isEqualTo(
            "NAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay2)).isEqualTo(fieldColumnName.get(changeColumnDisplayBis2)).isEqualTo(
            "FIRSTNAME");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay3)).isEqualTo(
            fieldColumnName.get(changeColumnDisplayBis3)).isEqualTo(
            "BIRTH");
    Assertions.assertThat(fieldColumnName.get(changeColumnDisplay4)).isEqualTo(
            fieldColumnName.get(changeColumnDisplayBis4)).isEqualTo(
            "ACTOR_IMDB");

    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay0)).getValue()).isEqualTo(
            ((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis0)).getValue()).isEqualTo(
            new BigDecimal("3"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay1)).getValue()).
            isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis1)).getValue()).
                      isEqualTo("Worthington");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay2)).getValue()).
            isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis2)).getValue()).
                      isEqualTo("Sam");
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay3)).getValue()).
            isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis3)).getValue()).
                      isEqualTo(
                              Date.valueOf("1976-08-02"));
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay4)).getValue()).
            isEqualTo(((Value) fieldValueAtStartPoint.get(changeColumnDisplayBis4)).getValue()).
                      isEqualTo(
                              UUID.fromString("D735221B-5DE5-4112-AA1E-49090CB75ADA"));


    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay4)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis0)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis1)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis2)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis3)).getValue()).isNull();
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplayBis4)).getValue()).isNull();

    ChangeColumnDisplay changeColumnDisplay = display(changes).change(3).columnAmongTheModifiedOnes(0);
    try {
      changeColumnDisplay.columnAmongTheModifiedOnes(1);
      fail("An exception must be raised");
    } catch (AssertJDBException e) {
      Assertions.assertThat(e.getMessage()).isEqualTo("Index 1 out of the limits of the modified columns [0, 1[");
    }
    Assertions.assertThat(((Value) fieldValueAtStartPoint.get(changeColumnDisplay)).getValue()).isEqualTo("Sigourney");
    Assertions.assertThat(((Value) fieldValueAtEndPoint.get(changeColumnDisplay)).getValue()).isEqualTo(
            "Susan Alexandra");
  }
}
