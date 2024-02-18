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
            break;
        case "list":
            instruction = Instruction.LIST;
            break;
        case "mark":
            instruction = Instruction.MARK;
            break;
        case "unmark":
            instruction = Instruction.UNMARK;
            break;
        case "todo":
            instruction = Instruction.TODO;
            break;
        case "deadline":
            instruction = Instruction.DEADLINE;
            break;
        case "event":
            instruction = Instruction.EVENT;
            break;
        case "delete":
            instruction = Instruction.DELETE;
            break;
        case "find":
            instruction = Instruction.FIND;
            break;
        case "edit":
            instruction = Instruction.EDIT;
            break;
        default:
            instruction = Instruction.NONE;
        }

        // Parser actions
        if (instruction == Instruction.BYE) {
            ByeCommand byeCommand = new ByeCommand();
            return byeCommand;
        } else if (instruction == Instruction.NONE) {
            if (inputArgs[0].equals("")) {
                throw new EmptyCommandException("Empty command! Type something!\n ");
            }

            throw new InvalidCommandException("Sorry, please type a valid command\n");
        } else if (instruction == Instruction.LIST) {
            ListCommand listCommand = new ListCommand();
            return listCommand;
        }

        // Commands with arguments
        if (inputArgs.length < 2 || inputArgs[1].equals("")) {
            throw new EmptyTaskException("Please input a task name\n");
        }

        String commandArg = inputArgs[1];

        if (instruction == Instruction.MARK) {
            if (!isNumeric(commandArg)) {
                throw new InvalidArgsException("Please input a number for the task you want to mark!\n");
            }

            int taskNumber = Integer.parseInt(commandArg) - 1;
            MarkCommand markCommand = new MarkCommand(taskNumber);
            resultCommand = markCommand;
        } else if (instruction == Instruction.UNMARK) {
            if (!isNumeric(commandArg)) {
                throw new InvalidArgsException("Please input a number for the task you want to unmark!\n");
            }

            int taskNumber = Integer.parseInt(commandArg) - 1;
            UnmarkCommand unmarkCommand = new UnmarkCommand(taskNumber);
            resultCommand = unmarkCommand;
        } else if (instruction == Instruction.TODO) {
            Task newTodo = new ToDo(commandArg.trim());
            AddCommand addCommand = new AddCommand(newTodo);
            resultCommand = addCommand;
        } else if (instruction == Instruction.DEADLINE) {
            if (!commandArg.contains("/by")) {
                throw new MissingDuedateException("Please specify a due date using '/by'!\n");
            }

            String[] taskArr = commandArg.split("/by", 2);
            String taskName = taskArr[0].trim();
            String taskDue = taskArr[1].trim();
            Task newDeadline = new Deadline(taskName, taskDue);
            AddCommand addCommand = new AddCommand(newDeadline);
            resultCommand = addCommand;
        } else if (instruction == Instruction.EVENT) {
            if (!commandArg.contains("/from")) {
                throw new MissingEventTimeException("Please specify a start date using '/from'!\n");
            } else if (!commandArg.contains("/to")) {
                throw new MissingEventTimeException("Please specify an end date using '/to'!\n");
            }

            String[] taskArr = commandArg.split("/from", 2);
            String taskName = taskArr[0].trim();
            String taskDur = taskArr[1];

            String[] taskTime = taskDur.split("/to", 2);
            String taskFrom = taskTime[0].trim();
            String taskTo = taskTime[1].trim();

            Task newEvent = new Event(taskName, taskFrom, taskTo);
            AddCommand addCommand = new AddCommand(newEvent);
            resultCommand = addCommand;
        } else if (instruction == Instruction.DELETE) {
            if (!isNumeric(commandArg)) {
                throw new InvalidArgsException("Please input a number for the task you want to delete!\n");
            }

            int taskNumber = Integer.parseInt(commandArg) - 1;
            DeleteCommand deleteCommand = new DeleteCommand(taskNumber);
            resultCommand = deleteCommand;
        } else if (instruction == Instruction.FIND) {
            commandArg = commandArg.trim();

            if (commandArg.equals("")) {
                throw new InvalidArgsException("Please input a valid search string\n");
            }

            FindCommand findCommand = new FindCommand(commandArg);
            resultCommand = findCommand;
        } else if (instruction == Instruction.EDIT) {
            String[] editArgs = commandArg.split("\\s", 2);

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

            if(portionToEdit.equals("")) {
                throw new InvalidArgsException("Please use /task, /by, /from or /to to indicate which portion to edit");
            }

            if (updatedPortion.equals("")) {
                throw new InvalidArgsException("Please specify what the updated portion should be!");
            }

            EditCommand editCommand = new EditCommand(taskIndex, portionToEdit, updatedPortion);
            resultCommand = editCommand;
        }

        if (resultCommand == null) {
            throw new InvalidCommandException("Sorry, please type a valid command\n");
        }
        // Assert resultCommand is valid command
        assert resultCommand instanceof Command : "Parser.parse should return a Command object";

        return resultCommand;
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
}
