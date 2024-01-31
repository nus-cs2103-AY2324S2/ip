public class removeCommand extends Command {
    int index;
    public removeCommand(int i){
        index = i;
    }
    @Override
    public void execute(TaskList t, Ui ui) {
        String task = t.removeTask(index);
        ui.display("HASSNT:\n" + "Noted. I've removed this task: " + task);
    }
}
