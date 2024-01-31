public class markCommand extends Command {

    private TaskList taskList;
    private Ui ui;

    public markCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }
    @Override

    public void execute(TaskList taskList, Ui ui) throws Exception {
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int position = Integer.parseInt(indexStr) -1;
        taskList.mark(position);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
