package duke;


/**
 * This class handles user commands.
 * It provides methods to execute commands.
 */
public class CommandHandler {
    private Gui gui;

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
     * Constructs a new CommandHandler with the specified user interface.
     *
     * @param gui the user interface to use
     */
    public CommandHandler(Gui gui) {
        this.gui = gui;
    }

    /**
     * Executes the specified command.
     *
     * @param userInput the command to execute
     * @return true if the command is to exit, false otherwise
     * @throws DukeException if the command is not recognized or if an error occurs while executing the command
     */
    public String executeCommand(String userInput) throws DukeException {
        String[] words = userInput.split("\\s+");
        Command command;
        try {
            command = Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            String commandStr = words[0];
            throw new CommandNotFoundException(commandStr);
        }

        String output = "";
        switch (command) {
            case BYE:
                output = "bye";
                break;
            case LIST:
                output = TaskList.list();
                break;
            default:
                String arguments;
                try {
                    arguments = userInput.substring(command.name().length() + 1);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new ArgumentNotFoundException(command.name());
                }
                switch (command) {
                case MARK:
                    output = TaskList.markTask(processTaskIdx(arguments));
                    break;
                case UNMARK:
                    output = TaskList.unmarkTask(processTaskIdx(arguments));
                    break;
                case DELETE:
                    output = TaskList.deleteTask(processTaskIdx(arguments));
                    break;
                case TODO:
                    output = TaskList.addTask(processToDo(arguments));
                    break;
                case DEADLINE:
                    output = TaskList.addTask(processDeadline(arguments));
                    break;
                case EVENT:
                    output = TaskList.addTask(processEvent(arguments));
                    break;
                case FIND:
                    output = TaskList.findTask(arguments);
                    break;
                default:
                    output = "Error: Fell into default case in executeCommand method!";
                    break;
                }
        }
            // To store the updated Task List
            Storage.store();

            return output;
    }


    /**
     * Processes the argument of a command that operates on a task by index.
     *
     * @param arguments the argument to process
     * @return the index of the task
     * @throws IndexOutOfRange if the index is out of range
     */
    private static int processTaskIdx(String arguments) throws IndexOutOfRange{
        int idx = Integer.parseInt(arguments);
        int size = TaskList.listSize();
        if (idx <= 0 || idx > size) {
            throw new IndexOutOfRange(idx, size);
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
     * @throws InvalidDateFormat if the date format is invalid
     */
    private static Deadline processDeadline(String arguments) throws InvalidDeadlineFormatException, InvalidDateFormat {
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
     * @throws InvalidDateFormat if the date format is invalid
     */
    private static Event processEvent(String arguments) throws InvalidEventFormatException, InvalidDateFormat {
        try {
            String[] parts = arguments.split("/from ");
            String[] parts2 = parts[1].split("/to ");
            return new Event(parts[0], parts2[0], parts2[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidEventFormatException();
        }
    }
}