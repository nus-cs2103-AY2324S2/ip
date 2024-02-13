package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.task.TodoTask;


public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;

    }
    public void execute(TaskList taskList, UiHandler ui) throws ChatException {
        Task eventTask = new TodoTask(this.description);
        taskList.addTask(eventTask);
        ui.addTaskResponse(eventTask, taskList.getSize());
    };
    public boolean isExit() {
        return false;
    };
}