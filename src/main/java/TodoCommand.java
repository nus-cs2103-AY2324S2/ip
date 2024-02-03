public class TodoCommand extends Command {
    private String name;

    public TodoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTodo = new Todo(this.name);
        tasks.addTask(newTodo);
        ui.printTaskAdded(newTodo, tasks.getSize());
        storage.save(tasks);
    }
}
