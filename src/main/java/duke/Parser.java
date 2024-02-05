package duke;

import java.util.ArrayList;

public class Parser {

    public void parse(String fullCommand) throws DukeException {

        if (!(fullCommand.startsWith("todo") || fullCommand.startsWith("deadline") || fullCommand.startsWith("event") || fullCommand.startsWith("list") || fullCommand.startsWith("bye") || fullCommand.startsWith("delete"))) {
            Ui ui = new Ui();
            ui.showDivider();
            throw new DukeException("OPPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String[] commandSplit = fullCommand.split(" ");



    }
}
