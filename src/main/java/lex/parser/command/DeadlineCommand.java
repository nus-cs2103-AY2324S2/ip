package lex.parser.command;

import lex.TaskList;
import lex.tasks.Deadline;
import lex.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

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
        ui.print("Now you have " + tasks.size() + " lex.tasks in the list.");

        return false;
    }
}
