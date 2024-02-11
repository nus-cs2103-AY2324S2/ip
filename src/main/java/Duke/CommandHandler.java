package duke;


/**
 * This class handles user commands.
 * It provides methods to execute commands.
 */
public class CommandHandler {

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT,
        FIND
    }

    /**
     * Executes the specified command.
     *
     * @param userInput the command to execute
     * @return the response of the chatbot
     * @throws DukeException if the command is not recognized or if an error occurs while executing the command
     */
    public String executeCommand(String userInput) throws DukeException {
        String[] words = userInput.split("\\s+");
        Command command = getCommand(words[0]);

        String output;
        if (command == Command.BYE || command == Command.LIST) {
            output = handleSimpleCommands(command);
        } else {
            String arguments = getCommandArguments(userInput, command);
            output = handleComplexCommands(command, arguments);
        }

        Storage.store();
        assert output != null: "Output should not be null!";
        return output;
    }

    /**
     * Retrieves the command from the user input.
     *
     * @param commandString the string representation of the command
     * @return the Command corresponding to the command string
     * @throws CommandNotFoundException if the command string does not correspond to a valid command
     */
    private Command getCommand(String commandString) throws CommandNotFoundException {
        try {
            return Command.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new CommandNotFoundException(commandString);
        }
    }

    /**
     * Handles simple commands that do not require additional arguments.
     *
     * @param command the command to handle
     * @return the response of the chatbot
     * @throws IllegalArgumentException if the command is not a simple command
     */
    private String handleSimpleCommands(Command command) {
        switch (command) {
            case BYE:
                return "bye";
            case LIST:
                return TaskList.list();
            default:
                throw new IllegalArgumentException("Invalid command: " + command);
        }
    }

    /**
     * Retrieves the arguments for a command from the user input.
     *
     * @param userInput the user input
     * @param command the command to retrieve arguments for
     * @return the arguments for the command
     * @throws ArgumentNotFoundException if the user input does not contain the required arguments for the command
     */
    private String getCommandArguments(String userInput, Command command) throws ArgumentNotFoundException {
        try {
            return userInput.substring(command.name().length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ArgumentNotFoundException(command.name());
        }
    }

    /**
     * Handles complex commands that require additional arguments.
     *
     * @param command the command to handle
     * @param arguments the arguments for the command
     * @return the response of the chatbot
     * @throws DukeException if the arguments are invalid
     */
    private String handleComplexCommands(Command command, String arguments) throws DukeException {
        switch (command) {
            case MARK:
                return TaskList.markTask(processTaskIdx(arguments));
            case UNMARK:
                return TaskList.unmarkTask(processTaskIdx(arguments));
            case DELETE:
                return TaskList.deleteTask(processTaskIdx(arguments));
            case TODO:
                return TaskList.addTask(processToDo(arguments));
            case DEADLINE:
                return TaskList.addTask(processDeadline(arguments));
            case EVENT:
                return TaskList.addTask(processEvent(arguments));
            case FIND:
                return TaskList.findTask(arguments);
            default:
                assert false: "Should not fall into default case of switch block for executecomnand method! ";
                return "Error: Fell into default case in executeCommand method!";
        }
    }

    /**
     * Processes the argument of a command that operates on a task by index.
     *
     * @param arguments the argument to process
     * @return the index of the task
     * @throws IndexOutOfRangeException if the index is out of range
     */
    private static int processTaskIdx(String arguments) throws IndexOutOfRangeException {
        int idx = Integer.parseInt(arguments);
        int size = TaskList.listSize();
        assert size>=0: "Size of TaskList should always be >= 0";
        if (idx <= 0 || idx > size) {
            throw new IndexOutOfRangeException(idx, size);
        }
        return idx;
    }

    /**
     * Processes the argument of a ToDo command.
     *
     * @param arguments the argument to process
     * @return a new ToDo task with the specified name
     */
    private static ToDo processToDo(String arguments) {
        return new ToDo(arguments);
    }

    /**
     * Processes the argument of a Deadline command.
     *
     * @param arguments the argument to process
     * @return a new Deadline task with the specified name and due date
     * @throws InvalidDeadlineFormatException if the argument format is invalid
     * @throws InvalidDateFormatException if the date format is invalid
     */
    private static Deadline processDeadline(String arguments) throws InvalidDeadlineFormatException, InvalidDateFormatException {
        try {
            String[] parts = arguments.split("/by ");
            return new Deadline(parts[0], parts[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidDeadlineFormatException();
        }
    }

    /**
     * Processes the argument of an Event command.
     *
     * @param arguments the argument to process
     * @return a new Event task with the specified name and date range
     * @throws InvalidEventFormatException if the argument format is invalid
     * @throws InvalidDateFormatException if the date format is invalid
     */
    private static Event processEvent(String arguments) throws InvalidEventFormatException, InvalidDateFormatException {
        try {
            String[] parts = arguments.split("/from ");
            String[] parts2 = parts[1].split("/to ");
            return new Event(parts[0], parts2[0], parts2[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidEventFormatException();
        }
    }
}