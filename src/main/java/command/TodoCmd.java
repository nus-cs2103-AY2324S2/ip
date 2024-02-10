package command;

import task.Todo;

public class TodoCmd extends Command {
    public Todo task;
    @Override
    public void execute() {
        tasks.add(task);
        ui.addedResponse(task.toString());
    }

    public TodoCmd(String userInput) {
        task = new Todo(userInput);
    }
}
