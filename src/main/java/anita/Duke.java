package anita;

/**
 * The main class.
 */
public class Duke {
    protected static boolean initialize = true;

    private Database database;
    private Ui ui;
    private TaskList taskList;

    /**
     * The constructor for the Duke class.
     */
    public Duke() {
        database = new Database(this);
        ui = new Ui();
        taskList = new TaskList(database);
        database.createFile();
        database.loadData(database.readFile());
        initialize = false;
    }

    /**
     * Main driver code for executing commands.
     * If user command is not valid, throws the respective exception.
     *
     * @return String command.
     */
    public String allocateTask(String command, String description) {
        Parser parser = new Parser();
        String res = "";
        try {
            switch (command) {
            case "bye":
                res += ui.bye();
                break;
            case "list":
                res += taskList.listTask();
                break;
            case "mark":
                res += taskList.setDone(parser.indexParser(description));
                break;
            case "unmark":
                res += taskList.setNotDone(parser.indexParser(description));
                break;
            case "delete":
                res += taskList.removeTask(parser.removeParser(description));
                break;
            case "find":
                res += taskList.findTask(parser.findParser(description));
                break;
            default:
                res += addingTask(command, description);
                break;
            }
        } catch (Exception e) {
            return e.getMessage();
        }
        return res;
    }

    /**
     * Checks for the type of task to be added to the task list.
     * Runs the correct parser and add task function.
     *
     * @param command Command representing the type of task to be added.
     * @param description The details of the task.
     * @throws Exception If command is not valid.
     */
    public String addingTask(String command, String description) throws Exception {
        assert description != null : "Description should not be empty";
        Parser parser = new Parser();
        switch (command) {
        case "t":
        case "todo":
            String[] parsedTodo = parser.todoParser(description);
            database.writeFile(description);
            return taskList.addTask(new Todo(parsedTodo[0]));
        case "d":
        case "deadline":
            String[] parsedDeadline = parser.deadlineParser(description);
            database.writeFile(description);
            return taskList.addTask(new Deadline(parsedDeadline[0], parsedDeadline[1]));
        case "e":
        case "event":
            String[] parsedEvent = parser.eventParser(description);
            database.writeFile(description);
            return taskList.addTask(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
        default:
            throw new IllegalArgumentException("Please enter a valid command.");
        }
    }

    /**
     * Outputs the greeting message.
     *
     * @return String of the greeting message.
     */
    public String getGreeting() {
        return ui.greet() + taskList.listTask();
    }

    /**
     * Takes in raw user input and returns the UI output.
     *
     * @param input The raw user input.
     * @return The UI output for each task.
     */
    public String getResponse(String input) {
        assert input != null : "Input should not be null";
        Parser parser = new Parser();
        String command = parser.parseCommand(input);
        return allocateTask(command, input);
    }
}
