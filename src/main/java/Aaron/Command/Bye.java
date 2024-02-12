package aaron.command;

import aaron.exception.AaronBotException;
import aaron.task.TaskList;
import aaron.ui.UI;

public class Bye extends Command {
    public Bye(String userInput, CommandType commandType) throws AaronBotException {
        super(userInput, commandType);
    }

    @Override
    public void run(TaskList taskList, UI ui) {
        ui.goodbyeMessage();
    }

    @Override
    public boolean returnIsBye() {
        return true;
    }

}
