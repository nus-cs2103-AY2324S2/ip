package alpa.tasks;

import alpa.exceptions.AlpaException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * Finds tasks in the task list that contain the specified keyword in their description.
     *
     * @param keyword the keyword to search for in the task descriptions
     * @return a list of tasks that contain the specified keyword in their description
     */
    public List<Task> findTasksByKeyword(String keyword) {
        return tasks.stream()
                    .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());
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
