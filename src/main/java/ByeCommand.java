public class ByeCommand extends Command{
    public ByeCommand(){
        super(1);
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage st) {
        ui.showBye();
    }
}
