package chipchat.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

import chipchat.action.Action;
import chipchat.action.AddTask;
import chipchat.action.Bye;
import chipchat.action.CommandType;
import chipchat.action.Delete;
import chipchat.action.Find;
import chipchat.action.ListTasks;
import chipchat.action.Mark;
import chipchat.action.Unmark;
import chipchat.exception.ArgumentException;
import chipchat.exception.InvalidArgumentException;
import chipchat.exception.MissingArgumentException;
import chipchat.task.Task;

/**
 * Represents a utility class used to parse user inputs and data inputs given to the main Chipchat application.
 */
public class Parser {
    private enum ArgumentType {
        DESCRIPTION, ISDONE, BY, FROM, TO, TAG;
    }

    private static EnumMap<ArgumentType, String> argsMap = new EnumMap<>(ArgumentType.class);

    // Initialize argsMap such that is_done attribute is false by default
    static {
        argsMap.put(ArgumentType.ISDONE, "false");
    }

    /**
     * Returns an action parsed from the user input.
     *
     * @param userInput user input given from standard input
     * @return an action that can be executed on-demand
     * @throws ArgumentException
     */
    public static Action parseAction(String userInput) throws ArgumentException {
        String[] tokens = userInput.trim().split(" ");
        if (tokens.length == 0) {
            throw new MissingArgumentException("Error: Please enter something");
        }

        CommandType command = parseCommand(tokens[0]);
        switch(command) {
        case BYE:
            return new Bye();
        case LIST:
            return new ListTasks();
        case FIND:
            return parseFindAction(command, tokens);
        case DELETE:
        case MARK:
        case UNMARK:
            return parseEditAction(command, tokens);
        case TODO:
        case DEADLINE:
        case EVENT:
            return parseTask(command, tokens);
        default:
            // Should not reach default branch
            throw new ArgumentException("Reached default branch of parseAction() due to unrecognized command type");
        }
    }

    /**
     * A simple data parser that uses yyyy-MM-dd format.
     *
     * @param date a string representing a date
     * @return parsed date
     */
    public static LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        return LocalDate.parse(date, formatter);
    }

    /**
     * Returns a task parsed from saved data.
     *
     * @param line the data-format string retrieved from storage file
     * @return parsed action that has been initialized based on stored data
     * @throws ArgumentException
     */
    public static AddTask parseLoadedTask(String line) throws ArgumentException {
        String[] tokens = line.split(" ");
        CommandType taskType = parseCommand(tokens[1]);
        return parseTask(taskType, Arrays.copyOfRange(tokens, 1, tokens.length));
    }


    private static CommandType parseCommand(String command) throws InvalidArgumentException {
        assert(!command.isEmpty());

        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException exc) {
            throw new InvalidArgumentException("Error: Sorry. I don't know what that means");
        }
    }

    private static int parseIndex(String index) throws InvalidArgumentException {
        assert(!index.isEmpty());

        try {
            return Integer.parseInt(index);
        } catch (NumberFormatException exc) {
            throw new InvalidArgumentException(
                    "Error: Please enter the index (number) of the task you would like to edit");
        }
    }

    private static ArgumentType parseArgumentType(String arg) throws InvalidArgumentException {
        assert(!arg.isEmpty());

        try {
            return ArgumentType.valueOf(arg.toUpperCase());
        } catch (IllegalArgumentException exc) {
            throw new InvalidArgumentException("Error: Sorry. I don't know what that means");
        }
    }

    private static void readArguments(String[] tokens) {
        StringBuilder currArg = new StringBuilder();
        ArgumentType argType = ArgumentType.DESCRIPTION;
        for (String token : tokens) {
            if (token.startsWith("/")) {
                if (currArg.length() > 0) {
                    argsMap.put(argType, currArg.toString().trim());
                    currArg.setLength(0);
                }
                String[] args = token.trim().split("/");
                String arg = args[1].substring(0).toUpperCase();
                argType = parseArgumentType(arg);
            } else {
                currArg.append(token)
                        .append(" ");
            }
        }
        // Add remaining currArg to args list
        argsMap.put(argType, currArg.toString().trim());
    }

    private static List<String> parseTags(String tagString) {
        if (tagString == null) {
            return List.of();
        }

        String[] tags = tagString.split("#");
        return Arrays.asList(tags)
                .stream()
                .map(tag -> tag.trim())
                .filter(tag -> tag.length() > 0)
                .collect(Collectors.toList());
    }

    private static void resetArgsMap() {
        argsMap.clear();
        argsMap = new EnumMap<>(ArgumentType.class);
        argsMap.put(ArgumentType.ISDONE, "false");
    }

    private static Action parseFindAction(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException(
                    "Error: Missing query\nPlease enter the query (text) of the task to find");
        }
        return new Find(tokens[1]);
    }

    private static Action parseEditAction(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException(
                    "Error: Missing Index\nPlease enter the index (number) of the task to edit");
        }

        int index = parseIndex(tokens[1]);
        switch(command) {
        case DELETE:
            return new Delete(index);
        case MARK:
            return new Mark(index);
        case UNMARK:
            return new Unmark(index);
        default:
            // Should not reach default branch
            throw new ArgumentException("Reached default branch of parseEditAction() due to unrecognized command type");
        }
    }

    private static AddTask parseTask(CommandType command, String[] tokens) {
        if (tokens.length < 2) {
            throw new MissingArgumentException(
                    "Error: Missing description\nPlease enter the description of the task to add");
        } else {
            tokens = Arrays.copyOfRange(tokens, 1, tokens.length);
        }
        readArguments(tokens);

        String description = argsMap.get(ArgumentType.DESCRIPTION);
        boolean isDone = argsMap.get(ArgumentType.ISDONE).equals("true");
        List<String> tags = parseTags(argsMap.get(ArgumentType.TAG));

        AddTask addTaskAction = null;
        switch(command) {
        case TODO:
            addTaskAction = AddTask.addTodo(description, isDone, tags);
            break;
        case DEADLINE:
            LocalDate dueBy = parseDate(argsMap.get(ArgumentType.BY));
            addTaskAction = AddTask.addDeadline(description, isDone, dueBy, tags);
            break;
        case EVENT:
            LocalDate dateFrom = parseDate(argsMap.get(ArgumentType.FROM));
            LocalDate dateTo = parseDate(argsMap.get(ArgumentType.TO));
            addTaskAction = AddTask.addEvent(description, isDone, dateFrom, dateTo, tags);
            break;
        default:
            throw new ArgumentException("Reached default branch of parseTask() due to unrecognized command type");
        }
        resetArgsMap();
        return addTaskAction;
    }
}
