package haro;

import haro.command.*;
import haro.exception.EmptyCommandException;
import haro.exception.EmptyTaskException;
import haro.exception.InvalidArgsException;
import haro.exception.InvalidCommandException;
import haro.exception.MissingDuedateException;
import haro.exception.MissingEventTimeException;
import haro.task.Deadline;
import haro.task.Event;
import haro.task.Task;
import haro.task.ToDo;

/**
 * The parser class is responsible for parsing user input and creating corresponding Command objects
 * It handles the interpretation of commands and their arguments, throwing exceptions for invalid input.
 */
public class Parser {
    enum Instruction {
        BYE,
        NONE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        EDIT,

    }

    /**
     * Parses the user input and returns the corresponding Command object
     * @param input The users input through the command line
     * @return A Command object corresponding to the user input
     * @throws Exception If there are errors in the user input or command execution
     */
    public static Command parseCommand(String input) throws Exception {
        Instruction instruction = Instruction.NONE;
        String[] inputArgs = input.split(" ", 2);
        String instructWord = inputArgs[0].toLowerCase().trim();
        Command resultCommand = null;

        switch (instructWord) {
        case "bye":
            instruction = Instruction.BYE;
            resultCommand = processByeCommand();
            break;
        case "list":
            instruction = Instruction.LIST;
            resultCommand = processListCommand();
            break;
        case "mark":
            instruction = Instruction.MARK;
            checkArguments(inputArgs);
            resultCommand = processMarkCommand(inputArgs[1]);
            break;
        case "unmark":
            instruction = Instruction.UNMARK;
            checkArguments(inputArgs);
            resultCommand = processUnmarkCommand(inputArgs[1]);
            break;
        case "todo":
            instruction = Instruction.TODO;
            checkArguments(inputArgs);
            resultCommand = processTodoCommand(inputArgs[1]);
            break;
        case "deadline":
            instruction = Instruction.DEADLINE;
            checkArguments(inputArgs);
            resultCommand = processDeadlineCommand(inputArgs[1]);
            break;
        case "event":
            instruction = Instruction.EVENT;
            checkArguments(inputArgs);
            resultCommand = processEventCommand(inputArgs[1]);
            break;
        case "delete":
            instruction = Instruction.DELETE;
            checkArguments(inputArgs);
            resultCommand = processDeleteCommand(inputArgs[1]);
            break;
        case "find":
            instruction = Instruction.FIND;
            checkArguments(inputArgs);
            resultCommand = processFindCommand(inputArgs[1]);
            break;
        case "edit":
            instruction = Instruction.EDIT;
            checkArguments(inputArgs);
            resultCommand = processEditCommand(inputArgs[1]);
            break;
        default:
            instruction = Instruction.NONE;
            processUnknownCommand(inputArgs[0]);
        }

        if (resultCommand == null) {
            throw new InvalidCommandException("Sorry, please type a valid command\n");
        }
        // Assert resultCommand is valid command
        assert resultCommand instanceof Command : "Parser.parse should return a Command object";

        return resultCommand;
    }

    private static Command processByeCommand() {
        ByeCommand byeCommand = new ByeCommand();
        return byeCommand;
    }

    private static Command processListCommand() {
        ListCommand listCommand = new ListCommand();
        return listCommand;
    }

    private static Command processMarkCommand(String userInput) throws InvalidArgsException {
        if (!isNumeric(userInput)) {
            throw new InvalidArgsException("Please input a number for the task you want to mark!\n");
        }

        int taskNumber = Integer.parseInt(userInput) - 1;
        MarkCommand markCommand = new MarkCommand(taskNumber);
        return markCommand;
    }

    private static Command processUnmarkCommand(String userInput) throws InvalidArgsException {
        if (!isNumeric(userInput)) {
            throw new InvalidArgsException("Please input a number for the task you want to unmark!\n");
        }

        int taskNumber = Integer.parseInt(userInput) - 1;
        UnmarkCommand unmarkCommand = new UnmarkCommand(taskNumber);
        return unmarkCommand;
    }

