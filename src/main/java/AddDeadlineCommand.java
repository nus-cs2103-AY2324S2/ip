public class AddDeadlineCommand extends Command{
    protected String description;

    public AddDeadlineCommand(String description) {
        super();
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws OrkException {
        try {
            String[] detailsSplit = description.split("/by");
            if (detailsSplit.length == 0 || detailsSplit[0].trim().isEmpty()) {
                throw new OrkException("The deadline for what?! You dumb meat!");
            }
            if (detailsSplit.length < 2 || detailsSplit[1].trim().isEmpty()) {
                throw new OrkException("Come on, dumb meat. I need both the task and the date.");
            }
            String action = detailsSplit[0].trim();
            String deadline = detailsSplit[1].trim();
            Task Deadline = new Deadlines(action,deadline);
            TaskList.list.add(Deadline);
            int numberOfTasks = TaskList.list.size();
            ui.printAddedMessage(Deadline, numberOfTasks);
            Storage.writeToDisk(tasks);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }
}
