package Tasks;

import CONSTANTS.CORRECT_USAGE;
import CONSTANTS.EXCEPTIONS;
import Exceptions.DuplicateTaskNameException;
import Exceptions.IncorrectIndexException;
import Exceptions.NotEnoughInputsException;

import java.util.ArrayList;

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


}