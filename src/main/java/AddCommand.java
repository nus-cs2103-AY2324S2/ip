import java.io.IOException;

public class AddCommand implements Command {
    private String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask = TaskFactory.createTask(userInput);
            tasks.addTask(newTask);
            ui.showConfirmationMessage(tasks.getTasks());
            storage.saveTasksToFile(tasks);
        } catch (IOException e) {
            // Handle or log the IOException as needed
            ui.showIOExceptionMessage();
        }
    }
}
