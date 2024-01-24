import java.util.ArrayList;
public class TaskManager {
    ArrayList<Task> list = new ArrayList<>();
    private int NoOfTask = 0;
    public void addTask(Task t) {
        System.out.println("added: ");
        System.out.println(t.toString());
        this.list.add(t);
        this.NoOfTask++;
        System.out.println("There are now " + this.NoOfTask + " tasks in the list.");
    }
    public void listTasks() {
        int count = 1;
        for (Task t : list) {
            System.out.println(count + "." + t.toString());
            count++;
        }
    }
    public void markDone(int i) {
        Task t = list.get(i - 1);
        t.setDone(true);
    }

    public void undo(int i) {
        Task t = list.get(i - 1);
        t.setDone(false);
    }

    public void delete(int i) {
        Task t = list.remove(i - 1);
        this.NoOfTask--;
        System.out.println(t.toString());
        System.out.println("There are now " + this.NoOfTask + " tasks in the list.");
    }
}
