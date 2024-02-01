package lex.parser.command;

import lex.ui.Ui;

public class UnknownCommand implements Command {
    private final Ui ui;

    public UnknownCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public boolean execute() throws Exception {
        ui.print("OOPS!!! I'm sorry, but I don't know what that means :-(");

        return false;
    }
}
