public class AddTodoCommand extends Command {

    private String arguments;

    public AddTodoCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentException {
        try {
            Task newTask = new Todo(this.arguments);
            taskList.add(newTask);
            ui.addTask(newTask, taskList.getLength());

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidArgumentException("TODO");
        }
    }
}
