public class UnmarkCommand extends Command {
    private final int positionToUnmark;

    UnmarkCommand(int positionToUnmark) {
        this.positionToUnmark = positionToUnmark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        Task toUnmark;
        try {
            toUnmark = tasks.get(positionToUnmark - 1);
        } catch (IndexOutOfBoundsException e) {
            DookException err;
            if (tasks.size() == 0) {
                err = new DookException("Nooo! You don't have any tasks to unmark :(");
            } else {
                err = new DookException(String.format("Nooo! " +
                                "You have %d tasks!" +
                                " Valid inputs for unmark is in the range [1 - %d]",
                        tasks.size(), tasks.size()));
            }
            throw err;
        }
        toUnmark.markAsNotDone();
        ui.println("Lazy bum. >:( I've marked this task as done:");
        ui.println(toUnmark.toString());
        storage.write(tasks);
    }
}
