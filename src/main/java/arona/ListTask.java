package arona;

public class ListTask extends Command {
    public ListTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList.getTasks());
    }
}