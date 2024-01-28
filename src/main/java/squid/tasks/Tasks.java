package squid.tasks;

import squid.constants.CORRECT_USAGE;
import squid.constants.EXCEPTIONS;
import squid.constants.REGEX;
import squid.io.SquidFile;
import squid.exceptions.DuplicateTaskNameException;
import squid.exceptions.IncorrectIndexException;
import squid.exceptions.ParseFailException;
import squid.exceptions.SquidDateException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tasks {
    private static ArrayList<Task> arr;

    /**
     * @return The number of existing tasks.
     */
    public static int size() {
        return arr.size();
    }

    /**
     * Gets the specified task.
     *
     * @param i The index of the task. (0 indexing)
     * @return The task stored at the specified index.
     * @throws IncorrectIndexException If there is no task at the specified index.
     */
    public static Task get(int i) throws IncorrectIndexException {
        try {
            return arr.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIndexException(EXCEPTIONS.INCORRECT_INDEX);
        }
    }

    /**
     * Adds a task.
     *
     * @param task The task to be added.
     * @throws DuplicateTaskNameException If there is an existing task with the same task name.
     */
    public static void add(Task task) throws DuplicateTaskNameException {
        long dupes = arr.stream().filter(task1 -> task1.taskName.equals(task.taskName)).count();
        if (dupes != 0) {
            throw new DuplicateTaskNameException(
                    String.format(EXCEPTIONS.DUPLICATE_TASK_NAME, task.taskName));
        }
        arr.add(task);
    }

    /**
     * Deletes a task.
     *
     * @param index The task to be deleted.
     * @return The deleted task.
     * @throws IncorrectIndexException If there is no task at the specified index.
     */
    public static Task delete(String index) throws IncorrectIndexException {
        int i = -1;
        Task deleted;
        try {
            i = Integer.parseInt(index.strip()) - 1;
            deleted = arr.get(i);
        } catch (Exception e) {
            throw new IncorrectIndexException(
                    String.format(EXCEPTIONS.INCORRECT_INDEX, CORRECT_USAGE.DELETE));
        }
        arr.remove(i);
        return deleted;
    }

    /**
     * Purely lists current tasks.
     * @throws IncorrectIndexException Should never be thrown.
     */
    public static void list() throws IncorrectIndexException {
        for (int i = 0; i < Tasks.size(); i++) {
            Task currTask = Tasks.get(i);
            System.out.printf("%d: %s%n", i + 1, currTask);
        }
    }

    public static void list(List<Task> arr) {
        for (int i = 0; i < arr.size(); i++) {
            Task currTask = arr.get(i);
            System.out.printf("%d: %s%n", i + 1, currTask);
        }
    }

    public static void find(String regex) {
        List<Task> filtered = arr.stream().filter(x -> x.taskName.matches(".*" + regex + ".*")).toList();
        list(filtered);
    }

    /**
     * Initialize a Tasks class.
     */
    public Tasks() {
        arr = new ArrayList<>();
    }

    /**
     * Convert a String from the hard disk storage file into a Task object.
     * @param s The string to be parsed.
     * @return The parsed Task.
     * @throws ParseFailException If the string is unable to be parsed.
     * @throws SquidDateException If the DateTime object is unable to be parsed.
     */
    public static Task parseTask(String s) throws ParseFailException, SquidDateException {
        String[] params = s.split(REGEX.TASK_SPLIT);
        Task task;
//        System.out.println("string " + s + " length " + params.length);
        switch (params.length) {
            case (5): // event
                task = new Event(params[2], new DateTime(params[3]), new DateTime(params[4]));
                task.setCompleted(Objects.equals(params[1], "X"));
                return task;
            case (4): // deadline
                task = new Deadline(params[2], new DateTime(params[3]));
                task.setCompleted(Objects.equals(params[1], "X"));
                return task;
            case (3): //todo
                task = new Todo(params[2]);
                task.setCompleted(Objects.equals(params[1], "X"));
                return task;
        }
        throw new ParseFailException(String.format(EXCEPTIONS.PARSE_FAIL, s));
    }

    /**
     * Saves the current list of tasks onto the hard disk.
     */
    public static void save() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.size(); i++) {
            String s = arr.get(i).parseStr();
            stringBuilder.append((s));
        }
        try {
            SquidFile.writeToFile(stringBuilder.toString(), false);
        } catch (IOException e) {
//            System.out.println(e);
        }
    }

    /**
     * Reads current tasks from hard disk storage.
     * @throws ParseFailException If a string is unable to be parsed.
     * @throws DuplicateTaskNameException If there are duplicate task names in the storage file.
     * @throws SquidDateException If a DateTime object is unable to be parsed.
     */
    public static void read() throws ParseFailException, DuplicateTaskNameException, SquidDateException {

        try {
            String tasks = SquidFile.readFromFile();
            String[] separated = tasks.split("\n");
            if (separated.length == 1 && separated[0] == "") {
                // empty file; do nothing
            } else {
                for (int i = 0; i < separated.length; i++) {
                    Tasks.add(parseTask(separated[i]));
                }
            }

        } catch (IOException e) {
        }
    }



}