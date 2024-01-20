import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public ArrayList<String> listTasks() {
        ArrayList<String> messages = new ArrayList<String>();
        for (int i = 0; i < this.tasks.size(); i++) {
            messages.add(String.format(
                    "%d.%s", i + 1, this.tasks.get(i).toString()));
        }
        return messages;
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }
}