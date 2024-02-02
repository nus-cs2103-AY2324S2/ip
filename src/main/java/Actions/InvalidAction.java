package Actions;

import ChatBot.Duke;
import TaskList.TaskList;

public class InvalidAction implements Action {
    public InvalidAction() {
    }

    @Override
    public void execute(Duke bot) {
        System.out.println("Command does not exist");
    }
}
