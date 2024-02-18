package aaron.command;

import aaron.exception.AaronBotException;
import aaron.task.TaskList;
import aaron.ui.UI;

/**
 * Class that represents a bye command
 */
public class Bye extends Command {
    public Bye(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
    }

    @Override
    public String run(TaskList taskList, UI ui) {
        return ui.goodbyeMessage();
    }

    @Override
    public boolean returnIsBye() {
        return true;
    }

}
