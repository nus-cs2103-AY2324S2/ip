package Actions;

import ChatBot.Duke;
import TaskList.TaskList;

public class Delete implements Action {
    private int index;
    public Delete(int index) {
        this.index = index;
    }

    @Override
    public void execute(Duke bot) {
        bot.getTaskList().deleteTask(index);
    }
}
