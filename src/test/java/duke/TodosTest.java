package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** Tests the functionality of the Todos class */
public class TodosTest {
  /** Tests the toString method of the Todos class */
  @Test
  public void testToString() {
    Todos todos = new Todos("Sample Todo");
    assertEquals("[T][ ] Sample Todo", todos.toString());
  }

  /** Tests the toFileString method of the Todos class */
  @Test
  public void testToFileString() {
    Todos todos = new Todos("Sample Todo");
    assertEquals("T | 0 | Sample Todo", todos.toFileString());
  }
}
