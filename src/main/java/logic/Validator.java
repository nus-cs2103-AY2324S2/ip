package logic;

import static logic.Extractor.extractDeadlineParameters;
import static logic.Extractor.extractEventParameters;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import commands.Commands;
import exceptions.CommandNotFoundException;
import exceptions.ErrorMessages;
import exceptions.IncorrectParametersException;
import exceptions.MissingParametersException;
import exceptions.ParseDateException;
import tasks.TaskList;

/**
 * This class contains class methods used to validate parameters of specific commands
 */
public class Validator {

    /**
     * Calls the relevant command validation method depending on the command type
     * @param commandType Command type
     * @param command String input command
     * @param existingTaskList TaskList object containing current tasks
     * @throws Exception Exception to explain invalidity of command
     */
    public static void validateCommand(Commands commandType, String command, TaskList existingTaskList)
            throws Exception {
        switch (commandType) {
        case BYE:
            validateByeCommand(command);
            break;
        case LIST:
            validateListCommand(command);
            break;
        case MARK:
            validateMarkCommand(command, existingTaskList);
            break;
        case UNMARK:
            validateUnmarkCommand(command, existingTaskList);
            break;
        case DELETE:
            validateDeleteCommand(command, existingTaskList);
            break;
        case TODO:
            validateTodoCommand(command);
            break;
        case DEADLINE:
            validateDeadlineCommand(command);
            break;
        case EVENT:
            validateEventCommand(command);
            break;
        case FIND:
            validateFindCommand(command);
            break;
        case HELP:
            validateHelpCommand(command);
            break;
        default:
            throw new CommandNotFoundException(ErrorMessages.COMMAND_NOT_FOUND);
        }
    }

    /**
     * Takes in 'bye' command as a string and checks if it is valid
     * @param command Bye command entered by user
     * @throws CommandNotFoundException if the command contains any parameters
     */
    public static void validateByeCommand(String command) throws CommandNotFoundException {
        // command contains more than the 'bye' word
        if (command.split(" ").length > 1) {
            throw new CommandNotFoundException(ErrorMessages.COMMAND_NOT_FOUND);
        }
    }

    /**
     * Takes in a 'list' command and checks if it is valid
     * @param command List command entered by user
     * @throws CommandNotFoundException if the command contains any parameters
     */
    public static void validateListCommand(String command) throws CommandNotFoundException {
        // command contains more than the 'list' word
        if (command.split(" ").length > 1) {
            throw new CommandNotFoundException(ErrorMessages.COMMAND_NOT_FOUND);
        }
    }

