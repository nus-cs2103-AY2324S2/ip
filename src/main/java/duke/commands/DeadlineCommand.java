package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.TaskList;
import duke.tasks.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final String details;
    private List<String> messages = new ArrayList<>();

    public DeadlineCommand(String details) {
        this.details = details;
    }

    public List<String> execute(TaskList tasks) throws DukeException {
        String[] parsedInput = details.split("/by ", 2);
        if (parsedInput.length != 2) {
            throw new DukeException("Please enter tasks description and deadline"
                    + "\ncorrect format: deadline *tasks description* /by *deadline*");
        }

        try {
            tasks.add(new Deadline(parsedInput[0], LocalDateTime.parse(parsedInput[1]
                    , dateTimeFormatter)));
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date/Time or Date/Time is in wrong format"
                    + "\ncorrect format: dd/MM/yyyy HHmm");
        }
        messages.add("Got it. I've added this tasks:");
        messages.add("added: " + tasks.get(tasks.size() - 1).toString());
        messages.add("Now you have " + tasks.size() + " tasks in the list.");
        return messages;
    }
}
