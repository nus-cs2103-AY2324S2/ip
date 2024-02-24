public class LoadCommand extends Command {

    public LoadCommand() {
        super(CommandType.SAVE);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);

        tl.clear();
        storage.loadList(tl);
    }
}
