public class DeleteCommand extends Command{
    int index;

    public DeleteCommand(int index) {
        super(Command.CommandType.DELETE);
        this.index = index;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        if (tl.isValidIndex(index)) {
            tl.delete(index);
        } else {
            new InvalidCommand("index not in range of list").execute(tl, ui, storage);
        }
        ui.showList(tl);
    }
}
