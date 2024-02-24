public class ExitCommand extends Command{
    public ExitCommand() {
        super(CommandType.EXIT);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        ui.exitMessage();
        System.exit(0);
//        super.execute(tl, ui, storage);
    }


}
