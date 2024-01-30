package Osiris.Commands;

import Osiris.Task.TaskManager;
import Osiris.UI.Ui;

public abstract class Command {

    private boolean isTerminateChat = false;

    public boolean isTerminateChat() {
       return isTerminateChat;
    }

    public abstract boolean execute(TaskManager taskManager, Ui userInterface);
}
