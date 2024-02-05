package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest {
  @Test
  public void testToString() {
    Todos todos = new Todos("Sample Todo");
    assertEquals("[T][ ] Sample Todo", todos.toString());
  }

  @Test
  public void testToFileString() {
    Todos todos = new Todos("Sample Todo");
    assertEquals("T | 0 | Sample Todo", todos.toFileString());
  }
}
