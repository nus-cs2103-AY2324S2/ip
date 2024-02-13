package paimon.command;

import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.TaskList;

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
