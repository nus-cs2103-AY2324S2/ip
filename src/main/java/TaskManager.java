import java.sql.Array;
import java.util.ArrayList;
public class TaskManager {
    ArrayList<Task> list = new ArrayList<>();
    private int NoOfTask = 0;
    public void addTask(Task t) {
        this.list.add(t);
        this.NoOfTask++;
    }
    public void listTasks() {
        int count = 1;
        for (Task t : list) {
            System.out.println(count + ".[" + t.getStatusIcon() + "] " + t.toString());
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
}
