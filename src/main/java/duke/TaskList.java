package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;
    private Ui ui;

    private List<Task> l;


    public TaskList(Storage storage, Ui ui) throws IOException {
        this.tasks = new ArrayList<>();
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

    public void listTasks() throws IOException {
        tasks = storage.readFromFile();
        ui.divider();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.showTask((i + 1) + ". " + tasks.get(i));
        }
        ui.divider();
    }

    public void markTask(int index) throws IOException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markDone();
            System.out.println("Nice! I've marked this task as done:");
            ui.showTask(task.toString());
            storage.saveToFile(tasks);
        } else {
            System.out.println("Invalid task index");
        }
        ui.divider();
    }

    public void unmarkTask(int index) throws IOException {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.mark_not_done();
            System.out.println("OK, I've marked this task as not done yet:");
            ui.showTask(task.toString());
            storage.saveToFile(tasks);
        } else {
            System.out.println("Invalid task index");
        }
        ui.divider();
    }

    public void addTask(Task task) throws IOException {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        ui.showTask(task.toString());
        storage.saveToFile(tasks);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        ui.divider();
    }

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

}

