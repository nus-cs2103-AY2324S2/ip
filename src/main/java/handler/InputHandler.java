package handler;

import action.*;
import action.task.DeadlineTask;
import action.task.EventTask;
import action.task.Task;
import action.task.TodoTask;
import exception.InvalidCommandException;

public class InputHandler {
    public static final InputHandler instance = new InputHandler();
    private InputHandler() {}
    public Action handleInput(String input) throws InvalidCommandException{
        String[] parsedInput = input.split(" ");
        String taskName;
        switch (parsedInput[0]) {
            case "list":
                return new ListAction();
            case "mark":
                if (parsedInput.length != 2) {
                    throw new InvalidCommandException("Incorrect number of arguments");
                }
                return new MarkAction(parsedInput[1]);
            case "unmark":
                if (parsedInput.length != 2) {
                    throw new InvalidCommandException("Incorrect number of arguments");
                }
                return new UnmarkAction(parsedInput[1]);
            case "todo":
                if (parsedInput.length < 2) {
                    throw new InvalidCommandException("Incorrect number of arguments");
                }
                taskName = input.substring(5);
                TodoTask todoTask = new TodoTask(taskName);
                return new TaskAction(todoTask);
            case "deadline":
                if (countByFlag(parsedInput) != 1) {
                    throw new InvalidCommandException("Illegal use of flags");
                }
                int flagIndex = input.indexOf(" /by ");
                if (flagIndex == -1) {
                    throw new InvalidCommandException("Illegal use of flags");
                }
                taskName = input.substring(9, flagIndex);
                String by = input.substring(flagIndex + 5);
                DeadlineTask deadlineTask = new DeadlineTask(taskName, by);
                return new TaskAction(deadlineTask);
            case "event":
                if (countFromFlag(parsedInput) != 1 || countToFlag(parsedInput) != 1) {
                    throw new InvalidCommandException("Illegal use of flags");
                }
                int fromIndex = input.indexOf(" /from ");
                int toIndex = input.indexOf(" /to ");
                if (fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
                    throw new InvalidCommandException("Illegal use of flags");
                }
                taskName = input.substring(6, fromIndex);
                String from = input.substring(fromIndex + 7, toIndex);
                String to = input.substring(toIndex + 5);
                EventTask eventTask = new EventTask(taskName, from, to);
                return new TaskAction(eventTask);
            default:
                return new TaskAction(input);
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
