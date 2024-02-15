package command;

import task.Todo;

public class TodoCmd extends Command {
    public Todo task;
    @Override
    public String execute() {
        tasks.add(task);
        response = ui.addedResponse(task.toString());
        return response;
    }

    public TodoCmd(String userInput) {
        task = new Todo(userInput);
    }
}
