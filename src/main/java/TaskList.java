import java.util.ArrayList;
import java.util.List;

public class TaskList {
  private List<Task> tasks;

  public TaskList() {
    this.tasks = new ArrayList<>();
  }

  public void addAll(List<Task> tasks) {
    this.tasks.addAll(tasks);
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public Task deleteTask(int index) throws AlpaException {
    if (index >= 0 && index < tasks.size()) {
      return tasks.remove(index);
    } else {
      throw new AlpaException("\nInvalid task number, human!!");
    }
  }

  public Task markTaskAsDone(int index) throws AlpaException {
    if (index < 0 || index >= tasks.size()) {
      throw new AlpaException("\nTask does not exist, human!!");
    }
    Task task = tasks.get(index);
    task.markAsDone();
    return task;
  }

  public Task markTaskAsNotDone(int index) throws AlpaException {
    if (index < 0 || index >= tasks.size()) {
      throw new AlpaException("\nTask does not exist, human!!");
    }
    Task task = tasks.get(index);
    task.markAsNotDone();
    return task;
  }

  public List<Task> getTasks() {
    return List.copyOf(tasks);
  }

  public int getSize() {
    return tasks.size();
  }
}
