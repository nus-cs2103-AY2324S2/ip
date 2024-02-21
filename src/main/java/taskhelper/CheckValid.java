package taskhelper;

import java.time.LocalDateTime;
import java.util.List;

import exceptions.TaylorException;
import tasks.Task;

/**
 * Checks if the given user input is valid.
 */
public class CheckValid {
    private CheckValid() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * Checks if UserInput of task type to delete/mark is valid
     * throw exception if task type is not supported by ChatBot.
     *
     * @param taskType Type of task to be edited
     */
    public static void checkValidType(String taskType) {
        if (!(taskType.equals("EVENT") || taskType.equals("DEADLINE") || taskType.equals("TODO"))) {
            throw new TaylorException("\n"
                    + "The weight of the world, it pulls me down,\n"
                    + "More than I can handle, I start to drown.\n"
                    + "==============================\n"
                    + "<TaskType> only accepts EVENT/DEADLINE/TODO");
        }
    }

    /**
     * Checks if UserInput of task index to delete/mark is valid
     * throw exception if index is invalid.
     *
     * @param no Check if index queried is valid
     * @param listToEdit list of task
     */
    public static void checkValidNum(int no, List<Task> listToEdit) {
        if (no < 0 || no > listToEdit.size()) {
            throw new TaylorException("\n"
                    + "For in the darkness, there's a light that shines\n"
                    + "A beacon of hope in these troubled times.\n"
                    + "==============================\n"
                    + "Invalid task number");
        }
    }

    /**
     * Checks if starting time is before ending time for event task.
     *
     * @param from starting date and time
     * @param to ending date and time
     */
    public static void checkTime(LocalDateTime from, LocalDateTime to) {
        if (from.isAfter(to)) {
            throw new TaylorException("\n"
                    + "For in the tapestry moments weave,\n"
                    + "Time reveals what our heart believes.\n"
                    + "==============================\n"
                    + "End time cannot be before start time!");
        }
    }
}
