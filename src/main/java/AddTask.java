public class AddTask extends Command {
    public AddTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException, TaskException {
        taskList.addTask(fullCommand);
        storage.save(taskList.getTasks());
    }
}
