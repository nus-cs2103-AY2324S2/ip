package simpli.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void mark(int taskNum) {
        int itemIndex = taskNum - 1;
        tasks.get(itemIndex).done();
    }

    public void unmark(int taskNum) {
        int itemIndex = taskNum - 1;
        tasks.get(itemIndex).undone();
    }

    public Task addTodo(String[] tokens) {
        Todo todo = new Todo(tokens[1].equals("1"), tokens[2]);
        tasks.add(todo);
        return todo;
    }

    public Task addDeadline(String[] tokens, LocalDateTime[] dateTimes) {
        Deadline deadline = new Deadline(tokens[1].equals("1"), tokens[2], dateTimes[0]);
        tasks.add(deadline);
        return deadline;
    }

    public Task addEvent(String[] tokens, LocalDateTime[] dateTimes) {
        Event event = new Event(tokens[1].equals("1"), tokens[2], dateTimes[0], dateTimes[1]);
        tasks.add(event);
        return event;
    }

    public Task deleteTask(int taskNum) {
        Task removedTask = tasks.get(taskNum - 1);
        tasks.remove(taskNum - 1);
        return removedTask;
    }

    @Override
    public String toString() {
        StringBuilder strItems = new StringBuilder();
        for (int i = 0; i < tasks.size() - 1; i++) {
            strItems.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        if (!tasks.isEmpty()) {
            strItems.append(String.format("%d. %s", tasks.size(), tasks.get(tasks.size() - 1)));
        }

        return strItems.toString();
    }

    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> tasks() {
        return this.tasks;
    }

}
