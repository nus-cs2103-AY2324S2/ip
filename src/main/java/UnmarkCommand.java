public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TodoList todoList, Storage storage, Ui ui) throws InvalidIndexException {
        todoList.unmarkTask(index);
        ui.print("Alright, I have marked this task as incomplete:\n"
                + todoList.getTask(index));
    }
}
