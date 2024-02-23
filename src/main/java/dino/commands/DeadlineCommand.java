package dino.commands;

import dino.DinoException.DinoException;
import dino.tasks.Deadline;
import dino.tasks.TaskList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * The `DeadlineCommand` class is a subclass of the `Command` class in Java that represents a command to add a
 * deadline task to a task list.
 */
public class DeadlineCommand extends Command {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private final String details;
    private final List<String> messages = new ArrayList<>();


    public DeadlineCommand(String details) {
        this.details = details;
    }

    /**
     * The function takes in a task list and a string input, parses the input to extract the task description and
     * deadline, adds a new Deadline task to the task list with the parsed details, and returns a list of messages
     * indicating the success of the operation.
     * 
     * @param tasks A TaskList object that represents a list of tasks.
     * @return List of Strings messages whihc will be printed to user later.
     */
    public List<String> execute(TaskList tasks) throws DinoException {
        String[] parsedInput = details.split("/by ", 2);
        if (parsedInput.length != 2) {
            throw new DinoException("Please enter tasks description and deadline"
                    + "\ncorrect format: deadline *tasks description* /by *deadline*");
        }

        try {
            tasks.add(new Deadline(parsedInput[0], LocalDateTime.parse(parsedInput[1]
                    , dateTimeFormatter)));
        } catch (DateTimeParseException e) {
            throw new DinoException("Invalid Date/Time or Date/Time is in wrong format"
                    + "\ncorrect format: dd/MM/yyyy HHmm");
        }

        messages.add("Got it. I've added this task:");
        messages.add("added: " + tasks.get(tasks.size() - 1).toString());
        messages.add("Now you have " + tasks.size() + " tasks in the list.");
        return messages;
    }
}
