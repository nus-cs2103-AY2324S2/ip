package Luna;

public class ListCommand extends Command {

    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        ui.showList(tl);
    }
}
