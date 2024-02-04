public class AddTodoCommand extends AddCommand {

    private String description;

    public AddTodoCommand(TaskList taskList, String description) {
        super(taskList);
        this.description = description;
    }

    public static String getUsage() {
        return Command.getUsage() + " add todo <description>";
    }

    @Override
    public void execute() {
        super.getTaskList().add(new ToDo(this.description));
        Ui.printMessage("Added ToDo task: " + description);
    }
}
