package lex.parser.command;

import lex.storage.Storage;
import lex.tasks.TaskList;
import lex.ui.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand implements Command {
    private final TaskList tasks;
    private final Ui ui;
    private final Storage storage;

    /**
     * Constructor for ByeCommand.
     *
     * @param tasks The list of tasks.
     * @param ui The user interface.
     * @param storage The storage.
     */
    public ByeCommand(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public boolean execute() {
        try {
            storage.save(tasks);
        } catch (Exception e) {
            ui.print(e.getMessage());
        }

        ui.print("Bye. Hope to see you again soon!");

        return true;
    }
}
