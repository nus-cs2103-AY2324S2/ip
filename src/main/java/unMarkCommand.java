public class unMarkCommand extends Command {
    private final int  index;
    public unMarkCommand(int i){
        index = i;
    }
    @Override
    public void execute(TaskList t, Ui ui){
        String task = t.showUnmark(index);
        ui.display("HASSNT:\n" + "Noted. I've removed this task: " + task);
    }
}
