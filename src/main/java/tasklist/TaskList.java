package tasklist;

import java.util.ArrayList;

import task.Task;

public class TaskList {
  private ArrayList<Task> tasksList;

  public TaskList() {
    this.tasksList = new ArrayList<Task>();
  }

  public TaskList(ArrayList<Task> tasksList) {
    this.tasksList = tasksList;
  }

  /*
   * Returns the number of tasks in the list
   */
  public int getTaskCount() {
    return this.tasksList.size();
  }

  /*
   * Get the list of tasks
   */
  public ArrayList<Task> getTasksList() {
    return this.tasksList;
  }

  /*
   * Get task at index
   */
  public Task getTask(int index) {
    return this.tasksList.get(index - 1);
  }

  /*
   * Prints the list of tasks
   */
  public void printList() {
    System.out.println("Here are the tasks in your list:");
    for (int i = 0; i < this.tasksList.size(); i++) {
      System.out.println((i + 1) + ". " + this.tasksList.get(i));
    }
  }

  /*
   * Adds a task to the list
   */
  public void addTask(Task task) {
    this.tasksList.add(task);
  }

  /*
   * Removes a task from the list
   */
  public void removeTask(int index) {
    this.tasksList.remove(index - 1);
  }
}
