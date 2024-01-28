import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, ParsedInput input) throws DukeException, IOException {
        if (input.numberOfPositionalArguments() < 1) {
            throw new DukeException("Please enter the task description.");
        } else if (!input.hasNamedArgument("by")) {
            throw new DukeException("Please specify the due date of the deadline task using /by [Date Time].");
        }
        String description = input.getPositionalArgument(0);
        String dueString = input.getNamedArgument("by");
        try {
            LocalDateTime due = Parser.parseDateTime(dueString);
            Task task = new Deadline(description, due);
            tasks.addTask(task);
            ui.showAddedTask(task, tasks);
            tasks.save();
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use the correct datetime format.");
        }
    }
}
