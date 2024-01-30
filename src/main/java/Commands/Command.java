package Commands;

import Task.TaskManager;
import UI.Ui;

public abstract class Command {

    private boolean isTerminateChat = false;

    public boolean isTerminateChat() {
       return isTerminateChat;
    }

    public abstract boolean execute(TaskManager taskManager, Ui userInterface);
}
