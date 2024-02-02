public class TodoCommand extends Command {
    private String inputs;

    public TodoCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputs.isEmpty()) {
            throw new DukeMissingArgument(1, "todo");
        }
        Task newTask = new ToDos(inputs, false);
        ui.sendReply(tasks.add(newTask));
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
