package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user input into commands for the Duke application.
 * This class provides static methods to interpret user input and create tasks
 * based on that input.
 */
public class Parser {

    /**
     * Parses the given input string into a {@link ParsedCommand} object.
     * The method identifies the type of command and extracts necessary information
     * from the input.
     *
     * @param input The user input string to parse.
     * @return A {@link ParsedCommand} object representing the parsed command.
     */
    public static ParsedCommand parse(String input) {
        if (input.trim().isEmpty()) {
            return new ParsedCommand(CommandType.INVALID, "");
        }
    
        String[] parts = input.split(" ", 2);
        CommandType commandType = CommandType.fromString(parts[0]);
    
        switch (commandType) {
        case LIST:
        case BYE:
            return handleSimpleCommand(commandType);
        case MARK:
        case UNMARK:
        case DELETE:
            return handleTaskModificationCommand(parts, commandType);
        case FIND:
            return handleFindCommand(parts);
        default:
            // create TODO, DEADLINE, EVENT, INVALID tasks
            return handleOtherCommands(parts, input, commandType);
        }
    }
    
    private static ParsedCommand handleSimpleCommand(CommandType commandType) {
        return new ParsedCommand(commandType, "");
    }
    
    private static ParsedCommand handleTaskModificationCommand(String[] parts, CommandType commandType) {
        if (parts.length < 2) {
            return new ParsedCommand(CommandType.INVALID, "");
        }
        try {
            int taskNumber = Integer.parseInt(parts[1]);
            if (taskNumber > TaskList.storageFill || taskNumber <= 0) {
                return new ParsedCommand(CommandType.INVALID_NUM, "");
            }
            return new ParsedCommand(commandType, taskNumber);
        } catch (NumberFormatException e) {
            return new ParsedCommand(CommandType.INVALID_FORMAT, "");
        }
    }
    
