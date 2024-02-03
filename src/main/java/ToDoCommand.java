public class ToDoCommand extends Command {

    private ToDo toDo;

    public ToDoCommand(String description) {
        this.toDo = new ToDo(description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(toDo);
        ui.showAddMsg(toDo, tasks.getTaskSize());
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
