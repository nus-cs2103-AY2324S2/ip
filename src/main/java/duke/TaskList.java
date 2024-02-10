package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    //private List<Task> tasks;
    private Storage storage;
    private Ui ui;

    private List<Task> l;

    /**
     * Constructs a TaskList with the specified storage and UI.
     *
     * @param storage The storage to be used for loading and saving tasks.
     * @param ui      The UI to be used for displaying information to the user.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList(Storage storage, Ui ui) throws IOException {
        //this.tasks = new ArrayList<>();
        this.storage = storage;
        this.ui = ui;
        l = storage.readFromFile();
    }

    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd, e.g., 2023-03-15");
            return null;
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param deletedIndex The index of the task to delete, starting from 0.
     * @return
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public String deleteTask(int deletedIndex) throws IOException {
        assert deletedIndex >= 0 && deletedIndex < l.size();
        if (deletedIndex >= 0 && deletedIndex < l.size()) {
            Task removedTask = l.remove(deletedIndex);
            storage.saveToFile(l);
            return "Noted. I've removed this task: \n  " + removedTask + "\nNow you have " + l.size() + " tasks in the list.";
        } else {
            return "Invalid task index. Please enter a valid number between 1 and " + l.size();
        }
    }


    /**
     * Adds a new Event task to the task list.
     * @param task The Event task to be added.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public void addEventTask(Task task) throws IOException {
        assert task != null;
        System.out.println("______________________________________________________");
        System.out.println("Got it. I've added this task:");
        //System.out.println(" " + task);
        ui.showTask(task.toString());
        l.add(task);
        storage.saveToFile(l);
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }
        System.out.println("______________________________________________________");
    }

    /**
     * Adds a new ToDo task to the task list.
     * @param task The ToDo task to be added.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public String addTodoTask(Task task) throws IOException {
        assert task != null;
        l.add(task);
        storage.saveToFile(l);
        String response = "Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + l.size() + " tasks in the list.";
        return response;
    }


    /**
     * Adds a new Deadline task to the task list.
     * @param task The Deadline task to be added.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public String addDeadlineTask(Task task) throws IOException {
        assert task != null;
        l.add(task);
        storage.saveToFile(l);
        String response = "Got it. I've added this task:\n" + task.toString() +
                "\nNow you have " + l.size() + " tasks in the list.";
        return response;
    }


    /**
     * Gets the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return l.size();
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Object getTask(int i) {
        return l.get(i);
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     *
     * @param user_keyword The keyword to search for in the task descriptions.
     * @return
     */
    public String findTask(String user_keyword) {
        assert user_keyword != null;
        StringBuilder response = new StringBuilder();
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : l) {
            if (task.description.contains(user_keyword)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            response.append("No matching tasks found.");
        } else {
            response.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return response.toString();
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return
     * @throws IOException If an I/O error occurs.
     */
    public String listTasks() {
        assert l != null;
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < l.size(); i++) {
            response.append(i + 1).append(". ").append(l.get(i)).append("\n");
        }
        return response.toString();
    }
    /**
     * Marks a task as done.
     *
     * @param index The index of the task to mark as done, starting from 0.
     * @return
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public String markTask(int index) throws IOException {
        assert index >= 0 && index < l.size();
        if (index >= 0 && index < l.size()) {
            Task task = l.get(index);
            task.markDone();
            storage.saveToFile(l);
            return "Nice! I've marked this task as done:\n  " + task;
        } else {
            return "Invalid task index";
        }
    }
    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to mark as not done, starting from 0.
     * @return
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public String unmarkTask(int index) throws IOException {
        assert index >= 0 && index < l.size();
        if (index >= 0 && index < l.size()) {
            Task task = l.get(index);
            task.mark_not_done();
            storage.saveToFile(l);
            return "OK, I've marked this task as not done yet:\n  " + task;
        } else {
            return "Invalid task index";
        }
    }


}
