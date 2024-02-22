package duchess.parser;

import duchess.CommandType;
import duchess.DuchessException;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.ToDo;

/**
 * Parser class provides methods to parse input strings into Task objects.
 * It is responsible for parsing task information from file format strings.
 */
public class Parser {

    /**
     * Parses a task from a line read from a file.
     *
     * @param line the line read from the file
     * @return the Task object parsed from the line
     * @throws DuchessException if an error occurs during parsing
     */
    public static Task parseTaskFromFileString(String line) throws DuchessException {
        try {
            Task task;
            // Parse the line and create task objects accordingly
            // Example line format: "T | 1 | read book"
            String[] parts = line.split("\\|");
            String type = parts[0].trim();
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            switch (type) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                String by = parts[3].trim();
                task = new Deadline(description, isDone, by);
                break;
            case "E":
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, isDone, from, to);
                break;
            default:
                throw new DuchessException("Unknown task type: " + type);
            }
            return task;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DuchessException("Invalid task format: " + line);
        }
    }

    /**
     * Parses the user's input command to determine the command type.
     *
     * @param input the user's input command
     * @return the CommandType enum value representing the command type
     * @throws DuchessException if the input command is not recognized
     */
    public static CommandType parseCommand(String input) throws DuchessException {
        // Normalize input (convert to lowercase and remove leading/trailing whitespace)
        input = input.trim().toLowerCase();

        if (input.equals("bye")) {
            return CommandType.BYE;
        } else if (input.equals("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("todo")) {
            return CommandType.TODO;
        } else if (input.startsWith("event")) {
            return CommandType.EVENT;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (input.startsWith("find")) {
            return CommandType.FIND;
        } else if (input.startsWith("mark")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("duplicates")) {
            return CommandType.DUPLICATES;
        } else {
            // Unknown command
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Parses the arguments from the user's input command.
     *
     * @param input the user's input command
     * @return the argument fields of the input command
     * @throws DuchessException if the command requires argument(s) and it is missing
     */
    public static String parseArgs(String input) throws DuchessException {
        try {
            // Split the input command at the first space character and return the second part
            return input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DuchessException("No arguments found.");
        }
    }
}
