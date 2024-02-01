public class UnmarkCommand extends Command {
    private final int indexToUnmark;
    UnmarkCommand(int indexToUnmark) {
        this.indexToUnmark = indexToUnmark;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FelixException {
        try {
            Task task = tasks.getTask(this.indexToUnmark);
            task.unmarkDone();
            ui.println("OK, I've marked this task as not done yet:");
            ui.println(task);
            storage.writeToFile(tasks);
        } catch (IndexOutOfBoundsException err) {
            throw new FelixException(String.format("You have %d tasks, provide a valid index in the range [1,%d]",
                    tasks.getCount(), tasks.getCount()));
        }
    }
}
