package duke;

import duke.exceptions.CorruptedLogException;
import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> tasks = new ArrayList<>();

  Storage manager;

  public TaskList(Storage manager) {
    this.manager = manager;
    // Try to load on initialisation
    LoadTaskList();
  }

  public void LoadTaskList() {
    try {
      this.manager.loadHistory(this.tasks);
    } catch (CorruptedLogException e) {
      System.out.println(e.getMessage());
    }
  }

  public TaskList() {
    this.manager = null; // No manager for testing.
  }

  /**
   * This function is used to test that adding tasks to internal task list is functional.
   *
   * @param t The task to add.
   * @return The output to be printed by the TaskList.
   */
  public String addTaskTest(Task t) {
    this.tasks.add(t);
    String to_print = "_________________________\n" +
      "added: " + t.getFullStatus() + "\n" +
      "_________________________\n" +
      "Now you have " + this.tasks.size() + " items in your list!\n";
    return to_print;
  }

  /**
   * Saves current state of the task list into the log.
   */
  public void saveTaskList() {
    this.manager.writeLog(this.tasks);
  }

  /**
   * Deletes the task at index i.
   *
   * @param i Index to delete the task at.
   */
  public void deleteTask(int i) {
    Task deleted = this.tasks.remove(i);
    String print_out = "_________________________\n" +
      "Finished with this? Good Job!" + "\n" +
      deleted.getFullStatus() + "\n" +
      "_________________________\n" +
      "Now you have " + this.tasks.size() + " items in your list!\n";
    System.out.println(print_out);
    this.saveTaskList();
  }

  /**
   * Adds task to the task list.
   *
   * @param t The task to add.
   */
  public void addTask(Task t) {
    this.tasks.add(t);
    String to_print = "_________________________\n" +
      "added: " + t.getFullStatus() + "\n" +
      "_________________________\n" +
      "Now you have " + this.tasks.size() + " items in your list!\n";
    System.out.println(to_print);
    this.saveTaskList();
  }

  public int getLength() {
    return this.tasks.size();
  }

  /**
   * Marks a task as done.
   *
   * @param idx The index to mark the task done.
   */
  public void markTask(int idx) {
    Task focus_task = this.tasks.get(idx);
    focus_task.mark();
    String print_out = "_________________________\n" +
      "Marking this done!" + "\n" +
      focus_task.getFullStatus() + "\n" +
      "_________________________\n";
    System.out.println(print_out);
    this.saveTaskList();
  }

  /**
   * Marks a task as not completed.
   *
   * @param idx The index to unmark the task done.
   */
  public void unmarkTask(int idx) {
    Task focus_task = this.tasks.get(idx);
    focus_task.unmark();
    String print_out = "_________________________\n" +
      "Marking this done!" + "\n" +
      focus_task.getFullStatus() + "\n" +
      "_________________________\n";
    System.out.println(print_out);
    this.saveTaskList();
  }

  /**
   * Prints current tasks in the list as well as their respective states.
   */
  public void List() {
    System.out.println("_________________________\n" +
      "Get off your ass and starting doing work!" + "\n");
    for (int i = 0; i < this.tasks.size(); i++) {
      Task curr = this.tasks.get(i);
      System.out.println((i + 1) + "." +
        curr.getFullStatus());
    }
    System.out.println("_________________________\n");
  }

}
