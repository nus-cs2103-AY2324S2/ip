package duke;

import java.util.List;

/**
 * The Handler class is responsible for handling user input and executing the
 * corresponding commands.
 */
public class Handler {
    private TaskList taskList;
    private Parser parser = new Parser();
    private Ui ui = new Ui();

    /**
     * Constructs a new Handler object.
     *
     * @param taskList The list of tasks to be managed.
     */
    public Handler(TaskList taskList) {
        this.taskList = taskList;
    }

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
        } else if (command.equals("find")) {
            handleFind(input);
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

    private void handleFind(String input) {
        String keyword = parser.parseDescription(input);
        List<Task> keywordList = taskList.findTasksFromKeyword(keyword);
        ui.printMessage("Here are the matching tasks in your list:\n");
        for (Task task : keywordList) {
            ui.printMessage(task.toString());
        }
        ui.printMessage("\n");
    }
}
