package controller;

import view.ExitView;

public class Exit {
    private final ExitView exitView;

    public Exit() {
        this.exitView = new ExitView();
    }

    public void execute() {
        exitView.display();
    }
}
