package arona;

public class UnmarkTask extends Command {
    public UnmarkTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException {
        int taskNum = Integer.parseInt(fullCommand.split(" ", 0)[1]);
        taskList.changeTaskStatus(taskNum, false);
        storage.save(taskList.getTasks());
    }
}