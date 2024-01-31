public class ByeCommand extends Command{

    @Override
    public boolean handle(Ui ui, TaskList taskList, Storage storage) throws ToothlessException {
        ui.showFarewell();
        storage.writeTasks(taskList);
        return true;
    }
}
