package duke.command;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private String input;
    public EventCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Storage storage) {
        try {
            String description;
            String start;
            String end;
            LocalDate startDate;
            LocalDate endDate;
            if (!input.contains("/from") || !input.contains("/to")) {
                return "Add dates for EVENT";
            } else {
                String[] parts = input.split("/from| /to");
                description = parts[0].trim().substring("event".length()).trim();
                startDate = LocalDate.parse(parts[1].trim());
                endDate = LocalDate.parse(parts[2].trim());
                start = startDate.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
                end = endDate.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
                if (description.isEmpty()) {
                    return "Add description for EVENT";
                } else {
                    storage.addToFile(input);
                    return tasks.addEvent(description, start, end);
                }
            }
        } catch (IOException | DateTimeParseException e) {
            return "Invalid event task format";
        }
    }
}
