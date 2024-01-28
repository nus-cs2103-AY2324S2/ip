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
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Tasks {
    private static ArrayList<Task> arr;

    public static int size() {
        return arr.size();
    }

    public static Task get(int i) throws IncorrectIndexException {
        try {
            return arr.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectIndexException(EXCEPTIONS.INCORRECT_INDEX);
        }
    }
    public static void add(Task task) throws DuplicateTaskNameException {
        long dupes = arr.stream().filter(task1 -> task1.task.equals(task.task)).count();
        if (dupes != 0) {
            throw new DuplicateTaskNameException(
                    String.format(EXCEPTIONS.DUPLICATE_TASK_NAME, task.task));
        }
        arr.add(task);
    }

    public static Task delete(String index) throws IncorrectIndexException {
        int i = 0;
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
        List<Task> filtered = arr.stream().filter(x -> x.task.matches(".*" + regex + ".*")).toList();
        list(filtered);
    }

    public Tasks() {
        arr = new ArrayList<>();
    }

    public static Task parseTask(String s) throws ParseFailException, SquidDateException {
        String[] params = s.split(REGEX.TASK_SPLIT);
        Task task;
//        System.out.println("string " + s + " length " + params.length);
        switch (params.length) {
            case (5): // event
                task = new Event(params[2], new Date(params[3]), new Date(params[4]));
                task.setCompleted(Objects.equals(params[1], "X"));
                return task;
            case (4): // deadline
                task = new Deadline(params[2], new Date(params[3]));
                task.setCompleted(Objects.equals(params[1], "X"));
                return task;
            case (3): //todo
                task = new Todo(params[2]);
                task.setCompleted(Objects.equals(params[1], "X"));
                return task;
        }
        throw new ParseFailException(String.format(EXCEPTIONS.PARSE_FAIL, s));
    }


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
//            System.out.println(e);
        }
    }



}