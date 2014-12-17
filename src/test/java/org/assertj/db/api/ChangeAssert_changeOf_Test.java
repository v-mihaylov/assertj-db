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

import org.assertj.db.common.AbstractTest;
import org.assertj.db.common.NeedReload;
import org.assertj.db.type.Changes;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

/**
 * Test on {@code changeOfCreation()}, {@code changeOfModification()} and {@code changeOfDeletion()} methods.
 *
 * @author Régis Pouiller
 */
public class ChangeAssert_changeOf_Test extends AbstractTest {

  /**
   * This method test the {@code changeOfCreation()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfCreation_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changeAssert.changeOfCreation()).as("changeAssert.changeOfCreation()")
                                   .isSameAs(changesOfCreationAssert.changeOfCreation(0))
                                   .isSameAs(changesAssert.changeOfCreation(0));
  }

  /**
   * This method test the {@code changeOfModification()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfModification_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changeAssert.changeOfModification()).as("changeAssert.changeOfModification()")
                                   .isSameAs(changesOfCreationAssert.changeOfModification(0))
                                   .isSameAs(changesAssert.changeOfModification(0));
  }

  /**
   * This method test the {@code changeOfDeletion()} of {@code Change}.
   */
  @Test
  @NeedReload
  public void test_changeOfDeletion_methods() {
    Changes changes = new Changes(source).setStartPointNow();
    updateChangesForTests();
    changes.setEndPointNow();

    ChangesAssert changesAssert = assertThat(changes);
    ChangesAssert changesOfCreationAssert = changesAssert.ofCreation();
    ChangeAssert changeAssert = changesAssert.change();

    assertThat(changeAssert.changeOfDeletion()).as("changeAssert.changeOfDeletion()")
                                   .isSameAs(changesOfCreationAssert.changeOfDeletion(0))
                                   .isSameAs(changesAssert.changeOfDeletion(0));
  }
}