import java.util.ArrayList;
import java.util.List;

public class TaskManager {
  private List<Task> tasks = new ArrayList<>();
  
  public Task get(int i) {
    return this.tasks.get(i);
  }

  public void add(Task task) {
    System.out.println("--------------------");
    System.out.println("Got it, I've added this task: \n" + task);
    tasks.add(task);
    System.out.println("--------------------");
  }

  public void print() {
    System.out.println("--------------------");
    for (int i = 0; i < this.tasks.size(); i++) {
        System.out.println((i + 1) + ". " + this.get(i));
    }
    System.out.println("--------------------");
  }
}
