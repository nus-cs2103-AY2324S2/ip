package command;
import task.Task;

public class AddCmd extends Command {
    public Task task;
    @Override
    public void execute() {
        tasks.add(task);
        ui.addedResponse(task.toString());
    }

    public AddCmd(String userInput) {
        task = new Task(userInput);
    }
}
