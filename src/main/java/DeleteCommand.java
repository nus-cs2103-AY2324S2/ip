public class DeleteCommand extends Command {
    private final int deleteIndex;

    DeleteCommand(int deleteIndex) {
        this.deleteIndex = deleteIndex;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        try {
            Task task = tasks.getTask(this.deleteIndex);
            tasks.deleteTask(deleteIndex);
            ui.println("Noted. I've removed this task:");
            ui.println(task);
            ui.println(String.format("Now you have %d tasks in the list.", tasks.getCount()));
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]",
                    tasks.getCount(), tasks.getCount()));
        }
    }
}
