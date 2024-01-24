import java.util.ArrayList;

public class TaskList {
    public static final String INDENT = "     ";
    public static final String LINE =  "____________________________________________________________";

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String markTaskDone(int idx) {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markDone();
        }
        return tasks.get(idx).toString();
    }

    public String markTaskUndone(int idx) {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markUndone();
        }
        return tasks.get(idx).toString();
    }

    public void printTasks() {
        System.out.println(INDENT + LINE);

        if (tasks.size() == 0) {
            System.out.println(INDENT + "no tasks");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("     "  + (i+1) + ". " + tasks.get(i));
            }
        }

        System.out.println(INDENT + LINE + "\n");
    }
}
