public class TodoCommand extends Command {
    private String name;

    public TodoCommand(String[] commands) throws EggyException {
        if (commands.length < 2) {
            throw new IncompleteTaskException("todo");
        }
        this.name = commands[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EggyException {
        Task newTodo = new Todo(this.name);
        tasks.addTask(newTodo);
        ui.printTaskAdded(newTodo, tasks.getSize());
        storage.save(tasks);
    }
}
