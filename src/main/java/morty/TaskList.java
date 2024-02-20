package morty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import morty.task.Task;

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
   */
  public TaskList(List<Task> tasks) {
    assert tasks != null : "List of tasks cannot be null";
    this.tasks = tasks;
  }

  /**
   * Returns the task at the given index.
   *
   * @param index The index of the task.
   * @return The task at the given index.
   */
  public Task get(int index) {
    assert index >= 0 && index < tasks.size() : "Index out of bounds";
    return tasks.get(index);
  }

  /**
   * Adds the given task to the list of tasks.
   *
   * @param task The task to be added.
   * @return True if the task is added successfully, false otherwise.
   */
  public boolean add(Task task) {
    assert task != null : "Task cannot be null";
    return tasks.add(task);
  }

  /**
   * Removes the task at the given index from the list of tasks.
   *
   * @param index The index of the task to be removed.
   * @return The task that is removed.
   */
  public Task remove(int index) {
    assert index >= 0 && index < tasks.size() : "Index out of bounds";
    return tasks.remove(index);
  }

  /**
   * Returns the number of tasks in the list.
   *
   * @return The number of tasks in the list.
   */
  public int getSize() {
    assert tasks != null : "List of tasks cannot be null";
    return tasks.size();
  }

  /**
   * Returns the list of tasks.
   *
   * @return The list of tasks.
   */
  public List<Task> getTasks() {
    assert tasks != null : "List of tasks cannot be null";
    return tasks;
  }

  /**
   * Marks the task at the given index as done.
   *
   * @param index The index of the task to be marked as done.
   */
  public void markDone(int index) {
    assert index >= 0 && index < tasks.size() : "Index out of bounds";
    tasks.get(index).markDone();
  }

  /**
   * Returns a list of tasks that contain the given keyword.
   *
   * @param keyword The keyword to search for.
   * @return The list of tasks that contain the given keyword.
   */
  public List<Task> find(String keyword) {
    assert keyword != null : "Keyword cannot be null";
    return tasks.stream()
        .filter(task -> task.getTitle().contains(keyword))
        .collect(Collectors.toList());
  }

}
