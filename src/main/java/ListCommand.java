
/*
* The ListCommand class is a subclass of Command and represents a command to list all tasks in the task list.
* It does not take in any parameters.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}