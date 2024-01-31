package dude;

import dude.task.Task;

import java.util.ArrayList;

public class TaskList {
    Storage storage;

    /**
     * Class constructor.
     */
    public TaskList() {
        this.storage = new Storage();
    }

    /**
     * Class constructor specifying the name of the task list.
     * @param listName List name which will be used as the path for the storage file.
     */
    public TaskList(String listName) {
        this.storage = new Storage(listName);
    }
    public void add(Task task) {
        storage.createRow(task);
        ArrayList<Task> tasks = storage.listRows();
        Ui.print("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }

    /**
     * Lists the current tasks.
     */
    public void list() {
        ArrayList<Task> tasks = storage.listRows();
        String listString = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            listString += i + "." + task + "\n";
        }
        Ui.print(listString);
    }

    /**
     * Marks task at index as done.
     * @param index Task index to be marked as done.
     */
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

    /**
     * Marks task at index as undone.
     * @param index Task index to be marked as undone.
     */
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

    /**
     * Deletes task at index.
     * @param index Index at which task is removed.
     */
    public void delete(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            Ui.print("Invalid index range\n");
            return;
        }
        Task task = tasks.remove(index);
        storage.createRows(tasks);
        Ui.print(
                "Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n");
    }

    public void find(String keyword) {
        ArrayList<Task> tasks = storage.listRows();
        String listString = "";
        int count = 1;
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            if (task.contains(keyword)) {
                listString += count + "." + task + "\n";
                count++;
            }
        }
        Ui.print("Here are the matching tasks in your list:\n" + listString);
    }
}
