package fishstock.task;

import java.util.Arrays;

import fishstock.Command;
import fishstock.UserInput;

/**
 * A Factory class to initialize Tasks.
 */
public class TaskFactory {
    /**
     * Initializes a Task from a UserInput.
     */
    public static Task of(UserInput input) throws TaskException {
        Command command = input.getCommandType();

        assert Arrays.asList(Command.TODO, Command.DEADLINE, Command.EVENT)
                .contains(command) : "Attempted to add an invalid Task";

        Task task = null;
        switch (command) {
        case TODO:
            task = Todo.of(input);
            break;
        case DEADLINE:
            task = Deadline.of(input);
            break;
        case EVENT:
            task = Event.of(input);
            break;
        default:
            // Not possible as checked beforehand.
        }
        return task;
    }

    /**
     * Initializes a Task from stored format.
     */
    public static Task fromStorageString(String line) throws TaskException {
        String[] arr = line.split("\\|");
        Command command = Command.findShortened(arr[0]);
        boolean isDone = getIsDone(arr[arr.length - 1]);

        Task task;
        switch (command) {
        case TODO:
            task = new Todo(arr[1], isDone);
            break;
        case DEADLINE:
            task = new Deadline(arr[1], isDone, TaskParser.parseDate(arr[2]));
            break;
        case EVENT:
            task = new Event(arr[1], isDone, TaskParser.parseDate(arr[2]), TaskParser.parseDate(arr[3]));
            break;
        default:
            throw new TaskException("Wrong format..");
        }

        return task;
    }

    private static boolean getIsDone(String markStatus) throws TaskException {
        if ("1".equals(markStatus)) {
            return true;
        } else if ("0".equals(markStatus)) {
            return false;
        } else {
            throw new TaskException("Mark status corrupted..");
        }
    }
}
