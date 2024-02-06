public class TodoCommand extends Command {
    private TodoTask todo;

    public TodoCommand(TodoTask todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.addTask(todo);
        Ui.displayTodoCommand(todo);
    }

    @Override
    public boolean changedData() {
        return true;
    }
}
