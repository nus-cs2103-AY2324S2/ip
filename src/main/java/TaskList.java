import java.util.ArrayList;
import java.util.List;

public class TaskList {

  /**
   * The list of tasks.
   */
  private List<Task> tasks;

  /**
   * Constructs a TaskList object.
   */
  public TaskList() {
    tasks = new ArrayList<Task>();
  }

  /**
   * Constructs a TaskList object with the given list of tasks.
   *
   * @param tasks The list of tasks.
   * @throws DukeException If the given list of tasks is null.
   */
  public TaskList(List<Task> tasks) throws DukeException {
    this.tasks = tasks;
  }

  /**
   * Returns the task at the given index.
   *
   * @param index The index of the task.
   * @return The task at the given index.
   */
  public Task get(int index) {
    return tasks.get(index);
  }

  /**
   * Adds the given task to the list of tasks.
   *
   * @param task The task to be added.
   * @return True if the task is added successfully, false otherwise.
   */
  public boolean add(Task task) {
    return tasks.add(task);
  }

  /**
   * Removes the task at the given index from the list of tasks.
   *
   * @param index The index of the task to be removed.
   * @return The task that is removed.
   */
  public Task remove(int index) {
    return tasks.remove(index);
  }

  /**
   * Returns the number of tasks in the list.
   *
   * @return The number of tasks in the list.
   */
  public int getSize() {
    return tasks.size();
  }

  /**
   * Returns the list of tasks.
   *
   * @return The list of tasks.
   */
  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * Marks the task at the given index as done.
   *
   * @param index The index of the task to be marked as done.
   */
  public void markDone(int index) {
    tasks.get(index).markDone();
  }

}
