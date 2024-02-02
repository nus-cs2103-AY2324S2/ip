package Actions;

import ChatBot.Duke;
import TaskList.TaskList;

public class Unmark implements Action {
    private int index;
    public Unmark(int index) {
        this.index = index;
    }

    @Override
    public void execute(Duke bot) {
        bot.getTaskList().unmarkTask(index);
    }
}
