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

  public void saveTaskList() {
    this.manager.writeLog(this.tasks);
  }

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
