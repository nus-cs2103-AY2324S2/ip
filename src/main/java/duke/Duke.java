package duke;

import duke.command.Command;
import duke.util.Parser;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a chatbot whose name is "Alfred".
 * It is a task management app for recording different kinds of tasks.
 * It provides a czommand Line interface for users to interact with.
 */
public class Duke {
    private Parser parser;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a new instance of the Duke class.
     * This constructor initializes the Parser, Ui, and TaskList for the Duke application.
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser(ui);
        this.taskList = new TaskList();
    }

    public String getResponse(String input) {
        Command command;
        try {
            command = this.parser.parse(input);
            command.run(this.taskList, this.ui);
        } catch (IllegalArgumentException e) {
            return ui.getMessage();
        }
        return ui.getMessage();
    }
}
