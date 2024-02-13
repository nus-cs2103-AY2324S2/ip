package actions;

import ui.Duke;

public class Bye implements Action {
    @Override
    public String execute(Duke bot) {
        bot.exit();
        return "";
    }
}
