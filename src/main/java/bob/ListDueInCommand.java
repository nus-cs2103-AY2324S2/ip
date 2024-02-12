package bob;

public class ListDueInCommand extends ListCommand {
    private int days;

    public ListDueInCommand(int days) {
        this.days = days;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.showList(taskList.listDueIn(days));
    }
}
