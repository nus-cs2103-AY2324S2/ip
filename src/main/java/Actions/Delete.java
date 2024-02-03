package Actions;

import UI.Duke;

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
