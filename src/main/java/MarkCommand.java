public class MarkCommand extends Command {
    private final int listIndex;
    public MarkCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * {@inheritDoc}
     *
     * Marks the task at listIndex in tasks as done.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task markedTask = tasks.mark(this.listIndex);
            this.statusMsg = "Nice! I've marked this task as done:\n\t" + markedTask;
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            this.statusMsg = "Sorry, I can't find that task. Please enter a valid index\n" +
                    "You can see the tasks list by inputting \"list\"";
        }
    }
}