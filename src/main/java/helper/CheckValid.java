package helper;

import java.time.LocalDateTime;
import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * Check if the given user input is valid
 */
public class CheckValid {
    private CheckValid() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Check if UserInput of task type to delete/mark is valid
     * throw exception if task type is not supported by ChatBot
     * @param taskType Type of task to be edited
     */
    public static void checkValidType(String taskType) {
        if (!(taskType.equals("EVENT") || taskType.equals("DEADLINE") || taskType.equals("TODO"))) {
            throw new TaylorException("<TaskType> only accepts EVENT/DEADLINE/TODO");
        }
    }

    /**
     * Check if UserInput of task index to delete/mark is valid
     * throw exception if index is invalid
     * @param no Check if index queried is valid
     * @param listToEdit list of task
     */
    public static void checkValidNum(int no, List<Task> listToEdit) {
        if (no < 0 || no > listToEdit.size()) {
            throw new TaylorException("Invalid task number");
        }
    }

    /**
     * For event task, check if starting time is before ending time
     * @param from starting date and time
     * @param to ending date and time
     */
    public static void checkTime(LocalDateTime from, LocalDateTime to) {
        if (from.isBefore(to)) {
            throw new TaylorException("End time cannot be before start time!");
        }
    }
}
