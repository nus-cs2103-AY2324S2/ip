package logic;

import commands.Commands;
import exceptions.*;
import javafx.util.Pair;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;

import static logic.Extractor.*;
import static logic.Validator.*;

/**
 * The Parser class parses user input (a command) and executes the command accordingly
 */
public class Parser {
    private static final int CONTINUE_PROGRAM = 1;
    private static final int TERMINATE_PROGRAM = 0;

    private static String dividerText = "____________________________________________________________\n";

    /**
     * Takes the command as a string and the existing TaskList object
     * Processes the command and executes it on TaskList object
     * Returns an integer (0 or 1) to indicate if it is a terminating command
     *
     * @param input            Command entered by user
     * @param existingTaskList User's current TaskList object
     * @return 0 or 1
     * @throws NumberFormatException     If number cannot be parsed (a non-number is entered)
     * @throws IndexOutOfBoundsException If task number does not exist in list
     */
    public static Pair<Integer, String> parseAndExecuteCommand(String input, TaskList existingTaskList) {
        String[] splitInput = input.split(" ");
        String commandType = splitInput[0].toLowerCase();
        System.out.print(dividerText);
        String response = "default response";
        try {
            switch (commandType) {
            case "bye":
                return parseAndExecuteBye(input, existingTaskList);
            case "list":
                return parseAndExecuteList(input, existingTaskList);
            case "mark":
                return parseAndExecuteMark(input, existingTaskList);
            case "unmark":
                return parseAndExecuteUnmark(input, existingTaskList);
            case "delete":
                if (splitInput[1].equalsIgnoreCase("all")) {
                    return parseAndExecuteDeleteAll(input, existingTaskList);
                } else {
                    return parseAndExecuteDelete(input, existingTaskList);
                }
            case "todo":
                return parseAndExecuteTodo(input, existingTaskList);
            case "deadline":
                return parseAndExecuteDeadline(input, existingTaskList);
            case "event":
                return parseAndExecuteEvent(input, existingTaskList);
            case "find":
                return parseAndExecuteFind(input, existingTaskList);
            case "help":
                return parseAndExecuteHelp(input, existingTaskList);
            default:
                response = "Invalid command entered. Type 'help' for list of commands";
                return new Pair<>(CONTINUE_PROGRAM, response);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response = e.getMessage();
            return new Pair<>(TERMINATE_PROGRAM, response);
        }
    }

    public static Pair<Integer, String> parseAndExecuteBye(String input, TaskList existingTaskList) throws CommandNotFoundException {
        String response;
        try {
            validateByeCommand(input);
            response = Ui.exit();
            return new Pair<>(TERMINATE_PROGRAM, response);
        } catch (CommandNotFoundException e) {
            response = e.getMessage();
            return new Pair<>(CONTINUE_PROGRAM, response);
        }
    }

    public static Pair<Integer, String> parseAndExecuteList(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateListCommand(input);
            response = existingTaskList.listTasks();
        } catch (CommandNotFoundException e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteMark(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateMarkCommand(input, existingTaskList);
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            response = existingTaskList.markTaskDone(taskNum);
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteUnmark(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateMarkCommand(input, existingTaskList);
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            response = existingTaskList.markTaskUndone(taskNum);
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteDelete(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateDeleteCommand(input, existingTaskList);
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            response = existingTaskList.deleteTask(taskNum);
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteDeleteAll(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateDeleteAllCommand(input);
            response = existingTaskList.deleteAllTasks();
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteTodo(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateTodoCommand(input);
            String todoDescription = input.replace("todo", "").trim();
            response = existingTaskList.addTodo(todoDescription);
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteDeadline(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateDeadlineCommand(input);
            String[] parameters = extractDeadlineParameters(input);
            String deadlineDescription = parameters[0];
            LocalDate deadlineDueDateLocal = LocalDate.parse(parameters[1]);
            response = existingTaskList.addDeadline(deadlineDescription, deadlineDueDateLocal);
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteEvent(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateEventCommand(input);
            String[] parameters = extractEventParameters(input);
            String eventDescription = parameters[0];
            LocalDate eventFromDateLocal = LocalDate.parse(parameters[1]);
            LocalDate eventToDateLocal = LocalDate.parse(parameters[2]);
            response = existingTaskList.addEvent(eventDescription, eventFromDateLocal, eventToDateLocal);
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteFind(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateFindCommand(input);
            String searchTerm = extractSearchTerm(input);
            response = existingTaskList.findAndPrintTasks(searchTerm);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    public static Pair<Integer, String> parseAndExecuteHelp(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateHelpCommand(input);
            response = "LIST OF COMMANDS\n" // USE STREAM TO PRINT COMMAND HELP MESSAGES
                    + Commands.TODO.getHelpMessage() + "\n"
                    + Commands.DEADLINE.getHelpMessage() + "\n"
                    + Commands.EVENT.getHelpMessage() + "\n"
                    + Commands.LIST.getHelpMessage() + "\n"
                    + Commands.MARK.getHelpMessage() + "\n"
                    + Commands.UNMARK.getHelpMessage() + "\n"
                    + Commands.DELETE.getHelpMessage() + "\n"
                    + Commands.FIND.getHelpMessage() + "\n"
                    + Commands.BYE.getHelpMessage();
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }
}

