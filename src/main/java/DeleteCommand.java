public class DeleteCommand extends Command {
    private final int listIndex;
    public DeleteCommand(int listIndex) {
        this.listIndex = listIndex;
    }

    /**
     * {@inheritDoc}
     *
     * Delete the task at listIndex in tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            Task deletedTask = tasks.delete(this.listIndex);
            this.statusMsg = String.format("Okay. I've deleted this task:\n\t%s\nNow you have %d tasks in the list.",
                    deletedTask, tasks.size());
            storage.saveToFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            this.statusMsg = "Sorry, I can't find that task. Please enter a valid index\n" +
                    "You can see the tasks list by inputting \"list\"";
        }
    }
}