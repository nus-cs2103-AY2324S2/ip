package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.Task;
import paimon.task.TaskList;
import paimon.task.TodoTask;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;

    }
    public void execute(TaskList taskList, UiHandler ui) throws ChatException {
        String foundTasks = taskList.getFoundString(keyword);
        if (foundTasks.isEmpty()) {
            throw new ChatException("Could not find any tasks with that keyword");
        } else {
            ui.getFoundResponse(foundTasks);
        }
    };
    public boolean isExit() {
        return false;
    };
}
