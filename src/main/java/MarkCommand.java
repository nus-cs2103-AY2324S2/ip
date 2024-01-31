public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int i){
        index = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui){
        String t = taskList.showMark(index);
        ui.display("HASSNT:\n" + "Nice! I've marked this task as done: " + t);
    }
}
