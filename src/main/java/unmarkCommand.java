public class unmarkCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    public unmarkCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    public void execute(TaskList taskList, Ui ui) throws Exception {
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;
        taskList.unmark(position);
    }

    public boolean isExit() {
        return false;
    }
}
