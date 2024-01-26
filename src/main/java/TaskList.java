import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String input) {
        Task task = null;
        String[] details = input.split(" ", 2);
        String type = details[0];

        switch (type) {
            case "todo":
                task = new ToDos(details[1]);
                break;

            case "deadline":
                task = Utils.createDeadline(details[1]);
                break;

            case "event":
                task = Utils.createEvent(details[1]);
        }

        tasks.add(task);
        String o = String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task.toString(), tasks.size());
        Utils.encaseLines(o);
    }

    public Task getTask(int i) {
        return i >= 0 && i < tasks.size() ? tasks.get(i) : null;
    }

    public void listTasks() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("%d. %s", i + 1, tasks.get(i)));

            if (i < tasks.size() - 1) {
                output.append("\n");
            }
        }

        Utils.encaseLines(output.toString());
    }

    public void mark(int i) {
        this.getTask(i).mark();
    }

    public void unmark(int i){
        this.getTask(i).unmark();
    }
}
