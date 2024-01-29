public class AddTodoCommand extends Command {

    private String name;
    public AddTodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JohnnyException {
        Task task = new ToDo(name);
        tasks.addTask(task);
        storage.appendToFile(task);
        ui.showAddTask(task, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
