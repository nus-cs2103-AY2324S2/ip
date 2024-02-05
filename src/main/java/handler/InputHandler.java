package handler;

import action.*;
import action.task.DeadlineTask;
import action.task.EventTask;
import action.task.TodoTask;
import exception.IllegalArgumentException;
import exception.UnknownCommandException;

public class InputHandler {
    public static final InputHandler instance = new InputHandler();
    private InputHandler() {}
    public Action handleInput(String input) throws Exception{
        String[] parsedInput = input.split(" ");
        String taskName;
        switch (parsedInput[0]) {
            case "list":
                return new ListAction();
            case "mark":
                if (parsedInput.length != 2) {
                    throw new IllegalArgumentException("mark", 1);
                }
                return new MarkAction(parsedInput[1]);
            case "unmark":
                if (parsedInput.length != 2) {
                    throw new IllegalArgumentException("unmark", 1);
                }
                return new UnmarkAction(parsedInput[1]);
            case "delete":
                if (parsedInput.length != 2) {
                    throw new IllegalArgumentException("delete", 1);
                }
                return new DeleteAction(parsedInput[1]);
            case "todo":
                if (parsedInput.length < 2) {
                    throw new IllegalArgumentException("todo requires at least 1 argument");
                }
                taskName = input.substring(5);
                TodoTask todoTask = new TodoTask(taskName);
                return new TaskAction(todoTask);
            case "deadline":
                if (countByFlag(parsedInput) != 1) {
                    throw new IllegalArgumentException("deadline requires exactly 1 /by flag");
                }
                int flagIndex = input.indexOf(" /by ");
                if (flagIndex == -1) {
                    throw new IllegalArgumentException("illegal use of /by flag");
                }
                taskName = input.substring(9, flagIndex);
                String by = input.substring(flagIndex + 5);
                DeadlineTask deadlineTask = new DeadlineTask(taskName, by);
                return new TaskAction(deadlineTask);
            case "event":
                if (countFromFlag(parsedInput) != 1 || countToFlag(parsedInput) != 1) {
                    throw new IllegalArgumentException("'event' requires exactly 1 /from flag and 1 /to flag");
                }
                int fromIndex = input.indexOf(" /from ");
                int toIndex = input.indexOf(" /to ");
                if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
                    throw new IllegalArgumentException("illegal use of flags");
                }
                taskName = input.substring(6, fromIndex);
                String from = input.substring(fromIndex + 7, toIndex);
                String to = input.substring(toIndex + 5);
                EventTask eventTask = new EventTask(taskName, from, to);
                return new TaskAction(eventTask);
            default:
                throw new UnknownCommandException(input);
        }
    }

    private int countByFlag(String[] parsedInput) {
        int counter = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            if (parsedInput[i].equals("/by")) {
                counter++;
            }
        }
        return counter;
    }

    private int countFromFlag(String[] parsedInput) {
        int counter = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            if (parsedInput[i].equals("/from")) {
                counter++;
            }
        }
        return counter;
    }

    private int countToFlag(String[] parsedInput) {
        int counter = 0;
        for (int i = 0; i < parsedInput.length; i++) {
            if (parsedInput[i].equals("/to")) {
                counter++;
            }
        }
        return counter;
    }
}
