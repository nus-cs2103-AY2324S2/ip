package actions;

import ui.Duke;

public class InvalidAction implements Action {
    public InvalidAction() {
    }

    @Override
    public String execute(Duke bot) {
        return ("Command does not exist");
    }
}
