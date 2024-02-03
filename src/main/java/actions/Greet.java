package actions;

import ui.Duke;

public class Greet implements Action {
    @Override
    public void execute(Duke bot) {
        System.out.println("Hello! I'm " + bot.getName() + "\nWhat can I do for you?\n");
    }
}
