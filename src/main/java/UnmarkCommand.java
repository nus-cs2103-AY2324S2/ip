public class UnmarkCommand extends Command{
    int index;
    private UnmarkCommand() {
        super(Command.CommandType.MARK);
    }

    public UnmarkCommand(int index) {
        super(Command.CommandType.MARK);
        this.index = index;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (tl.isValidIndex(index)) {
            tl.unmark(index);
        } else {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        }
        ui.showList(tl);
    }
}
