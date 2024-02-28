public class AddTodoCommand extends Command{
    protected String description;

    public void execute(TaskList list, Ui ui, Storage storage) throws OrkException {
        try {
            if (description.trim().isEmpty()) {
                throw new OrkException("To do what? You dumb meat!");
            }
            Task todo = new ToDos(description.trim());
            list.addTask(todo);
            int size = TaskList.list.size();
            ui.printAddedMessage(todo, size);
            storage.writeToDisk(list);
        } catch (OrkException exception) {
            ui.printException(exception);
        }
    }

    public AddTodoCommand(String description) {
        super();
        this.description = description;
    }
}
