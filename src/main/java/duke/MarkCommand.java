package duke;

public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        super("", CommandType.MARK);
        this.index = index;
    }

    @Override
    public void execute(State state, Ui ui) {
        int taskNo = this.index - 1;
        Task task = state.getTask(taskNo);
        if (task == null) {
            System.out.println("Mamma-mia! This task no exist-o!");
            return;
        }
        task.setDone(true);
        ui.say("Mamma-mai! I've marked it done!");
        ui.say(task.toString());
    }
}
