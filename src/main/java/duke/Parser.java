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
        if (input.replaceAll("\\s", "").equals("")) {
            System.out.println("\tTask should not be empty!");
            return new ParsedCommand(CommandType.INVALID, -1);
        }

        String[] parts = input.split(" ", 2);
        CommandType commandType = CommandType.fromString(parts[0]);
        if (commandType == CommandType.LIST || commandType == CommandType.BYE) {
            return new ParsedCommand(commandType, -1);
        }
        if (parts.length < 2 || commandType == CommandType.INVALID) {
            return new ParsedCommand(CommandType.INVALID, -1); // Indicate invalid task number
        }
        if (commandType == CommandType.MARK || commandType == CommandType.UNMARK || commandType == CommandType.DELETE) {
            try {
                int taskNumber = Integer.parseInt(parts[1]);
                if (taskNumber > TaskList.storageFill || taskNumber <= 0) {
                    System.out.println("Task does not exist!");
                    return new ParsedCommand(CommandType.INVALID, -1); // Indicate invalid task number
                }
                return new ParsedCommand(commandType, taskNumber); // success
            } catch (NumberFormatException e) {
                System.out.println("Invalid task number!");
                return new ParsedCommand(CommandType.INVALID, -1); // Indicate invalid task number
            }
        } else {
            return new ParsedCommand(commandType, input); // success
        }
    }

    /**
     * Represents a parsed command, encapsulating the command type, input string, and any task number associated with it.
     * This class is used to hold the result of parsing a user input command within the Parser class.
     */
    public static class ParsedCommand {
        private final CommandType commandType; // The type of command parsed from the input.
        private final String input; // The input string containing task details, if applicable.
        private final int taskNumber; // The task number associated with the command, if applicable.
    
        /**
         * Constructor for commands that include task details but no task number.
         * This constructor is typically used for commands that create or modify tasks based on textual input.
         *
         * @param commandType The type of command, as determined by the parser.
         * @param input The input string containing task details. This could include task descriptions
         *              or additional information required to perform the command.
         */
        public ParsedCommand(CommandType commandType, String input) {
            this.commandType = commandType;
            this.input = input;
            this.taskNumber = -1; // Indicates that no task number is associated with this command.
        }
    
        /**
         * Constructor for commands that are associated with a specific task number.
         * This constructor is used for commands that operate on an existing task, identified by its number.
         *
         * @param commandType The type of command, such as MARK, UNMARK, or DELETE.
         * @param taskNumber The number of the task to which the command applies.
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
         * This method returns the original input string used to create or modify a task.
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
         * @return The task number, or -1 if no task number is associated with this command.
         */
        public int getTaskNumber() {
            return taskNumber;
        }
    }
    

    /**
     * Creates a {@link Task} object based on the given command type and input.
     * This method supports creation of Todo, Deadline, and Event tasks.
     *
     * @param command The command type indicating which kind of task to create.
     * @param input   The user input string containing task details.
     * @return A {@link Task} object or {@code null} if task creation fails.
     */
    public static Task createTask(CommandType command, String input) {
        String[] parts = input.split(" ", 2);
        Task newTask = null;
        switch (command) {
            case TODO:
                if (parts[1].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else {
                    newTask = new Todo(parts[1], input);
                }
                break;
            case DEADLINE:
                String[] deadlineParts = parts[1].split(" /by ", 2);
                if (deadlineParts.length < 2) {
                    System.out.println("\tSpecify /by xxx!");
                } else if (deadlineParts[0].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else if (deadlineParts[1].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tDue date should not be empty!");
                } else {
                    LocalDate dueDate = parseDate(deadlineParts[1]);
                    if (dueDate != null) {
                        newTask = new Deadline(deadlineParts[0], dueDate, input);
                    }
                }
                break;
            case EVENT:
                String[] eventParts = parts[1].split(" /from ", 2);
                if (eventParts[0].replaceAll("\\s", "").equals("")) {
                    System.out.println("\tTask should not be empty!");
                } else if (eventParts.length < 2) {
                    // not enough parts for an event
                    System.out.println("\tSpecify /from xxx and /to xxx!");
                } else {
                    String[] timeParts = eventParts[1].split(" /to ", 2);
                    if (timeParts[0].replaceAll("\\s", "").equals("")) {
                        System.out.println("\tStart time should not be empty!");
                    } else if (timeParts.length < 2) {
                        System.out.println("\tSpecify xxx /to xxx!");
                    } else if (timeParts[1].replaceAll("\\s", "").equals("")) {
                        System.out.println("\tEnd time should not be empty!");
                    } else {
                        // Construct the event string
                        String eventTime = timeParts[0] + " to: " + timeParts[1];
                        LocalDate start = parseDate(timeParts[0]);
                        LocalDate end = parseDate(timeParts[1]);
                        if (start != null && end != null) {
                            newTask = new Event(eventParts[0], eventTime, input, start, end);
                        }
                    }
                }
                break;
            default:
                System.out.println("Default case");
                break;
        }
        return newTask;
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
            System.out.println("\tUnable to parse the date. Please use the format: yyyy-MM-dd");
            return null;
        }
    }

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
