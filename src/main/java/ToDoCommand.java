import java.io.IOException;

public class ToDoCommand extends Command {
    private final String toDoDescription;
    public ToDoCommand(String input) throws CommandException {
        this.toDoDescription = input.trim();
        if (toDoDescription.isEmpty()) {
            throw new CommandException("Error. Unable to create ToDo task.\nFormat: " + ToDo.CREATE_TODO_FORMAT);
        }
    }

    @Override
    public void run(TaskList taskList, Ui ui, Storage storage) throws IOException, CommandException {
        ui.showMessage(taskList.addTask(new ToDo(this.toDoDescription)));
        storage.save(taskList.toDataString());
    }
}
