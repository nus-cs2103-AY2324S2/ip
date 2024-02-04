package actions;

import ui.Duke;

public class Bye implements Action {
    @Override
    public void execute(Duke bot) {
        bot.exit();
    }
}
