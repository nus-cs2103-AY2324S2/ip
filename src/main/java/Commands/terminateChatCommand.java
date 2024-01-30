package Commands;

import Task.TaskManager;
import UI.Ui;

public class terminateChatCommand extends Command{

    public static final String COMMAND = "bye";

    public boolean isTerminateChat() {
        return true;
    }

    @Override
    public boolean execute(TaskManager taskManager, Ui userInterface) {
        taskManager.termintate();
        return true;
    }
}
