package Actions;

import UI.Duke;

public class InvalidAction implements Action {
    public InvalidAction() {
    }

    @Override
    public void execute(Duke bot) {
        System.out.println("Command does not exist");
    }
}
