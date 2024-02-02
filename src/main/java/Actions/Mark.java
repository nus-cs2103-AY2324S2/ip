package Actions;

import ChatBot.Duke;
import TaskList.TaskList;

public class Mark implements Action {
    private int index;
    public Mark(int index) {
        this.index = index;
    }

    @Override
    public void execute(Duke bot) {
        bot.getTaskList().markTask(index);
    }

}
