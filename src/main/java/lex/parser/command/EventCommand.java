package lex.parser.command;

import lex.TaskList;
import lex.tasks.Event;
import lex.ui.Ui;

import java.time.LocalDate;

public class EventCommand implements Command {
    private final String[] inputs;
    private final TaskList tasks;
    private final Ui ui;

    public EventCommand(String[] inputs, TaskList tasks, Ui ui) {
        this.inputs = inputs;
        this.tasks = tasks;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws Exception {
        String[] eventInputs = inputs[1].split(" /from ", 2);
        String[] eventTimeInputs = eventInputs[1].split(" /to ", 2);
        if (eventTimeInputs.length < 2) {
            throw new Exception("OOPS!!! Formatting error.");
        }

        tasks.add(new Event(eventInputs[0], LocalDate.parse(eventTimeInputs[0]),
                LocalDate.parse(eventTimeInputs[1])));

        ui.print("Got it. I've added this task:");
        ui.print("  " + tasks.get(tasks.size() - 1));
        ui.print("Now you have " + tasks.size() + " lex.tasks in the list.");

        return false;
    }
}
