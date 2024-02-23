package nihao.handler;

import java.time.LocalDateTime;

import nihao.action.Action;
import nihao.action.DeleteAction;
import nihao.action.ExitAction;
import nihao.action.FindAction;
import nihao.action.ListAction;
import nihao.action.MarkAction;
import nihao.action.TaskAction;
import nihao.action.UnmarkAction;
import nihao.action.task.DeadlineTask;
import nihao.action.task.EventTask;
import nihao.action.task.TodoTask;
import nihao.enums.Command;
import nihao.exception.IllegalArgumentException;
import nihao.exception.UnknownCommandException;

/**
 * Parses the input String and returns runnable Action objects.
 */
public class InputHandler {
    InputHandler() {}

    /**
     * @param input User input command.
     * @return An executable action to run the specified command.
     * @throws Exception When the command is not in an acceptable format.
     */
    public static Action handleInput(String input) throws Exception {
        String[] parsedInput = input.split(" ");
        String commandName = parsedInput[0];
        Command command = Command.getEnum(commandName);
        String taskName;
        switch (command) {
        case BYE:
            return new ExitAction();
        case LIST:
            return new ListAction();
        case MARK:
            if (parsedInput.length != 2) {
                throw new IllegalArgumentException("mark", 1);
            }
            return new MarkAction(parsedInput[1]);
        case UNMARK:
            if (parsedInput.length != 2) {
                throw new IllegalArgumentException("unmark", 1);
            }
            return new UnmarkAction(parsedInput[1]);
        case DELETE:
            if (parsedInput.length != 2) {
                throw new IllegalArgumentException("delete", 1);
            }
            return new DeleteAction(parsedInput[1]);
        case FIND:
            if (parsedInput.length != 2) {
                throw new IllegalArgumentException("find", 1);
            }
            return new FindAction(parsedInput[1]);
        case TODO:
            if (parsedInput.length < 2) {
                throw new IllegalArgumentException("todo requires at least 1 argument");
            }
            taskName = input.substring(5);
            TodoTask todoTask = new TodoTask(taskName);
            return new TaskAction(todoTask);
        case DEADLINE:
            if (countByFlag(parsedInput) != 1) {
                throw new IllegalArgumentException("deadline requires exactly 1 /by flag");
            }
            int byIndex = input.indexOf(" /by ");
            if (byIndex < 9) {
                throw new IllegalArgumentException("illegal use of /by flag");
            }
            taskName = input.substring(9, byIndex);
            LocalDateTime by = DateTimeHandler.handleInput(input.substring(byIndex + 5));
            DeadlineTask deadlineTask = new DeadlineTask(taskName, by);
            return new TaskAction(deadlineTask);
        case EVENT:
            if (countFromFlag(parsedInput) != 1 || countToFlag(parsedInput) != 1) {
                throw new IllegalArgumentException("'event' requires exactly 1 /from flag and 1 /to flag");
            }
            int fromIndex = input.indexOf(" /from ");
            int toIndex = input.indexOf(" /to ");
            if (fromIndex < 6 || toIndex < fromIndex + 7) {
                throw new IllegalArgumentException("illegal use of flags");
                // Todo: define "  " and " " more clearly
            }
            taskName = input.substring(6, fromIndex);
            LocalDateTime from = DateTimeHandler.handleInput(input.substring(fromIndex + 7, toIndex));
            LocalDateTime to = DateTimeHandler.handleInput(input.substring(toIndex + 5));
            EventTask eventTask = new EventTask(taskName, from, to);
            return new TaskAction(eventTask);
        default:
            throw new UnknownCommandException(input);
        }
    }

    private static int countByFlag(String[] parsedInput) {
        int counter = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            if (parsedInput[i].equals("/by")) {
                counter++;
            }
        }
        return counter;
    }

    private static int countFromFlag(String[] parsedInput) {
        int counter = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            if (parsedInput[i].equals("/from")) {
                counter++;
            }
        }
        return counter;
    }

    private static int countToFlag(String[] parsedInput) {
        int counter = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            if (parsedInput[i].equals("/to")) {
                counter++;
            }
        }
        return counter;
    }
}
