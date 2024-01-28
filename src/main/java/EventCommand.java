import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws DukeException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new DukeException("Please enter the task description.");
        } else if (!input.hasNamedArgument("from") || !input.hasNamedArgument("to")) {
            throw new DukeException("Please specify the duration of the event using /from [Date Time] /to [Date Time].");
        }
        String description = input.getPositionalArgument(0);
        String fromString = input.getNamedArgument("from");
        String toString = input.getNamedArgument("to");
        try {
            LocalDateTime from = Parser.parseDateTime(fromString);
            LocalDateTime to = Parser.parseDateTime(toString);
            Task task = new Event(description, from, to);
            tasks.addTask(task);
            ui.showAddedTask(task, tasks);
            tasks.save();
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use the correct datetime format.");
        }
    }
}
