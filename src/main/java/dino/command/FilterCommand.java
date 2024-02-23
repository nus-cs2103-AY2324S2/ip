package dino.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import dino.DinoException;
import dino.Storage;
import dino.TaskList;
import dino.task.Deadline;
import dino.task.Event;

/**
 * Represents a command to filter a task from the task list based on specified date.
 */
public class FilterCommand extends Command {

    private String argument;
    public FilterCommand(String argument) {
        this.argument = argument;
    }
    @Override
    public String execute(TaskList taskList, Storage storage) throws IOException, DinoException {
        assert argument != null : "Date cannot be null";
        StringBuilder result = new StringBuilder();

        try {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(argument, dateFormatter);

            result.append("Tasks for ").append(date).append(":\n");

            taskList.getTaskList().stream()
                    .filter(task -> task instanceof Deadline)
                    .map(task -> (Deadline) task)
                    .filter(deadline -> deadline.getDateTime().toLocalDate().equals(date))
                    .forEach(deadline -> result.append(deadline).append("\n"));

            taskList.getTaskList().stream()
                    .filter(task -> task instanceof Event)
                    .map(task -> (Event) task)
                    .filter(event -> event.getStartTime().toLocalDate().equals(date)
                            || event.getEndTime().toLocalDate().equals(date))
                    .forEach(event -> result.append(event).append("\n"));

        } catch (DateTimeParseException e) {
            result.append("Error parsing date: ").append(e.getMessage());
        }
        return result.toString();
    }
}
