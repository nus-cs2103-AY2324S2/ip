public class Parser {
    //private String[] parts;
    private TaskList taskList;
    private Ui ui;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }
    public boolean parse(String input) throws JayneException {
        String[] parts = input.split(" ", 2);
        String commandText = parts[0].toLowerCase();
        switch (commandText) {
            case "bye":
                Handler.handleBye();
                return true;
            case "list":
                Handler.handleList(taskList);
                break;
            case "mark":
                Handler.handleMark(parts, taskList);
                break;
            case "unmark":
                Handler.handleUnmark(parts, taskList);
                break;
            case "todo":
                Handler.handleTodo(parts, taskList);
                break;
            case "deadline":
                Handler.handleDeadline(parts, taskList);
                break;
            case "event":
                Handler.handleEvent(parts, taskList);
                break;
            case "delete":
                Handler.handleDelete(parts, taskList);
                break;
            default:
                throw new JayneException(ui.question());
        }
        return false;
    }
}
