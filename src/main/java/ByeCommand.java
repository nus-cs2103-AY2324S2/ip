public class ByeCommand extends Command{

    protected ByeCommand(Parser.Cmd type) {
        super(type);
    }
    @Override
    public void run(TaskList taskList){
        Ui.leave();
    }
}
