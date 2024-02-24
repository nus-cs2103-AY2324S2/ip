package Luna;

public class SaveCommand extends Command {

    public SaveCommand() {
        super(CommandType.SAVE);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        storage.clearFile();
        storage.appendList(tl);
    }
}
