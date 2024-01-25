public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        taskList.listTasks();
    }
}
