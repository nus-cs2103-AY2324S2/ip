public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String args) throws HenryException {
        if (!args.contains("/by")) {
            throw new HenryException("Missing /by");
        }
        String[] deadlineParams = args.split(" /by ");
        if (deadlineParams[0].isBlank()) {
            throw new HenryException("No description and /by provided");
        }
        if (deadlineParams[1].isBlank()) {
            throw new HenryException("When this has to be done by?");
        }
        this.description = deadlineParams[0];
        this.by = deadlineParams[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws HenryException {
        tasks.addTask(new Deadline(description, by));
        storage.save(tasks);
    }
}
