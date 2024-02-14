package paimon.command;

import paimon.ChatException;
import paimon.util.UiHandler;
import paimon.task.TaskList;

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
            ui.displayFoundTasksMessage(foundTasks);
        }
    };
    public boolean isExit() {
        return false;
    };
}
