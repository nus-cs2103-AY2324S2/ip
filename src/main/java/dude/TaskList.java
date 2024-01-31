package dude;

import dude.task.Task;

import java.util.ArrayList;

public class TaskList {
    Storage storage;

    public TaskList() {
        this.storage = new Storage();
    }

    public TaskList(String path) {
        this.storage = new Storage(path);
    }
    public void add(Task task) {
        storage.createRow(task);
        ArrayList<Task> tasks = storage.listRows();
        Ui.print("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }

    public void list() {
        ArrayList<Task> tasks = storage.listRows();
        String listString = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            listString += i + "." + task + "\n";
        }
        Ui.print(listString);
    }

    public void mark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            Ui.print("Invalid index range\n");
            return;
        }
        Task task = tasks.get(index);
        task.mark();
        storage.createRows(tasks);
        Ui.print("Nice! I've marked this task as done:\n" + task + "\n");
    }

    public void unmark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            Ui.print("Invalid index range\n");
            return;
        }
        Task task = tasks.get(index);
        task.unmark();
        storage.createRows(tasks);
        Ui.print("OK, I've marked this task as not done yet:\n" + task + "\n");

    }

    public void delete(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            Ui.print("Invalid index range\n");
            return;
        }
        Task task = tasks.remove(index);
        storage.createRows(tasks);
        Ui.print("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");

    }
}
