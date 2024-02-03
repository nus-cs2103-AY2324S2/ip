package Actions;

import UI.Duke;

public class DisplayList implements Action {
    @Override
    public void execute(Duke bot) {
        bot.getTaskList().displayList();
    }
}
