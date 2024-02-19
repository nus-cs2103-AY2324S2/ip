package duke.command;

import duke.Storage;
import duke.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command {

    private String input;
    public DeadlineCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Storage storage) {
        try {
            String description;
            String deadline;
            LocalDate deadlineDate;
            if (!input.contains("/by")) {
                return "Add date for DEADLINE";
            } else {
                String[] parts = input.split("/by");
                description = parts[0].trim().substring("deadline".length()).trim();
                deadlineDate = LocalDate.parse(parts[1].trim());
                deadline = deadlineDate.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
                if (description.isEmpty()) {
                    return "Add description for DEADLINE";
                } else {
                    storage.addToFile(input);
                    return tasks.addDeadline(description, deadline);
                }
            }
        } catch (IOException | DateTimeParseException e) {
            return "Invalid deadline task format";
        }
    }
}
