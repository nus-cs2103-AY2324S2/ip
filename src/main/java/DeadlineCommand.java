public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final Deadline deadlineItem;

    public DeadlineCommand(Deadline deadlineItem) {
        this.deadlineItem = deadlineItem;
    }

    @Override
    public CommandResult execute() {
        super.taskList.addItem(this.deadlineItem);
        return new CommandResult(
                String.format(Messages.MESSAGE_ADD_TASK, this.deadlineItem, super.taskList.getListSize())
        );
    }
}
