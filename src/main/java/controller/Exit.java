package controller;

import duke.Storage;
import view.ExitView;

public class Exit {
    private final ExitView exitView;

    public Exit() {
        this.exitView = new ExitView();
    }

    public void execute(Storage storage) {
        exitView.display();
    }
}
