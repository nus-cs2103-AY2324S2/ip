package duke;

/**
 * The Handler class is responsible for handling user input and executing the
 * corresponding commands.
 */
public class Handler {
    private TaskList taskList;
    private Parser parser = new Parser();

    /**
     * Constructs a new Handler object.
     *
     * @param taskList The list of tasks to be managed.
     */
    public Handler(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Handles the user input.
     *
     * @param input The user input.
     * @throws DukeException If the input is invalid.
     */
    public void handle(String input) throws DukeException {
        String command = parser.parseCommand(input);

        if (command.equals("list")) {
            handleList();
        } else if (command.equals("mark")) {
            handleMark(input);
        } else if (command.equals("unmark")) {
            handleUnmark(input);
        } else if (command.equals("delete")) {
            handleDelete(input);
        } else {
            handleAdd(input);
        }
    }

    private void handleList() {
        taskList.displayTasks();
    }

    private void handleMark(String input) throws DukeException {
        int index = parser.parseIndex(input);
        taskList.markTaskAsDone(index);
    }

    private void handleUnmark(String input) throws DukeException {
        int index = parser.parseIndex(input);
        taskList.unmarkTaskAsDone(index);
    }

    private void handleAdd(String input) throws DukeException {
        taskList.addTask(input);
    }

    private void handleDelete(String input) throws DukeException {
        int index = parser.parseIndex(input);
        taskList.deleteTask(index);
    }

}
