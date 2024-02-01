package arona;

public class MarkTask extends Command {
    public MarkTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException {
        int taskNum = Integer.parseInt(fullCommand.split(" ", 0)[1]);
        taskList.changeTaskStatus(taskNum, true);
        storage.save(taskList.getTasks());
    }
}