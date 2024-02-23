package lex.parser.command;

import java.time.LocalDate;

import lex.tasks.Deadline;
import lex.tasks.TaskList;
import lex.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for DeadlineCommand.
     *
     * @param inputs The user input.
     * @param tasks The list of tasks.
     * @param ui The user interface.
     */
    public DeadlineCommand(String[] inputs, TaskList tasks, Ui ui) {
        this.inputs = inputs;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws Exception {
        String[] deadlineInputs = inputs[1].split(" /by ", 2);
        if (deadlineInputs.length < 2) {
            throw new Exception("OOPS!!! Formatting error.");
        }

        tasks.add(new Deadline(deadlineInputs[0], LocalDate.parse(deadlineInputs[1])));

        ui.print("Got it. I've added this task:");
        ui.print("  " + tasks.get(tasks.size() - 1));
        ui.print("Now you have " + tasks.size() + " tasks in the list.");

        return false;
    }
}
