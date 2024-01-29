public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        String list = tasks.list();
        ui.showList(list);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
