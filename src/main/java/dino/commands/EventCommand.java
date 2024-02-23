package dino.commands;

import dino.DinoException.DinoException;
import dino.tasks.Event;
import dino.tasks.TaskList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The `EventCommand` class is used to create and execute commands for adding event tasks to a task list.
 */
public class EventCommand extends Command {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final String details;
    private final List<String> messages = new ArrayList<>();


    public EventCommand(String details) {
        this.details = details;
    }

    /**
     * The function takes in a task list and a string input, parses the input to extract event details and dates,
     * adds the event to the task list, and returns a list of messages.
     * 
     * @param tasks A TaskList object that represents a list of tasks.
     * @return List of Strings messages which will be printed to user later.
     */
    public List<String> execute(TaskList tasks) throws DinoException {
        String[] parsedInput = details.split("/from ", 2);

        if (parsedInput.length != 2) {
            throw new DinoException("Please enter event description and time in the correct format"
                    + "\ncorrect format: event *event name* /from dd/MM/yyyy HHmm /to dd/MM/yyyy HHmm");
        }

        String[] parsedDates= parsedInput[1].split(" /to ", 2);

        if (parsedDates.length != 2) {
            throw new DinoException("Please enter event description and time in the correct format"
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
            throw new DinoException("Invalid Date/Time or Date/Time is in wrong format"
                    + "\ncorrect format: dd/MM/yyyy HHmm");
        }

        return messages;
    }
}
