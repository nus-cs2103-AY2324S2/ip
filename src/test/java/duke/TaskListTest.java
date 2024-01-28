package duke;

import duke.tasks.ToDos;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TaskListTest {
  @Test
  public void testInsert() {
    TaskList task_list = new TaskList();
    ToDos to_add = new ToDos("do stuff");
    ToDos to_add_2 = new ToDos("do more stuff");
    task_list.addTaskTest(to_add);
    assertEquals(1, task_list.getLength());
    task_list.addTaskTest(to_add_2);
    assertEquals(2, task_list.getLength());
  }
}
