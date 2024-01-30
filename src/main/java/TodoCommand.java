public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    private final ToDo toDoItem;

    public TodoCommand(ToDo toDoItem) {
        this.toDoItem = toDoItem;
    }

    @Override
    public CommandResult execute() {
        super.taskList.addItem(this.toDoItem);
        return new CommandResult(
                String.format(Messages.MESSAGE_ADD_TASK, this.toDoItem, super.taskList.getListSize()));
    }
}
