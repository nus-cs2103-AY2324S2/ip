package Tasks;

import CONSTANTS.CORRECT_USAGE;
import CONSTANTS.EXCEPTIONS;
import Exceptions.DuplicateTaskNameException;
import Exceptions.IncorrectIndexException;
import Exceptions.ParseFailException;

import java.util.ArrayList;
import java.util.Objects;

public class Tasks {
    private static ArrayList<Task> arr;

    public static int size() {
        return arr.size();
    }

    public static Task get(int i) {
        return arr.get(i);
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

    public static void list() {
        for (int i = 0; i < Tasks.size(); i++) {
            Task currTask = Tasks.get(i);
            System.out.printf("%d: %s%n", i + 1, currTask);
        }
    }

    public Tasks() {
        arr = new ArrayList<>();
    }

    public Task parseTask(String s) throws ParseFailException {
        String[] params = s.split(" ");
        Task task;
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
        throw new ParseFailException(EXCEPTIONS.PARSE_FAIL);
    }


    public static void save() {

    }
}