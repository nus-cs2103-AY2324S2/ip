package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
            return null;
        }
    }

    /**
     * Lists all tasks in the task list.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void listTasks() throws IOException {
        l = storage.readFromFile();
        ui.divider();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < l.size(); i++) {
            ui.showTask((i + 1) + ". " + l.get(i));
        }
        ui.divider();
    }

    /**
     * Marks a task as done.
     * @param index The index of the task to mark as done, starting from 0.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public void markTask(int index) throws IOException {
        if (index >= 0 && index < l.size()) {
            Task task = l.get(index);
            task.markDone();
            System.out.println("Nice! I've marked this task as done:");
            ui.showTask(task.toString());
            storage.saveToFile(l);
        } else {
            System.out.println("Invalid task index");
        }
        ui.divider();
    }

    /**
     * Marks a task as not done.
     * @param index The index of the task to mark as not done, starting from 0.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public void unmarkTask(int index) throws IOException {
        if (index >= 0 && index < l.size()) {
            Task task = l.get(index);
            task.mark_not_done();
            System.out.println("OK, I've marked this task as not done yet:");
            ui.showTask(task.toString());
            storage.saveToFile(l);
        } else {
            System.out.println("Invalid task index");
        }
        ui.divider();
    }

    /**
     * Adds a new task to the task list.
     * @param task The task to be added.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public void addTask(Task task) throws IOException {
        l.add(task);
        System.out.println("Got it. I've added this task:");
        ui.showTask(task.toString());
        storage.saveToFile(l);
        System.out.println("Now you have " + l.size() + " tasks in the list.");
        ui.divider();
    }

    /**
     * Deletes a task from the task list.
     * @param deleted_index The index of the task to delete, starting from 0.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public void deleteTask(int deleted_index) throws IOException {
        System.out.println("______________________________________________________");
        int actual_index = deleted_index - 1;
        Task removed_task = l.remove(actual_index);
        System.out.println("Noted. I've removed this task:");
        //System.out.println(" " + removed_task);
        ui.showTask(removed_task.toString());
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }
        System.out.println("______________________________________________________");
        storage.saveToFile(l);
    }

    /**
     * Adds a new Event task to the task list.
     * @param task The Event task to be added.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public void addEventTask(Task task) throws IOException {
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
    public void addTodoTask(Task task) throws IOException {
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
     * Adds a new Deadline task to the task list.
     * @param task The Deadline task to be added.
     * @throws IOException If there is an error saving the updated task list to storage.
     */
    public void addDeadlineTask(Task task) throws IOException {
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
}

