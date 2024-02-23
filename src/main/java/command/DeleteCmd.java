package command;

import task.Task;

public class DeleteCmd extends Command {
    private int index;
    private Task task;
    @Override
    public String execute() {
        task = tasks.getTask(index);
        tasks.delete(index);
        response = ui.deleteResponse(task.toString());
        return response;
    }

    public DeleteCmd(int i) {
        index = i - 1;
    }
}
