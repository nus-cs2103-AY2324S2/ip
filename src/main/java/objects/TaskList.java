package objects;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(String input) throws InvalidDeadlineException, InvalidEventException, InvalidCommandException {
        String[] details = input.split(" ", 2);

        if (details.length < 2) {
            throw new InvalidCommandException();
        }

        Task task = null;
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

    public Task getTask(int i) throws InvalidIndexException {
        if (i < 0 || i >= tasks.size()) {
            throw new InvalidIndexException();
        }

        return tasks.get(i);
    }

    public void listTasks() {
        StringBuilder output = new StringBuilder();

        if (tasks.size() == 0) {
            Utils.encaseLines("List is empty!");
        } else {

            for (int i = 0; i < tasks.size(); i++) {
                output.append(String.format("%d. %s", i + 1, tasks.get(i)));

                if (i < tasks.size() - 1) {
                    output.append("\n");
                }
            }

            Utils.encaseLines(output.toString());
        }
    }

    public void markTask(int i) throws InvalidIndexException {
        Task task = this.getTask(i);

        if (task != null) {
            task.mark();
        }
    }

    public void unmarkTask(int i) throws InvalidIndexException {
        Task task = this.getTask(i);

        if (task != null) {
            task.unmark();
        }
    }
}
