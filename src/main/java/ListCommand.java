public class ListCommand extends Command {
    public void execute(TaskList list, Ui ui, Storage s) {
        ui.listTasks(list.getList());
    }

    public boolean isExit() {
        return false;
    }
}
