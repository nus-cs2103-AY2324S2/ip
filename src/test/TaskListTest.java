// used chatGPT for this unit test file

package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class TaskListTest {

  private TaskList taskList;
  private Task todoStub;

  @BeforeEach
  void setUp() {
    ArrayList<Task> tasks = new ArrayList<>();
    todoStub = new Todo("Sample Task");
    tasks.add(todoStub);
    taskList = new TaskList(tasks);
  }

  @Test
  void addTask_success() {
    Task newTodo = new Todo("New Task");
    taskList.add(newTodo);
    assertEquals(2, taskList.len());
  }

  @Test
  void removeTask_success() {
    taskList.remove(0);
    assertEquals(0, taskList.len());
  }

  @Test
  void markTaskAsDone_success() {
    taskList.asDone(1);
    assertTrue(todoStub.isDone);
  }

  @Test
  void markTaskAsNotDone_success() {
    todoStub.markAsDone(); // First mark it as done
    taskList.asNotDone(1);
    assertFalse(todoStub.isDone);
  }

  @Test
  void getTask_success() {
    Task fetchedTask = taskList.get(0);
    assertEquals(todoStub, fetchedTask);
  }

  @Test
  void taskListLength_success() {
    assertEquals(1, taskList.len());
  }

  @Test
  void taskListMine() {
    assertEquals(tasks, taskList.mine());
  }
}
