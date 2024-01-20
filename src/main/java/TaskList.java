import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    @Override
    public String toString() {
        String out = "";
        int count = 1;

        for (Task currentItem : this.taskList) {
            out += count + "." + currentItem + "\n";
            count++;
        }
        return out.equals("") ? "No tasks to do! Yay!\n" : out;
    }

    public void add(Task taskName) {
        this.taskList.add(taskName);
        System.out.println("added: " + taskName);
        System.out.println("Looks like you have " + taskList.size() + " things left to do!");
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);

    }
}
