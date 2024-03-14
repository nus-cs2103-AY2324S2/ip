package logic;

import static logic.Extractor.extractDeadlineParameters;
import static logic.Extractor.extractEventParameters;
import static logic.Extractor.extractSearchTerm;
import static logic.Extractor.extractTodoParameters;
import static logic.Validator.validateByeCommand;
import static logic.Validator.validateDeadlineCommand;
import static logic.Validator.validateDeleteAllCommand;
import static logic.Validator.validateDeleteCommand;
import static logic.Validator.validateEventCommand;
import static logic.Validator.validateFindCommand;
import static logic.Validator.validateHelpCommand;
import static logic.Validator.validateListCommand;
import static logic.Validator.validateMarkCommand;
import static logic.Validator.validateTodoCommand;

import java.time.LocalDate;
import java.util.stream.Stream;

import commands.Commands;
import exceptions.CommandNotFoundException;
import javafx.util.Pair;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

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
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     * @throws NumberFormatException If number cannot be parsed (a non-number is entered)
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
                return parseAndExecuteBye(input);
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

    /**
     * Takes in a 'bye' command and executes if valid
     * @param input Bye command entered by user
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
    public static Pair<Integer, String> parseAndExecuteBye(String input) {
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

    /**
     * Takes in a 'list' command and executes if valid
     * @param input List command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
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

    /**
     * Takes in a 'mark' command and executes if valid
     * @param input Mark command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
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

    /**
     * Takes in an 'unmark' command and executes if valid
     * @param input Unmark command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
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

    /**
     * Takes in a 'delete' command and executes if valid
     * @param input Delete command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
    public static Pair<Integer, String> parseAndExecuteDelete(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateDeleteCommand(input, existingTaskList);
            int taskNum = Integer.parseInt(input.split(" ")[1]);
            int numTasks = existingTaskList.getNumTasks();
            response = existingTaskList.deleteTask(taskNum);
            int newNumTasks = numTasks - 1;
            assert existingTaskList.getNumTasks() == newNumTasks;
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    /**
     * Takes in a 'delete all' command and executes if valid
     * @param input Delete all command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
    public static Pair<Integer, String> parseAndExecuteDeleteAll(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateDeleteAllCommand(input);
            response = existingTaskList.deleteAllTasks();
            assert existingTaskList.getNumTasks() == 0;
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    /**
     * Takes in a 'todo' command and executes if valid
     * @param input Todo command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
    public static Pair<Integer, String> parseAndExecuteTodo(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateTodoCommand(input);
            String[] parameters = extractTodoParameters(input);
            String todoDescription = parameters[0];
            response = existingTaskList.addTodo(todoDescription);
            Storage.saveTaskList(existingTaskList);
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }

    /**
     * Takes in a 'deadline' command and executes if valid
     * @param input Deadline command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
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

    /**
     * Takes in a 'event' command and executes if valid
     * @param input Event command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
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

    /**
     * Takes in a 'find' command and executes if valid
     * @param input Find command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
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

    /**
     * Takes in a 'help' command and executes if valid
     * @param input Help command entered by user
     * @param existingTaskList User's current TaskList object
     * @return Pair containing the continue status of the execution (1 for continue, 0 for terminate)
     *     and the String response from the execution
     */
    public static Pair<Integer, String> parseAndExecuteHelp(String input, TaskList existingTaskList) {
        String response = "default response";
        try {
            validateHelpCommand(input);
            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("LIST OF COMMANDS\n");
            Stream<String> responseStream = Stream.of(Commands.values()).map(Commands::getHelpMessage);
            responseStream.forEach(str -> responseBuilder.append(str).append("\n"));
            response = responseBuilder.toString();
        } catch (Exception e) {
            response = e.getMessage();
        }
        return new Pair<>(CONTINUE_PROGRAM, response);
    }
}
