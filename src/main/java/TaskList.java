import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String name) {
        tasks.add(new Task(name));
        Utils.encaseLines("added: " + name);
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);

        } else {
            return null;

        }
    }

    public void printTasks() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < tasks.size(); i++) {
            output.append(i + 1).append(". ").append(tasks.get(i).getName());

            if (i < tasks.size() - 1) {
                output.append("\n");
            }
        }

        Utils.encaseLines(output.toString());
    }
}
