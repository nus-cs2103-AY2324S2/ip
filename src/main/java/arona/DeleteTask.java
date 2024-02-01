package arona;

public class DeleteTask extends Command {
    public DeleteTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException {
        int taskNum = Integer.parseInt(fullCommand.split(" ", 0)[1]);
        taskList.DeleteTask(taskNum);
        storage.save(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return this.exit;
    }
}