public abstract class Command {
    protected TaskList taskList;

    public void initData(TaskList taskList) {
        this.taskList = taskList;
    }

    public abstract CommandResult execute() throws CommandExecutionException;
}