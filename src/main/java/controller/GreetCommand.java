package controller;

import duke.Storage;
import view.GreetView;

public class GreetCommand extends Command {
    private final GreetView greetView;
    public GreetCommand() {
        this.greetView = new GreetView();
    }

    @Override
    public void execute(Storage storage) {
        greetView.display();
    }
}
