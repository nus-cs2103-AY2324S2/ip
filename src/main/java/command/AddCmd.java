package command;
import task.Task;

public class AddCmd extends Command {
    public Task task;
    @Override
    public String execute() {
        tasks.add(task);
        response = ui.addedResponse(task.toString());
        return response;
    }

    public AddCmd(String userInput) {
        task = new Task(userInput);
    }
}