    private static Command processTodoCommand(String userInput) {
        Task newTodo = new ToDo(userInput.trim());
        AddCommand addCommand = new AddCommand(newTodo);
        return addCommand;
    }

    private static Command processDeadlineCommand(String userInput) throws  MissingDuedateException {
        if (!userInput.contains("/by")) {
            throw new MissingDuedateException("Please specify a due date using '/by'!\n");
        }

        String[] taskArr = userInput.split("/by", 2);
        String taskName = taskArr[0].trim();
        String taskDue = taskArr[1].trim();
        Task newDeadline = new Deadline(taskName, taskDue);
        AddCommand addCommand = new AddCommand(newDeadline);
        return addCommand;
    }

    private static Command processEventCommand(String userInput) throws  Exception {
        if (!userInput.contains("/from")) {
            throw new MissingEventTimeException("Please specify a start date using '/from'!\n");
        } else if (!userInput.contains("/to")) {
            throw new MissingEventTimeException("Please specify an end date using '/to'!\n");
        }

        String[] taskArr = userInput.split("/from", 2);
        String taskName = taskArr[0].trim();
        String taskDur = taskArr[1];

        String[] taskTime = taskDur.split("/to", 2);
        String taskFrom = taskTime[0].trim();
        String taskTo = taskTime[1].trim();

        Task newEvent = new Event(taskName, taskFrom, taskTo);
        AddCommand addCommand = new AddCommand(newEvent);
        return addCommand;
    }

    private static Command processDeleteCommand(String userInput) throws InvalidArgsException{
        if (!isNumeric(userInput)) {
            throw new InvalidArgsException("Please input a number for the task you want to delete!\n");
        }

        int taskNumber = Integer.parseInt(userInput) - 1;
        DeleteCommand deleteCommand = new DeleteCommand(taskNumber);
        return deleteCommand;
    }

    private static Command processFindCommand(String userInput) throws InvalidArgsException {
        userInput = userInput.trim();

        if (userInput.equals("")) {
            throw new InvalidArgsException("Please input a valid search string\n");
        }

        FindCommand findCommand = new FindCommand(userInput);
        return findCommand;
    }

    private static Command processEditCommand(String userInput) throws InvalidArgsException {
        String[] editArgs = userInput.split("\\s", 2);

        if (editArgs.length < 2) {
            throw new InvalidArgsException("Please use edit command in the following format:\n"
                    + "edit {taskNumber} {portion to edit} {updated value}");
        }

        String taskNumber = editArgs[0].trim();
        String editInstruction = editArgs[1].trim();
        if (!isNumeric(taskNumber)) {
            throw new InvalidArgsException("Please input a number for the task you want to edit!\n");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;
        String[] editCommandArgs = editInstruction.split("\\s", 2);
        String portionToEdit = editCommandArgs[0].trim();
        String updatedPortion = editCommandArgs[1].trim();

        if (portionToEdit.equals("")) {
            throw new InvalidArgsException("Please use /task, /by, /from or /to to indicate which portion to edit");
        }

        if (updatedPortion.equals("")) {
            throw new InvalidArgsException("Please specify what the updated portion should be!");
        }

        EditCommand editCommand = new EditCommand(taskIndex, portionToEdit, updatedPortion);
        return editCommand;
    }
    private static void processUnknownCommand(String userInput) throws Exception {
        if (userInput.equals("")) {
            throw new EmptyCommandException("Empty command! Type something!\n ");
        }

        throw new InvalidCommandException("Sorry, please type a valid command\n");
    }

    /**
     * Checks if the given string is numeric
     * @param str String to be checked
     * @return True if the string is numeric and false otherwise
     */
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void checkArguments(String[] userInputs) throws EmptyTaskException {
        if (userInputs.length < 2 || userInputs[1].equals("")) {
            throw new EmptyTaskException("Please input a task name\n");
        }
    }

}
