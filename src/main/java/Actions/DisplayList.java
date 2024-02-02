package Actions;

import ChatBot.Duke;
import TaskList.TaskList;

public class DisplayList implements Action {
    @Override
    public void execute(Duke bot) {
        bot.getTaskList().displayList();
    }
}
