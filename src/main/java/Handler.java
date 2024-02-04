public class Handler {
    private TaskList taskList;
    private Parser parser;

    public Handler(TaskList taskList) {
        this.taskList = taskList;
        this.parser = new Parser();
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
        int index = Integer.parseInt(input.split(" ")[1]);
        taskList.deleteTask(index);
    }

}
