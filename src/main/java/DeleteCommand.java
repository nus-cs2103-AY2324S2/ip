public class DeleteCommand extends Command{
    private final int index;
    public DeleteCommand(int index) {
        super("", CommandType.DELETE);
        this.index = index;
    }

    @Override
    public void execute(State state) {
        Task taskToRemove = state.getTask(index);
        state.getTasks().remove(index);
        System.out.println("Mamma-mia!\n" + taskToRemove + "\nRemoved!");
    }
}
