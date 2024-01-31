import exception.EmptyInputException;

public class todoCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    public todoCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    public void execute(TaskList taskList, Ui ui) throws EmptyInputException {
        String input = ui.getInput();
        if (input.split(" ").length > 1) {
            String description = input.substring(4).trim();
            Todo t = new Todo(description);
            taskList.todo(t);
        } else {
            throw new EmptyInputException("todo");
        }
    }

    public boolean isExit() {
        return false;
    }
}
