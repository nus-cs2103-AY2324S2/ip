package Actions;

import UI.Duke;

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
