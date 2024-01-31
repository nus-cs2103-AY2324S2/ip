public class ListCommand extends Command{
    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws DiboException {
        ui.showList(taskList.getDisplayFormat());
    }
}