    /**
     * Takes in a 'mark' command and checks if it is valid
     * @param command Mark command entered by user
     * @param existingTaskList User's current TaskList object
     * @throws IncorrectParametersException if the number of parameters in the command is not 1
     * @throws IndexOutOfBoundsException if the index provided is an integer but out of range
     * @throws NumberFormatException if the parameter provided is not an integer
     */
    public static void validateMarkCommand(String command, TaskList existingTaskList)
            throws IncorrectParametersException, NumberFormatException {
        // command contains more than the 'mark' word and another String
        if (command.split(" ").length > 2) {
            throw new IncorrectParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]);
            if (taskNum > existingTaskList.getNumTasks()) {
                // command task number does not exist
                throw new IndexOutOfBoundsException(ErrorMessages.TASK_NUMBER_DOES_NOT_EXIST);
            }
        } catch (NumberFormatException e) {
            // command task number cannot be parsed into an int
            throw new NumberFormatException(ErrorMessages.TASK_NUMBER_PARSE_ERROR);
        }
    }

    /**
     * Takes in an 'unmark' command and checks if it is valid
     * @param command Unmark command entered by user
     * @param existingTaskList User's current TaskList object
     * @throws IncorrectParametersException if the number of parameters in the command is not 1
     * @throws IndexOutOfBoundsException if the index provided is an integer but out of range
     * @throws NumberFormatException if the parameter provided is not an integer
     */
    public static void validateUnmarkCommand(String command, TaskList existingTaskList)
            throws IncorrectParametersException, NumberFormatException {
        // command contains more than the 'unmark' word and another String
        if (command.split(" ").length > 2) {
            throw new IncorrectParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]);
            if (taskNum > existingTaskList.getNumTasks()) {
                // command task number does not exist
                throw new IndexOutOfBoundsException(ErrorMessages.TASK_NUMBER_DOES_NOT_EXIST);
            }
        } catch (NumberFormatException e) {
            // command task number cannot be parsed into an int
            throw new NumberFormatException(ErrorMessages.TASK_NUMBER_PARSE_ERROR);
        }
    }

    /**
     * Takes in a 'delete' command and checks if it is valid
     * @param command Delete command entered by user
     * @param existingTaskList User's current TaskList object
     * @throws IncorrectParametersException if the number of parameters in the command is not 1
     * @throws IndexOutOfBoundsException if the index provided is an integer but out of range
     * @throws NumberFormatException if the parameter provided is not an integer
     */
    public static void validateDeleteCommand(String command, TaskList existingTaskList)
            throws IncorrectParametersException, NumberFormatException {
        // command contains more than the 'delete' word and another string
        if (command.split(" ").length > 2) {
            throw new IncorrectParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }
        try {
            int taskNum = Integer.parseInt(command.split(" ")[1]);
            if (taskNum > existingTaskList.getNumTasks()) {
                // command task number does not exist
                throw new IndexOutOfBoundsException(ErrorMessages.TASK_NUMBER_DOES_NOT_EXIST);
            }
        } catch (NumberFormatException e) {
            // command task number cannot be parsed into an int
            throw new NumberFormatException(ErrorMessages.TASK_NUMBER_PARSE_ERROR);
        }
    }

    /**
     * Takes in a 'delete all' command and checks if it is valid
     * @param command Mark command entered by user
     * @throws IncorrectParametersException if the command contains any parameters
     */
    public static void validateDeleteAllCommand(String command)
            throws IncorrectParametersException {
        // command contains more than the 'delete' and 'all' words
        if (command.split(" ").length > 2) {
            throw new IncorrectParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }
    }

    /**
     * Takes in a 'todo' command and checks if it is valid
     * @param command Todo command entered by user
     * @throws IncorrectParametersException if the command contains any date parameters
     * @throws MissingParametersException if the command is missing a task description parameter
     */
    public static void validateTodoCommand(String command)
            throws MissingParametersException, IncorrectParametersException {
        String todoDescription = command.replaceFirst("(?i)todo", "").trim();
        if (todoDescription.isEmpty()) {
            throw new MissingParametersException(ErrorMessages.MISSING_TASK_DESCRIPTION);
        }
        if (todoDescription.contains("/by")) {
            throw new IncorrectParametersException(ErrorMessages.DUE_DATE_NOT_NEEDED);
        }
        if (todoDescription.contains("/from") || todoDescription.contains("/to")) {
            throw new IncorrectParametersException(ErrorMessages.FROM_TO_DATE_NOT_NEEDED);
        }
    }

    /**
     * Takes in a 'deadline' command and checks if it is valid
     * @param command Deadline command entered by user
     * @throws MissingParametersException if the command is missing a description or due date parameter
     * @throws IncorrectParametersException if the command contains any 'from' or 'to' date parameters
     * @throws DateTimeParseException if the date parameter provided is not in yyyy-mm-dd format
     * @throws ParseDateException if the date parameter provided is not in yyyy-mm-dd format
     */
    public static void validateDeadlineCommand(String command)
            throws MissingParametersException, IncorrectParametersException,
            DateTimeParseException, ParseDateException {
        String[] splitCommand = command.split(" ");

        if (splitCommand.length < 4) {
            // command has less than required parameters
            // requirement: deadline (description) /by (date) -> 4 parameters
            throw new MissingParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }

        if (!command.contains("/by")) {
            throw new MissingParametersException(ErrorMessages.MISSING_DUE_DATE);
        }
        if (command.contains("/from") || command.contains("/to")) {
            throw new IncorrectParametersException(ErrorMessages.FROM_TO_DATE_NOT_NEEDED);
        }

        int secondLastIndex = splitCommand.length - 2;
        // command has more than one parameter representing due date
        if (!splitCommand[secondLastIndex].equals("/by")) {
            throw new IncorrectParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }
        try {
            String[] parameters = new String[2];
            parameters = extractDeadlineParameters(command);
            String deadlineDescription = parameters[0];
            String deadlineDueDate = parameters[1];
            if (deadlineDescription.isEmpty()) {
                throw new MissingParametersException(ErrorMessages.MISSING_TASK_DESCRIPTION);
            }
            LocalDate deadlineDueDateLocal = LocalDate.parse(deadlineDueDate);
        } catch (DateTimeParseException e) {
            throw new ParseDateException(ErrorMessages.INCORRECT_DATE_FORMAT);
        }
    }

    /**
     * Takes in an 'event' command and checks if it is valid
     * @param command Event command entered by user
     * @throws MissingParametersException if the command is missing a description, from date, or to date parameter
     * @throws IncorrectParametersException if the command contains any due date parameter
     * @throws DateTimeParseException if the date parameter provided is not in yyyy-mm-dd format
     * @throws ParseDateException if the date parameter provided is not in yyyy-mm-dd format
     */
    public static void validateEventCommand(String command)
            throws MissingParametersException, IncorrectParametersException,
            DateTimeParseException, ParseDateException {
        String[] splitCommand = command.split(" ");

        if (!command.contains("/from")) {
            throw new MissingParametersException(ErrorMessages.MISSING_FROM_DATE);
        }
        else if (!command.contains("/to")) {
            throw new MissingParametersException(ErrorMessages.MISSING_TO_DATE);
        }
        if (command.contains("/by")) {
            throw new IncorrectParametersException(ErrorMessages.DUE_DATE_NOT_NEEDED);
        }

        if (splitCommand.length < 6) {
            // command has less than required parameters
            // requirement: event (description) /from (date) /to (date)
            throw new MissingParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }

        int secondLastIndex = splitCommand.length - 2;
        int fourthLastIndex = splitCommand.length - 4;
        if (!splitCommand[secondLastIndex].equals("/to") || !splitCommand[fourthLastIndex].equals("/from")) {
            // command has more or less than one parameter representing to date
            // or more or less than one parameter representing from date
            throw new IncorrectParametersException(ErrorMessages.INCORRECT_PARAMETERS);
        }

        String[] parameters = new String[3];
        parameters = extractEventParameters(command);
        String eventDescription = parameters[0];
        String eventFrom = parameters[1];
        String eventTo = parameters[2];
        if (eventDescription.isEmpty()) {
            throw new MissingParametersException(ErrorMessages.MISSING_TASK_DESCRIPTION);
        }
        if (eventFrom.isEmpty()) {
            System.out.println(ErrorMessages.MISSING_FROM_DATE);
            throw new MissingParametersException(ErrorMessages.MISSING_FROM_DATE);
        }
        else if (eventTo.isEmpty()) {
            System.out.println(ErrorMessages.MISSING_TO_DATE);
            throw new MissingParametersException(ErrorMessages.MISSING_TO_DATE);
        }

        try {
            LocalDate eventFromDateLocal = LocalDate.parse(eventFrom);
            LocalDate eventToDateLocal = LocalDate.parse(eventTo);
        } catch (DateTimeParseException e) {
            throw new ParseDateException(ErrorMessages.INCORRECT_DATE_FORMAT);
        }
    }

    /**
     * Takes in a 'find' command and checks if it is valid
     * @param command Find command entered by user
     */
    public static void validateFindCommand(String command) {
        // no validation needed
    }

    /**
     * Takes in a 'help' command and checks if it is valid
     * @param command Help command entered by user
     * @throws CommandNotFoundException if the command contains any parameters
     */
    public static void validateHelpCommand(String command) throws CommandNotFoundException {
        // command contains more than the 'list' word
        if (command.split(" ").length > 1) {
            throw new CommandNotFoundException(ErrorMessages.COMMAND_NOT_FOUND);
        }
    }

}
