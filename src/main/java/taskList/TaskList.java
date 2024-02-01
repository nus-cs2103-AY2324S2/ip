package taskList;

import java.io.Serializable;
import java.util.ArrayList;
import taskList.tasks.Task;

/**
 * Collection of tasks. Provides methods to manage the tasks in the list.
 *
 * <p>Example usage: Tasklist taskList = new Tasklist(); Tasklist.addTask(new ToDo("read book"));
 */
public class TaskList implements Serializable {
  /** arraylist to store tasks */
  private ArrayList<Task> taskList;

  /**
   * Initalize the TaskList.
   *
   * @param thelist list of tasks.
   */
  public TaskList(ArrayList<Task> thelist) {
    this.taskList = thelist;
  }

  /**
   * Add a task to the TaskList.
   *
   * @param task task to be added.
   */
  public void addTask(Task task) {
    taskList.add(task);
  }

  /**
   * Delete a task from the TaskList.
   *
   * @param task task to be deleted.
   */
  public Task deleteTask(int taskNo) {
    try {
      Task removedTask = taskList.remove(taskNo - 1);
      return removedTask;
    } catch (IndexOutOfBoundsException e) {
      System.out.println(
          "Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
    }
    return null;
  }

  /**
   * Mark a task in the TaskList
   *
   * @param taskNo index of the task to be marked
   */
  public void markTask(int taskNo) {
    try {
      taskList.get(taskNo - 1).markItem();
    } catch (IndexOutOfBoundsException e) {
      System.out.println(
          "Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
    }
  }

  /**
   * Unmark a task in the TaskList
   *
   * @param taskNo index of the task to be unmarked
   */
  public void unmarkTask(int taskNo) {
    try {
      taskList.get(taskNo - 1).unmarkItem();
    } catch (IndexOutOfBoundsException e) {
      System.out.println(
          "Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
    }
  }

  /**
   * Get the size of the tasklist.
   *
   * @return the size of the tasklist.
   */
  public int size() {
    return taskList.size();
  }

  /**
   * Get a specific task from the tasklist.
   *
   * @param i index of the task to be returned.
   * @return the task at the specified index.
   */
  public Task getTask(int i) {
    try {
      return taskList.get(i);
    } catch (IndexOutOfBoundsException e) {
      System.out.println(
          "Error: Index is out of bounds. The list currently has " + taskList.size() + " item(s)");
    }
    return null;
  }
}
