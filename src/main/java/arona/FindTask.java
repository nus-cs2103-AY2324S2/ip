package arona;
public class FindTask extends Command {
    public FindTask(String fullCommand) {
        super(fullCommand);
        this.exit = false;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FileException, TaskException {
        taskList.findTasks(fullCommand);
    }
}
