import java.util.*;

public class TaskList {
    static String line = "-------------------------------------------------\n";
    private static final int maxTask = 100; // assume no more than 100 tasks
    String[] tasks; // task list
    int count; // initialize task counter

    public TaskList() {
        this.tasks = new String[maxTask];
        this.count = 0;
    }

    // append task to the list
    public void addTask(String task) {
        if (count < maxTask) {
            tasks[count++] = task;
            System.out.println(line + "Task added: " + task + "\n" + line);
            //tasks.add(task);
        } else {
            // if more than 100 tasks
            System.out.println("Sorry, list is full. Can't add anymore.");
        }
    }

    // display task list
    public void getTasks() {
        for (int i = 0; i < count; i++) {
            System.out.println( (i + 1) + ". " + tasks[i]);
        }
    }
}
