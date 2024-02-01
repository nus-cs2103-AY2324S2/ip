public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task deleted = tasks.delete(index);
        ui.say("OK! I've deleted this task:\n" + deleted.toString() +
                "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }
}
