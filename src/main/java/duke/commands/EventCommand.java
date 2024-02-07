package duke.commands;

import duke.DukeException.DukeException;
import duke.tasks.Event;
import duke.tasks.TaskList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventCommand extends Command {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final String details;
    private List<String> messages = new ArrayList<>();

    public EventCommand(String details) {
        this.details = details;
    }
    public List<String> execute(TaskList tasks) throws DukeException {
        String[] parsedInput = details.split("/from ", 2);

        if (parsedInput.length != 2) {
            throw new DukeException("Please enter event description and time in the correct format"
                    + "\ncorrect format: event *event name* /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm");
        }

        String[] parsedDates= parsedInput[1].split(" /to ", 2);

        if (parsedDates.length != 2) {
            throw new DukeException("Please enter event description and time in the correct format"
                    + "\ncorrect format: event *event name* /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm");
        }


        try {
            tasks.add(
                    new Event(parsedInput[0], LocalDateTime.parse(parsedDates[0], dateTimeFormatter)
                            , LocalDateTime.parse(parsedDates[1], dateTimeFormatter)));
            messages.add("Got it. I've added this tasks:");
            messages.add("added: " + tasks.get(tasks.size() - 1).toString());
            messages.add("Now you have " + tasks.size() + " tasks in the list.");
        } catch (Exception e) {
            throw new DukeException("Invalid Date/Time or Date/Time is in wrong format"
                    + "\ncorrect format: dd/MM/yyyy HHmm");
        }
        return messages;
    }

}
