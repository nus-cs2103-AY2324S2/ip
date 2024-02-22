package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
     *
     * @return A string that contains all tasks
     */
    public String print() {
        if (tasks.isEmpty()) {
            return "No tasks yet...\n";
        } else {
            String result = "The tasks as as follows:\n";
            for (int i = 0; i < tasks.size(); i++) {
                result += (i + 1) + "." + tasks.get(i) + "\n";
            }
            return result;
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
        try {
            assert num > 0 && num <= tasks.size();
        } catch (AssertionError e) {
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
        try {
            assert num > 0 && num <= tasks.size();
        } catch (AssertionError e) {
            throw new DukeException("OOPS! Invalid Index!");
        }
        return tasks.remove(num - 1);
    }

    /**
     * Counts the number of available tasks.
     *
     * @return A string that contains the number of tasks
     */
    public String countSize() {
        if (tasks.size() == 1) {
            return "Now you have 1 task in the list\n";
        } else {
            return String.format("Now you have %d tasks in the list\n", tasks.size());
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
            try {
                assert lineSplit.length >= 4;
            } catch (AssertionError e) {
                throw new DukeException("Oops! The file format is wrong!");
            }
            newTask = new Deadline(lineSplit[2], lineSplit[3]);
            if (lineSplit[1].equals("O")) {
                newTask.mark();
            }
            return newTask;
        case "E":
            try {
                assert lineSplit.length >= 5;
            } catch (AssertionError e) {
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
     * @return A string that contains all matching tasks
     */
    public String searchDate(LocalDate localDate) {
        ArrayList<Task> result = new ArrayList<>();
        ArrayList<Task> temp = new ArrayList<>();
        HashMap<Task, LocalDateTime> pairs = new HashMap<>();
        for (Task task : tasks) {
            if (task instanceof Todo) {
                result.add(task);
            } else {
                if (task.canMatchDate(localDate)) {
                    pairs.put(task, ((TaskWithTime) task).getTimestamp());
                    temp.add(task);
                }
            }
        }
        for (int i = 0; i < temp.size(); i++) {
            for (int j = i + 1; j < temp.size(); j++) {
                if (!pairs.get(temp.get(i)).isBefore(pairs.get(temp.get(j)))) {
                    Task t = temp.get(i);
                    temp.set(i, temp.get(j));
                    temp.set(j, t);
                }
            }
        }
        result.addAll(temp);
        if (result.isEmpty()) {
            return "Sorry! No tasks can satisfy your query conditions...\n";
        } else {
            String output = "OK! The search results are as follows:\n";
            for (int i = 1; i <= result.size(); i++) {
                output += String.format("  %d. %s\n", i, result.get(i - 1));
            }
            return output;
        }
    }

    /**
     * Searches all tasks that contain the certain keyword and print them all.
     *
     * @param keyword The keyword typed in by the user
     * @return A string that contains all matching tasks
     */
    public String searchKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isContaining(keyword)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            return "Sorry! No tasks can satisfy your query conditions...\n";
        } else {
            String output = "OK! The search results are as follows:\n";
            for (int i = 1; i <= result.size(); i++) {
                output += String.format("  %d. %s\n", i, result.get(i - 1));
            }
            return output;
        }
    }

    /**
     * Adds a new Task to the list.
     *
     * @param task The task that needs to be added
     * @return A string that tells that the task-adding is successful
     */
    public String add(Task task) {
        tasks.add(task);
        String result = "Got it. I've added this task:\n";
        result += "  " + task + "\n";
        return result + countSize();
    }
}
