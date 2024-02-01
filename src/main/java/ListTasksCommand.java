public class ListTasksCommand extends Command {

    public ListTasksCommand() {}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTaskList(tasks);
    }
}