package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the part of the program that stores all currently available tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList using the extracted tasks from the file.
     *
     * @param load The ArrayLisst provided by the file
     * @throws DukeException if the initialization is unsuccessful.
     */
    TaskList(ArrayList<String> load) {
        tasks = new ArrayList<>();
        try {
            for (String task : load) {
                tasks.add(lineToTask(task));
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    /**
     * Prints all tasks in the list.
     */
    public void print() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks yet...");
        } else {
            System.out.println("The tasks as as follows:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Gets a certain task according to the index
     *
     * @param num Index of the task
     * @return The task that needs to be retrieved
     * @throws DukeException if the index is invalid
     */
    public Task retrieve(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("OOPS! Invalid Index!");
        }
        return tasks.get(num - 1);
    }

    /**
     * Removes a certain task according to the index
     *
     * @param num Index of the task
     * @return The task that needs to be removed
     * @throws DukeException if the index is invalid
     */
    public Task remove(int num) throws DukeException {
        if (num <= 0 || num > tasks.size()) {
            throw new DukeException("OOPS! Invalid Index!");
        }
        return tasks.remove(num - 1);
    }

    /**
     * Counts the number of available tasks.
     */
    public void countSize() {
        if (tasks.size() == 1) {
            System.out.println("Now you have 1 task in the list");
        } else {
            System.out.printf("Now you have %d tasks in the list\n", tasks.size());
        }
    }

    private Task lineToTask(String line) throws DukeException {
        String[] lineSplit = line.split(" \\| ");
        switch (lineSplit[0]) {
        case "T":
            Task newTask = new Todo(lineSplit[2]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "D":
            if (lineSplit.length < 4) {
                throw new DukeException("Oops! The file format is wrong!");
            }
            newTask = new Deadline(lineSplit[2], lineSplit[3]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "E":
            if (lineSplit.length < 5) {
                throw new DukeException("Oops! The file format is wrong!");
            }
            newTask = new Event(lineSplit[2], lineSplit[3], lineSplit[4]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        default:
            throw new DukeException("Oops! The file format is wrong!");
        }
    }

    /**
     * Searches all tasks with the given date. If there is a match, print it out.
     *
     * @param localDate The searching date
     */
    public void searchDate(LocalDate localDate) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.canMatchDate(localDate)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Sorry! No tasks can satisfy your query conditions...");
        } else {
            System.out.println("OK! The search results are as follows:");
            for (int i = 1; i <= result.size(); i++) {
                System.out.printf("  %d. %s\n", i, result.get(i - 1));
            }
        }
    }

    /**
     * Searches all tasks that contain the certain keyword and print them all.
     *
     * @param keyword The keyword typed in by the user
     */
    public void searchKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isContaining(keyword)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Sorry! No tasks can satisfy your query conditions...");
        } else {
            System.out.println("OK! The search results are as follows:");
            for (int i = 1; i <= result.size(); i++) {
                System.out.printf("  %d. %s\n", i, result.get(i - 1));
            }
        }
    }

    /**
     * HEAD
     * Adds a new Task to the list.
     *
     * @param task The task that needs to be added
     */
    public void add(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        countSize();
    }
}
