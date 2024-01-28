package task;

import exception.TodoFormatException;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void addTasks(Task task) {
        tasks.add(task);
        System.out.println("\t Got it. Uncle added this task:\n\t\t " + task
                + "\n\t Now you have " + tasks.size() + " task(s) in the list.");
    }

    public int numTasks() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public Task remove(int i) {
        return tasks.remove(i);
    }
}
