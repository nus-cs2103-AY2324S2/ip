import java.util.ArrayList;

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
   * Return a message representing the number of tasks in the list
   */
  public String getTaskCountMessage() {
    if (getTaskCount() == 1) {
      return "Now you have " + getTaskCount() + " task in the list.";
    } else {
      return "Now you have " + getTaskCount() + " tasks in the list.";
    }
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
    System.out.println("Got it. I've added this task:");
    this.tasksList.add(task);
    System.out.println(task);
    System.out.println(getTaskCountMessage());
  }

  /*
   * Removes a task from the list
   */
  public void removeTask(int index) {
    Task task = this.tasksList.get(index - 1);
    System.out.println("Noted. I've removed this task:");
    System.out.println(task);
    this.tasksList.remove(index - 1);
  }
}
