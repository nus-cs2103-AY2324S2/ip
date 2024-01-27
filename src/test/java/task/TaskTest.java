package task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {
  @Test
  public void testTaskToString() {
    Task task = new Task(1, "test task");
    assertEquals("[ ] test task",task.toString());

    Task task2 = new Task(1,"test task", true);
    assertEquals("[X] test task", task2.toString());
  }

  @Test
  public void testTaskGetDetails() {
    Task task = new Task(1,"test task");
    assertEquals("test task", task.getDetails());
  }

  @Test void testTaskToggleDone() {
    Task task = new Task(1,"test task");
    task.toggleDone();
    assertEquals("[X] test task", task.toString());
    task.toggleDone();
    assertEquals("[ ] test task", task.toString());
  }

  @Test
  public void testTodoToString() {
    Todo todo = new Todo(1,"test todo");
    assertEquals("[T][ ] test todo", todo.toString());

    Todo todo2 = new Todo(1,"test todo", true);
    assertEquals("[T][X] test todo", todo2.toString());
  }

  @Test
  public void testDeadlineToString() {
    Deadline deadline = new Deadline(1,"test deadline", "Aug 23 2020");
    assertEquals("[D][ ] test deadline (by: Aug 23 2020)", deadline.toString());

    Deadline deadline2 = new Deadline(1,"test deadline", "Aug 23 2020", true);
    assertEquals("[D][X] test deadline (by: Aug 23 2020)", deadline2.toString());
  }

  @Test
  public void testEventToString() {
    Event event = new Event(1,"test event", "Aug 23 2020", "Aug 24 2020");
    assertEquals("[E][ ] test event (from: Aug 23 2020 to: Aug 24 2020)", event.toString());

    Event event2 = new Event(1,"test event", "Aug 23 2020", "Aug 24 2020", true);
    assertEquals("[E][X] test event (from: Aug 23 2020 to: Aug 24 2020)", event2.toString());
  }
}
