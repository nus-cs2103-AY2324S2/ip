import java.util.ArrayList;

public class TaskList {
    public static final String INDENT = "     ";
    public static final String LINE =  "____________________________________________________________";

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public String addTask(Task task) {
        tasks.add(task);
        return task.toString();
    }

    public String markTaskDone(int idx) throws DukeException {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markDone();
            return tasks.get(idx).toString();
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    public String markTaskUndone(int idx) throws DukeException {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).markUndone();
            return tasks.get(idx).toString();
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    public String deleteTask(int idx) throws DukeException {
        if (idx >= 0 && idx < tasks.size()) {
            String taskDescription = tasks.get(idx).toString();
            tasks.remove(idx);
            return taskDescription;
        } else {
            throw new DukeException("Invalid task index: " + idx);
        }
    }

    public int getNumberTasks() {
        return tasks.size();
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
