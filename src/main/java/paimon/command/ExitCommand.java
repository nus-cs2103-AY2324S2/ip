package paimon.command;


import paimon.ChatException;
import paimon.UiHandler;
import paimon.task.TaskList;


public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, UiHandler ui) throws ChatException {

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
