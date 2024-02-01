package duke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    public static int storageFill = 0;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    protected void deleteTask(int index) {
        tasks.remove(index);
        TaskList.storageFill--; // Update task count

    }

    protected Task getTask(int index) {
        return this.tasks.get(index);
    }

    protected List<Task> getTasks() {
        return this.tasks;
    }

    public void list() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < TaskList.storageFill; i++) {
            String formattedOutput = String.format("\t%d. %s", (i + 1), this.tasks.get(i));
            System.out.println(formattedOutput);
        }
    }

    protected void addTask(Task task) {
        tasks.add(task);
        storageFill++;
    }
}
