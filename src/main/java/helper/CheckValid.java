package helper;

import exceptions.TaylorException;
import tasks.Task;

import java.time.LocalDateTime;
import java.util.List;

public class CheckValid {
    private CheckValid() {
        throw new AssertionError("Constructor is not allowed");
    }

    public static void checkValidType(String taskType) {
        if (!(taskType.equals("EVENT") || taskType.equals("DEADLINE") || taskType.equals("TODO"))) {
            throw new TaylorException("<TaskType> only accepts EVENT/DEADLINE/TODO");
        }
    }

    public static void checkValidNum(int no, List<Task> listToEdit) {
        if (no < 0 || no > listToEdit.size()) {
            throw new TaylorException("Invalid task number");
        }
    }

    public static void checkField(String action, String time) {
        boolean isActionEmpty = action.trim().isBlank();
        boolean isTimeEmpty = time.trim().isBlank();
        if (isActionEmpty || isTimeEmpty) {
            throw new TaylorException("Invalid format. Please type in the following format: "
                    + "event <action> /from <time> /to <time>");
        }
    }

    public static void checkTime(LocalDateTime from, LocalDateTime to) {
        if (from.isBefore(to)) {
            throw new TaylorException("End time cannot be before start time!");
        }
    }
}
