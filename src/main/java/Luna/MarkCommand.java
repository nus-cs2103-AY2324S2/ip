package Luna;

public class MarkCommand extends Command{
    int index;

    public MarkCommand(int index) {
        super(Command.CommandType.MARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (tl.isValidIndex(index)) {
            tl.mark(index);
        } else {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        }
        ui.showList(tl);
    }
}
