package chatteroo.parser;

import chatteroo.ChatterooException;
import chatteroo.commands.*;

import chatteroo.tasks.Task;
import chatteroo.tasks.ToDo;
import chatteroo.tasks.Deadline;
import chatteroo.tasks.Event;

/**
 * Parses the user input and returns the corresponding command.
 */
public class Parser {
    /**
     * Constructor for the Parser class.
     */
    public Parser() {
    }

    /**
     * Parses the user input and returns the corresponding command using a switch statement.
     * @param input The user input.
     * @return The corresponding command.
     * @throws Exception If the input is invalid.
     */
    public static Command parseInput(String input) throws ChatterooException {
        assert input != null : "Input should not be null";

        String[] inputArr = input.split(" ");
        String command = inputArr[0].toUpperCase();

        switch (command) {
        case "LIST":
            return new ListCommand();
        case "DONE":
            return new DoneCommand();
        case "MARK":
            if (inputArr.length < 2) {
                throw new ChatterooException("ChatterOOHNOO! A mark command's task number cannot be empty!");
            }
            int taskNum = Integer.parseInt(inputArr[1]);
            return new MarkCommand(taskNum);
        case "UNMARK":
            if (inputArr.length < 2) {
                throw new ChatterooException("ChatterOOHNOO! A unmark command's task number cannot be empty!");
            }
            taskNum = Integer.parseInt(inputArr[1]);
            return new UnmarkCommand(taskNum);
        case "DELETE":
            if (inputArr.length < 2) {
                throw new ChatterooException("ChatterOOHNOO! A delete command's task number cannot be empty!");
            }
            taskNum = Integer.parseInt(inputArr[1]);
            return new DeleteCommand(taskNum);
        case "FIND":
            if (inputArr.length < 2) {
                throw new ChatterooException("ChatterOOHNOO! A find's description cannot be empty!");
            }
            String keyword = input.substring(5);
            return new FindCommand(keyword);
        case "TODO":
            if (inputArr.length < 2) {
                throw new ChatterooException("ChatterOOHNOO! A todOO's description cannot be empty!");
            }
            input = input.substring(5);
            return Parser.parseTodo(input);
        case "DEADLINE":
            if (inputArr.length < 2 || inputArr[1].equals("/by")) {
                throw new ChatterooException("ChatterOOHNOO! A deadline's description cannot be empty!");
            }
            return Parser.parseDeadline(input);
        case "EVENT":
            if (inputArr.length < 2 || inputArr[1].equals("/from") || inputArr[1].equals("/to")) {
                throw new ChatterooException("ChatterOOHNOO! An event's description cannot be empty!");
            }
            return Parser.parseEvent(input);
        case "BYE":
            return new ExitCommand();
        default:
            throw new ChatterooException("ChatterOOHNOO! Crikey, Chatteroo don't get what you're on about mate.");
        }
    }

    /**
     * Parses the user input and returns the corresponding command for adding a todo task.
     * @param input The user input.
     * @return The corresponding command for adding a todo task.
     */
    public static Command parseTodo(String input) {
        Task newTodo = new ToDo(input);
        return new AddCommand(newTodo);
    }

    /**
     * Parses the user input and returns the corresponding command for adding a deadline task.
     * @param input The user input.
     * @return The corresponding command for adding a deadline task.
     * @throws ChatterooException If the deadline's date and time is empty.
     */
    public static Command parseDeadline(String input) throws ChatterooException {
        String[] deadlineInputArr = input.substring(9).split("/by");
        if (deadlineInputArr.length < 2) {
            throw new ChatterooException("ChatterOOHNOO! A deadline's date and time cannot be empty!");
        }
        String description = deadlineInputArr[0];
        String by = deadlineInputArr[1];
        Task newDeadline = new Deadline(description, by);
        return new AddCommand(newDeadline);
    }

    /**
     * Parses the user input and returns the corresponding command for adding an event task.
     * @param input The user input.
     * @return The corresponding command for adding an event task.
     * @throws ChatterooException If the event's date and time is empty.
     */
    public static Command parseEvent(String input) throws ChatterooException {
        input = input.substring(6);
        String[] eventInputArr = input.split("/from");
        if (eventInputArr.length < 2) {
            throw new ChatterooException("ChatterOOHNOO! An event's date and time cannot be empty!");
        }

        String description = eventInputArr[0];
        String[] timeArr = eventInputArr[1].split("/to");
        if (timeArr.length < 2) {
            throw new ChatterooException("ChatterOOHNOO! An event's date and time cannot be empty!");
        }
        String from = timeArr[0];
        String to = timeArr[1];
        Task newEvent = new Event(description, from, to);
        return new AddCommand(newEvent);
    }

    /**
     * Parses the task from the file and returns the corresponding task.
     * @param fileTask The task retrieved from the file.
     * @return The corresponding task.
     * @throws Exception If the task is invalid.
     */
    public static Task parseFileTasks(String fileTask) throws Exception {
        String[] inputArr = fileTask.split(" \\| ");
        String taskType = inputArr[0];
        String taskStatus = inputArr[1];
        String taskDescription = inputArr[2];
        Task newTask = null;

        if (taskType.equals("T")) {
            newTask = new ToDo(taskDescription);
        } else if (taskType.equals("D")) {
            String by = inputArr[3];
            newTask = new Deadline(taskDescription, by);
        } else if (taskType.equals("E")) {
            String from = inputArr[3];
            String to = inputArr[4];
            newTask = new Event(taskDescription, from, to);
        } else {
            throw new ChatterooException("ChatterOOHNOO! Chatteroo couldn't retrieve your tasks mate");
        }

        assert newTask != null : "Task should not be null";
        if (taskStatus.equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }
}
