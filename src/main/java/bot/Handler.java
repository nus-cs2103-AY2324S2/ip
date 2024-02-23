package bot;

import java.util.List;

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

    public String handle(String input) throws DukeException {
        assert input != null : "Input is null";
        String command = parser.parseCommand(input);
        String output = "";

        if (command.equals("list")) {
            output = handleList();
        } else if (command.equals("mark")) {
            output = handleMark(input);
        } else if (command.equals("unmark")) {
            output = handleUnmark(input);
        } else if (command.equals("delete")) {
            output = handleDelete(input);
        } else if (command.equals("find")) {
            output = handleFind(input);
        } else if (command.equals("help")) {
            output = taskList.getHelpString();
        } else {
            output = handleAdd(input);
        }

        return output;
    }

    private String handleList() {
        return taskList.displayTasks();
    }

    private String handleMark(String input) throws DukeException {
        int index = parser.parseIndex(input);
        assert index >= 0 && index < taskList.getSize() : "Invalid task index";
        return taskList.markTaskAsDone(index);
    }

    private String handleUnmark(String input) throws DukeException {
        int index = parser.parseIndex(input);
        assert index >= 0 && index < taskList.getSize() : "Invalid task index";
        return taskList.unmarkTaskAsDone(index);
    }

    private String handleAdd(String input) throws DukeException {
        String result = taskList.addTask(input);
        assert result != null : "Task was not added successfully";
        return result;
    }

    private String handleDelete(String input) throws DukeException {
        int index = parser.parseIndex(input);
        return taskList.deleteTask(index);
    }

    private String handleFind(String input) {
        String keyword = parser.parseDescription(input);
        List<Task> keywordList = taskList.findTasksFromKeyword(keyword);
        String output = "";
        output += "Here are the matching tasks in your list:\n";
        for (Task task : keywordList) {
            output += task.toString() + "\n";
        }
        return output;
    }
}