    private static ParsedCommand handleFindCommand(String[] parts) {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return new ParsedCommand(CommandType.INVALID, "");
        }
        return new ParsedCommand(CommandType.FIND, parts[1]);
    }
    
    private static ParsedCommand handleOtherCommands(String[] parts, String input, CommandType commandType) {
        if (parts.length < 2) {
            return new ParsedCommand(CommandType.INVALID, "");
        }
        return new ParsedCommand(commandType, input);
    }
    

    /**
     * Represents a parsed command, encapsulating the command type, input string,
     * and any task number associated with it.
     * This class is used to hold the result of parsing a user input command within
     * the Parser class.
     */
    public static class ParsedCommand {
        private final CommandType commandType; // The type of command parsed from the input.
        private final String input; // The input string containing task details, if applicable.
        private final int taskNumber; // The task number associated with the command, if applicable.
        
        /**
         * Constructor for commands that include task details but no task number.
         * This constructor is typically used for commands that create or modify tasks
         * based on textual input.
         *
         * @param commandType The type of command, as determined by the parser.
         * @param input       The input string containing task details. This could
         *                    include task descriptions
         *                    or additional information required to perform the command.
         */
        public ParsedCommand(CommandType commandType, String input) {
            this.commandType = commandType;
            this.input = input;
            this.taskNumber = -1; // Indicates that no task number is associated with this command.
        }

        /**
         * Constructor for commands that are associated with a specific task number.
         * This constructor is used for commands that operate on an existing task,
         * identified by its number.
         *
         * @param commandType The type of command, such as MARK, UNMARK, or DELETE.
         * @param taskNumber  The number of the task to which the command applies.
         */
        public ParsedCommand(CommandType commandType, int taskNumber) {
            this.commandType = commandType;
            this.taskNumber = taskNumber;
            this.input = null; // Indicates that no detailed input string is associated with this command.
        }

        /**
         * Gets the type of the command.
         *
         * @return The CommandType representing the type of command.
         */
        public CommandType getCommandType() {
            return commandType;
        }

        /**
         * Gets the input string containing task details.
         * This method returns the original input string used to create or modify a
         * task.
         * It is only applicable for commands that include such details.
         *
         * @return The input string containing task details, or null if not applicable.
         */
        public String getInput() {
            return input;
        }

        /**
         * Gets the task number associated with the command.
         * This method returns the number of the task to which the command applies.
         * It is only applicable for commands that operate on an existing task.
         *
         * @return The task number, or -1 if no task number is associated with this
         *         command.
         */
        public int getTaskNumber() {
            return taskNumber;
        }
    }

    /**
     * Creates a {@link Task} object based on the given command type and input.
     * This method supports creation of Invalid, Todo, Deadline, and Event tasks.
     *
     * @param command The command type indicating which kind of task to create.
     * @param input   The user input string containing task details.
     * @return A {@link Task} object or {@code null} if task creation fails.
     */
    public static Task createTask(CommandType command, String input) {
        String[] parts = input.split(" ", 2);
        switch (command) {
        case TODO:
            return createTodoTask(parts, input);
        case DEADLINE:
            return createDeadlineTask(parts, input);
        case EVENT:
            return createEventTask(parts, input);
        default:
            return new InvalidTask(ErrorMessage.INVALID_COMMAND);
        }
    }

    private static Task createTodoTask(String[] parts, String input) {
        if (parts[1].trim().isEmpty()) {
            return new InvalidTask(ErrorMessage.EMPTY_TODO_DESCRIPTION);
        }
        return new Todo(parts[1], input);
    }

    /**
     * Creates a {@code Deadline} task based on user input.
     * Validates input for empty descriptions and checks due date format.
     *
     * @param parts Split user input, where {@code parts[1]} contains the task description and due date.
     * @param input Original user input.
     * @return A {@code Deadline} task if input is valid, or an {@code InvalidTask} with an error message.
     */
    private static Task createDeadlineTask(String[] parts, String input) {
        String[] deadlineParts = parts[1].split(" /by ", 2);
        if (deadlineParts[0].replaceAll("\\s", "").equals("")) {
            return new InvalidTask(ErrorMessage.EMPTY_DEADLINE_DESCRIPTION);
        } else if (deadlineParts.length < 2) {
            return new InvalidTask(ErrorMessage.DEADLINE_TIME_NOT_SPECIFIED);
        } else if (deadlineParts[1].replaceAll("\\s", "").equals("")) {
            return new InvalidTask(ErrorMessage.EMPTY_DUE_DATE);
        } else {
            LocalDate dueDate = parseDate(deadlineParts[1]);
            if (dueDate == null) {
                return new InvalidTask(ErrorMessage.INVALID_DATE_FORMAT);
            }
            return new Deadline(deadlineParts[0], dueDate, input);
        }
    }

    /**
     * Creates an {@code Event} task based on user input.
     * Validates input for empty descriptions and checks event start and end date formats.
     *
     * @param parts Split user input, where {@code parts[1]} contains the task description and event timing.
     * @param input Original user input.
     * @return An {@code Event} task if input is valid, or an {@code InvalidTask} with an error message.
     */
    private static Task createEventTask(String[] parts, String input) {
        String[] eventParts = parts[1].split(" /from ", 2);
        if (eventParts[0].trim().isEmpty()) {
            return new InvalidTask(ErrorMessage.EMPTY_EVENT_DESCRIPTION);
        } else if (eventParts.length < 2) {
            // not enough parts for an event
            return new InvalidTask(ErrorMessage.EVENT_TIME_NOT_SPECIFIED);
        } else {
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts[0].replaceAll("\\s", "").equals("")) {
                return new InvalidTask(ErrorMessage.EMPTY_START_TIME);
            } else if (timeParts.length < 2) {
                return new InvalidTask(ErrorMessage.EVENT_TIME_NOT_SPECIFIED);
            } else if (timeParts[1].replaceAll("\\s", "").equals("")) {
                return new InvalidTask(ErrorMessage.EMPTY_END_TIME);
            } else {
                // Construct the event string
                LocalDate start = parseDate(timeParts[0]);
                LocalDate end = parseDate(timeParts[1]);
                String eventTime = formatEventTime(start, end);
                if (start == null || end == null) {
                    return new InvalidTask(ErrorMessage.INVALID_DATE_FORMAT);
                }
                return new Event(eventParts[0], eventTime, input, start, end);
            }
        }
    }

    private static String formatEventTime(LocalDate start, LocalDate end) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return start.format(formatter) + " to: " + end.format(formatter);
    }

    /**
     * Parses a date string into a {@link LocalDate} object.
     * The method expects a date string in "yyyy-MM-dd" format.
     *
     * @param dateStr The date string to parse.
     * @return A {@link LocalDate} object or {@code null} if parsing fails.
     */
    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            // Parse the date string into a LocalDate object
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Parses a task from a formatted string and creates a Task object.
     * 
     * The input string format should be "CommandType | Status | Input":
     * - CommandType is the task type (e.g., TODO, DEADLINE, EVENT).
     * - Status is 1 (done) or 0 (not done).
     * - Input is the description or details of the task.
     *
     * @param line The formatted string representing a task.
     * @return A Task object corresponding to the input string. The task is marked as done if
     *         the Status is 1. Returns an Invalid task for invalid input formats or command types.
     */
    protected static Task createTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        CommandType commandType = CommandType.fromString(parts[0]);
        boolean isDone = parts[1].trim().equals("1");
        String input = parts[2];
        Task task = createTask(commandType, input);
        if (isDone) {
            task.markWithoutPrint();
        }
        return task;
    }
}
