public class ToDoCommand extends Command{
    private String description;

    ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Todo todo = new Todo(description);
        tasks.add(todo);
        ui.taskResponse(todo, tasks);
        storage.saveList(tasks.getTasks());
    }
}


