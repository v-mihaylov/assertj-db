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
import org.assertj.db.api.ChangeRowAssert;
import org.assertj.db.api.ChangesAssert;
import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.display.ChangeDisplay;
import org.assertj.db.display.ChangeRowDisplay;
import org.assertj.db.display.ChangesDisplay;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.display.Displaying.display;

/**
 * Tests on {@link org.assertj.db.navigation.ToRowFromChange} class :
 * {@link org.assertj.db.navigation.ToRowFromChange#rowAtStartPoint()} method.
 *
 * @author Régis Pouiller
 *
 */
public class ToRowFromChange_RowAtStartPoint_Test extends AbstractTest {

  /**
   * This method tests the {@code rowAtStartPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_row_at_start_point_with_assertions() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeAssert.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRowAssert = PositionWithPoints.class.getDeclaredField("instanceAtStartPoint");
    fieldRowAssert.setAccessible(true);
    Field fieldRowFromChange = Change.class.getDeclaredField("rowAtStartPoint");
    fieldRowFromChange.setAccessible(true);
    Field fieldRowFromAssert = ChangeRowAssert.class.getDeclaredField("row");
    fieldRowFromAssert.setAccessible(true);

    ChangesAssert changesAssert = assertThat(changes);

    ChangeAssert changeCreationAssert = changesAssert.change();
    PositionWithPoints positionCreation = (PositionWithPoints) fieldPosition.get(changeCreationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNull();
    ChangeRowAssert changeCreationRowAssert = changeCreationAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionCreation)).isNotNull();
    ChangeRowAssert changeCreationRowAssertBis = changeCreationRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeCreationRowAssert).isSameAs(changeCreationRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeCreationRowAssert)).isNull();

    ChangeAssert changeModificationAssert = changesAssert.change(3);
    PositionWithPoints positionModification = (PositionWithPoints) fieldPosition.get(changeModificationAssert);
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNull();
    ChangeRowAssert changeModificationRowAssert = changeModificationAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionModification)).isNotNull();
    ChangeRowAssert changeModificationRowAssertBis = changeModificationRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeModificationRowAssert).isSameAs(changeModificationRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeModificationRowAssert)).isEqualTo(
            fieldRowFromChange.get(changes.getChangesList().get(3)));

    ChangeAssert changeDeletionAssert = changesAssert.change(6);
    PositionWithPoints positionDeletion = (PositionWithPoints) fieldPosition.get(changeDeletionAssert);
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNull();
    ChangeRowAssert changeDeletionRowAssert = changeDeletionAssert.rowAtStartPoint();
    Assertions.assertThat(fieldRowAssert.get(positionDeletion)).isNotNull();
    ChangeRowAssert changeDeletionRowAssertBis = changeDeletionRowAssert.rowAtStartPoint();
    Assertions.assertThat(changeDeletionRowAssert).isSameAs(changeDeletionRowAssertBis);
    Assertions.assertThat(fieldRowFromAssert.get(changeDeletionRowAssert)).isEqualTo(
            fieldRowFromChange.get(changes.getChangesList().get(6)));
  }

  /**
   * This method tests the {@code rowAtStartPoint} navigation method.
   */
  @Test
  @NeedReload
  public void test_row_at_start_point_with_displays() throws Exception {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    Field fieldPosition = ChangeDisplay.class.getDeclaredField("rowPosition");
    fieldPosition.setAccessible(true);
    Field fieldRowDisplay = PositionWithPoints.class.getDeclaredField("instanceAtStartPoint");
    fieldRowDisplay.setAccessible(true);
    Field fieldRowFromChange = Change.class.getDeclaredField("rowAtStartPoint");
    fieldRowFromChange.setAccessible(true);
    Field fieldRowFromDisplay = ChangeRowDisplay.class.getDeclaredField("row");
    fieldRowFromDisplay.setAccessible(true);

    ChangesDisplay changesDisplay = display(changes);

    ChangeDisplay changeCreationDisplay = changesDisplay.change();
    PositionWithPoints positionCreation = (PositionWithPoints) fieldPosition.get(changeCreationDisplay);
    Assertions.assertThat(fieldRowDisplay.get(positionCreation)).isNull();
    ChangeRowDisplay changeCreationRowDisplay = changeCreationDisplay.rowAtStartPoint();
    Assertions.assertThat(fieldRowDisplay.get(positionCreation)).isNotNull();
    ChangeRowDisplay changeCreationRowDisplayBis = changeCreationRowDisplay.rowAtStartPoint();
    Assertions.assertThat(changeCreationRowDisplay).isSameAs(changeCreationRowDisplayBis);
    Assertions.assertThat(fieldRowFromDisplay.get(changeCreationRowDisplay)).isNull();

    ChangeDisplay changeModificationDisplay = changesDisplay.change(3);
    PositionWithPoints positionModification = (PositionWithPoints) fieldPosition.get(changeModificationDisplay);
    Assertions.assertThat(fieldRowDisplay.get(positionModification)).isNull();
    ChangeRowDisplay changeModificationRowDisplay = changeModificationDisplay.rowAtStartPoint();
    Assertions.assertThat(fieldRowDisplay.get(positionModification)).isNotNull();
    ChangeRowDisplay changeModificationRowDisplayBis = changeModificationRowDisplay.rowAtStartPoint();
    Assertions.assertThat(changeModificationRowDisplay).isSameAs(changeModificationRowDisplayBis);
    Assertions.assertThat(fieldRowFromDisplay.get(changeModificationRowDisplay)).isEqualTo(
            fieldRowFromChange.get(changes.getChangesList().get(3)));

    ChangeDisplay changeDeletionDisplay = changesDisplay.change(6);
    PositionWithPoints positionDeletion = (PositionWithPoints) fieldPosition.get(changeDeletionDisplay);
    Assertions.assertThat(fieldRowDisplay.get(positionDeletion)).isNull();
    ChangeRowDisplay changeDeletionRowDisplay = changeDeletionDisplay.rowAtStartPoint();
    Assertions.assertThat(fieldRowDisplay.get(positionDeletion)).isNotNull();
    ChangeRowDisplay changeDeletionRowDisplayBis = changeDeletionRowDisplay.rowAtStartPoint();
    Assertions.assertThat(changeDeletionRowDisplay).isSameAs(changeDeletionRowDisplayBis);
    Assertions.assertThat(fieldRowFromDisplay.get(changeDeletionRowDisplay)).isEqualTo(
            fieldRowFromChange.get(changes.getChangesList().get(6)));
  }
}
