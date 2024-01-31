public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(String args) throws HenryException {
        if (args.isBlank()) {
            throw new HenryException("No index provided");
        }
        this.index = Integer.parseInt(args) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        tasks.markTask(index);
        storage.save(tasks);
    }
}
