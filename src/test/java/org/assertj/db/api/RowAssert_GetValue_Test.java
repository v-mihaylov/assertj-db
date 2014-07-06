package org.assertj.db.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.assertj.db.common.AbstractTest;
import org.assertj.db.error.AssertJDBException;
import org.assertj.db.type.Table;
import org.junit.Test;

/**
 * Test on the {@code getValue) assertion methods on {@code Row}.
 * 
 * @author Régis Pouiller
 * 
 */
public class RowAssert_GetValue_Test extends AbstractTest {

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is less than the
   * minimum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index -1 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_less_than_the_minimum() {
    Table table = new Table(source, "movie");
    assertThat(table).row().getValue(-1);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the {@code index} parameter is more than the
   * maximum.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 3 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_index_is_more_than_the_maximum() {
    Table table = new Table(source, "movie");
    assertThat(table).row().getValue(3);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because of reading after the last row.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Index 3 out of the limits [0, 3[
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_reading_after_the_end() {
    Table table = new Table(source, "movie");
    TableRowAssert assertion = assertThat(table).row();
    assertion.getValue();
    assertion.getValue();
    assertion.getValue();
    assertion.getValue();
  }

  /**
   * This method should throw a {@code NullPointerException}, because the column name in parameter is null.
   */
  @Test(expected = NullPointerException.class)
  public void should_throw_NullPointerException_because_column_name_is_null() {
    Table table = new Table(source, "movie");
    assertThat(table).row().getValue(null);
  }

  /**
   * This method should throw a {@code AssertJDBException}, because the column name in parameter don't exist in the list
   * of columns.
   * <p>
   * Message :
   * </p>
   * 
   * <pre>
   * Column &lt;notexist> does not exist
   * </pre>
   */
  @Test(expected = AssertJDBException.class)
  public void should_throw_AssertJDBException_because_column_dont_exist() {
    Table table = new Table(source, "movie");
    assertThat(table).row().getValue("notexist");
  }

  /**
   * This method tests the {@code getValue} method when using without parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_without_parameters() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Table table = new Table(source, "movie");
    TableRowAssert assertion = assertThat(table).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    field.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(assertion.getValue()).isSameAs(table.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
    assertThat(assertion.getValue()).isSameAs(table.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(assertion.getValue()).isSameAs(table.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
  }

  /**
   * This method tests the {@code getRow} method when using with {@code index} parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_with_parameter() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Table table = new Table(source, "movie");
    TableRowAssert assertion = assertThat(table).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    field.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(assertion.getValue(2)).isSameAs(table.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(assertion.getValue(1)).isSameAs(table.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(assertion.getValue(0)).isSameAs(table.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
  }

  /**
   * This method tests the {@code getColumn} method when using with {@code columnName} parameter.
   * 
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws NoSuchFieldException
   * @throws SecurityException
   */
  @Test
  public void test_when_get_rows_with_column_name_parameter() throws IllegalArgumentException, IllegalAccessException,
      SecurityException, NoSuchFieldException {

    Table table = new Table(source, "movie");
    TableRowAssert assertion = assertThat(table).row();

    Field field = AbstractSubAssert.class.getDeclaredField("indexNextValue");
    field.setAccessible(true);

    assertThat(field.getInt(assertion)).isEqualTo(0);
    assertThat(assertion.getValue("YEAR")).isSameAs(table.getRow(0).getValuesList().get(2));
    assertThat(field.getInt(assertion)).isEqualTo(3);
    assertThat(assertion.getValue("title")).isSameAs(table.getRow(0).getValuesList().get(1));
    assertThat(field.getInt(assertion)).isEqualTo(2);
    assertThat(assertion.getValue("ID")).isSameAs(table.getRow(0).getValuesList().get(0));
    assertThat(field.getInt(assertion)).isEqualTo(1);
  }
}