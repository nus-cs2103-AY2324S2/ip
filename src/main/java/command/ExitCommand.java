package main.java.command;

import main.java.ChatException;
import main.java.UiHandler;
import main.java.task.TaskList;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UiHandler ui) throws ChatException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
