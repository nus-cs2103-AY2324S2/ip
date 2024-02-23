package lex.parser.command;

import lex.ui.Ui;

/**
 * Represents a command for an unknown instruction.
 */
public class UnknownCommand implements Command {
    private final Ui ui;

    /**
     * Constructor for the UnknownCommand class.
     *
     * @param ui The user interface.
     */
    public UnknownCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public boolean execute() throws Exception {
        ui.print("OOPS!!! I'm sorry, but I don't know what that means :-(");

        return false;
    }
}
