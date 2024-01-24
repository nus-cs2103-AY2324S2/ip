package controller;

import duke.Storage;
import view.ExitView;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    private final ExitView exitView;

    public ExitCommand() {
        this.exitView = new ExitView();
    }

    @Override
    public void execute(Storage storage) {
        exitView.display();
    }
}
