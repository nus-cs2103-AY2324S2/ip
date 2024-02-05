package commands;

import DukeException.DukeException;
import tasks.TaskList;
import tasks.Deadline;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final String details;

    DeadlineCommand(String details) {
        this.details = details;
    }

    public void execute(TaskList tasks) throws DukeException {
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
        System.out.println("Got it. I've added this tasks:");
        System.out.println("added: " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
