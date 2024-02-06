package yapper;

import java.util.ArrayList;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

public class TaskList {
    private final ArrayList<Task> tasks;
    private Ui ui;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void setUi(Ui ui) {
        this.ui = ui;
    }

    public int listSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
        if (task instanceof Todo) {
            ui.addTodoMessage((Todo) task);
        } else if (task instanceof Deadline) {
            ui.addDeadlineMessage((Deadline) task);
        } else if (task instanceof Event) {
            ui.addEventMessage((Event) task);
        } else {
            System.out.println("wrong task type added, user should not reach here");
        }
    }

    public void addTaskNoMessage(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        Ui.listMessage();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Ui.indent() + (i + 1) + "." + tasks.get(i));
        }
    }

    public void markTask(int i) {
        Task task = tasks.get(i - 1);
        task.markAsDone();
        ui.markMessage(task);
    }

    public void unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.unmark();
        ui.unmarkMessage(task);
    }

    public void deleteTask(int i) {
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        ui.deleteMessage(task);
    }

    public TaskList find(String string) {
        TaskList foundTasks = new TaskList();
        Ui.findMessage(string);
        for (Task task : tasks) {
            if (task.contains(string)) {
                foundTasks.addTaskNoMessage(task);
            }
        }

        if (foundTasks.listSize() == 0) {
            Ui.foundNothingMessage();
        } else {
            foundTasks.listTasks();
        }

        return foundTasks;
    }
}
