package osiris.commands;

import osiris.task.TaskManager;
import osiris.ui.Ui;

public abstract class Command {

    private boolean isTerminateChat = false;

    public boolean isTerminateChat() {
       return isTerminateChat;
    }

    public abstract boolean execute(TaskManager taskManager, Ui userInterface);
}
