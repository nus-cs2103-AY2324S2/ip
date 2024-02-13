package actions;

import ui.Duke;

public class Greet implements Action {
    @Override
    public String execute(Duke bot) {
        return ("Hello! I'm " + bot.getName() + "\nWhat can I do for you?\n");
    }
}
