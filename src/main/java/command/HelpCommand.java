package main.java.command;

import main.java.ChatException;
import main.java.UiHandler;
import main.java.task.TaskList;

public class HelpCommand extends Command {
    @Override
    public void execute(TaskList tasks, UiHandler ui) throws ChatException {
        ui.helpResponse();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
