package dude;

import java.time.LocalDate;
import java.util.ArrayList;

import dude.task.Task;

/**
 * TaskList is an abstraction for Dude functions.
 */
public class TaskList {
    private Storage storage;

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

    /**
     * Adds task to list.
     * @param task Task to be added to list.
     * @return Response string for Dude.
     */
    public String add(Task task) {
        storage.createRow(task);
        ArrayList<Task> tasks = storage.listRows();
        return "Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Lists the current tasks.
     * @return Response string for Dude.
     */
    public String list() {
        ArrayList<Task> tasks = storage.listRows();
        String listString = "";
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            listString += i + "." + task + "\n";
        }
        if (listString.isEmpty()) {
            return "No tasks in list yet!";
        }
        return listString;
    }

    /**
     * Marks task at index as done.
     * @param index Task index to be marked as done.
     * @return Response string for Dude.
     */
    public String mark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            return "Invalid index range\n";
        }
        Task task = tasks.get(index);
        task.mark();
        boolean isRowsCreated = storage.createRows(tasks);
        assert isRowsCreated : "Rows not created";
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }

    /**
     * Marks task at index as undone.
     * @param index Task index to be marked as undone.
     * @return Response string for Dude.
     */
    public String unmark(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            return "Invalid index range\n";
        }
        Task task = tasks.get(index);
        task.unmark();
        boolean isRowsCreated = storage.createRows(tasks);
        assert isRowsCreated : "Rows not created";
        return "OK, I've marked this task as not done yet:\n" + task + "\n";

    }

    /**
     * Deletes task at index.
     * @param index Index at which task is removed.
     * @return Response string for Dude.
     */
    public String delete(int index) {
        ArrayList<Task> tasks = storage.listRows();
        if (index >= tasks.size() || index < 0) {
            return "Invalid index range\n";
        }
        Task task = tasks.remove(index);
        boolean isRowsCreated = storage.createRows(tasks);
        assert isRowsCreated : "Rows not created";
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.size() + " tasks in the list.\n";
    }

    /**
     * Finds tasks based on search keyword.
     * @param keyword Keyword to search for.
     * @return Response string for Dude.
     */
    public String find(String keyword) {
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
        if (listString.isEmpty()) {
            return "No tasks found!";
        }
        return "Here are the matching tasks in your list:\n" + listString;
    }

    /**
     * Finds tasks scheduled on the given date.
     * @param dateString Date to check schedule.
     * @return Response string for Dude.
     */
    public String schedule(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        ArrayList<Task> tasks = storage.listRows();
        String listString = "";
        int count = 1;
        for (int i = 1; i < tasks.size() + 1; i++) {
            Task task = tasks.get(i - 1);
            if (task.isOnDate(date)) {
                listString += count + "." + task + "\n";
                count++;
            }
        }
        if (listString.isEmpty()) {
            return "No tasks found!";
        }
        return "Here are the tasks happening on " + date + ":\n" + listString;
    }
}
