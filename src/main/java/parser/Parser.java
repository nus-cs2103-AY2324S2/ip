package parser;

import exceptions.TaylorException;
import tasklist.*;
import tasks.Task;
import ui.Ui;

import java.util.List;

/**
 * Deals with making sense of the suer command
 */
public class Parser {
    /**
     * enums for different Commands
     */
    enum Commands {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, SEARCH, FIND, INVALID
    }
    /**
     * No constructor needed
     */
    private Parser() {
        throw new AssertionError("Constructor is not allowed");
    }

    /**
     * enums for the different Commands
     * @param action User Input to select the different action to be taken
     * @return return Enums for that command
     */
    private static Commands getCommands(String action) {
        try {
            return Commands.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException err) {
            return Commands.INVALID;
        }
    }
}
