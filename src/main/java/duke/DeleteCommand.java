package duke;

public class DeleteCommand extends Command{
    private final int index;
    public DeleteCommand(int index) {
        super("", CommandType.DELETE);
        this.index = index;
    }

    @Override
    public void execute(State state, Ui ui) {
        Task taskToRemove = state.getTask(index);
        state.getTasks().remove(index);
        ui.say("Mamma-mia!\n" + taskToRemove + "\nRemoved!");
    }
}
