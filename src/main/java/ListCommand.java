public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     *
     * Loads the tasks in task list into status msg to be printed.
     */
    @Override
    public void execute(TaskList tasks, Storage storage) {
        this.statusMsg = tasks.toString();
    }
}
