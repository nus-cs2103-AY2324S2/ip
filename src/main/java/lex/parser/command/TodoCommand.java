package lex.parser.command;

import lex.tasks.TaskList;
import lex.tasks.Todo;
import lex.ui.Ui;

public class TodoCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

    public TodoCommand(String[] inputs, TaskList tasks, Ui ui) {
        this.inputs = inputs;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws Exception {
        if (inputs.length < 2) {
            throw new Exception("OOPS!!! Formatting error.");
        }

        tasks.add(new Todo(inputs[1]));

        ui.print("Got it. I've added this task:");
        ui.print("  " + tasks.get(tasks.size() - 1));
        ui.print("Now you have " + tasks.size() + " tasks in the list.");

        return false;
    }
}
