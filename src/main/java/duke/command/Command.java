package duke.command;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;

import java.io.IOException;

/**
 * Represents a command to be executed.
 * A <code>Command</code> object corresponds to a command that the user inputs.
 * e.g. <code>todo read book</code>
 */
public class Command {
    protected String command;
    protected String input;
    public Command(String command, String input) {
        this.command = command;
        this.input = input;
    }

    public Command(){}

    /**
     * Executes the command and returns the result.
     * @param tasks the list of tasks
     * @param storage the storage object
     * @throws IOException if there is an error writing to the file
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        String description;
        String keyword;
        int index;
        String[] words = input.split("\\s+");
        switch (command) {
        case "find":
            keyword = this.input.trim().substring("find".length()).trim();
            if (keyword.isEmpty()) {
                return "Add keyword to search for";
            } else {
                return tasks.find(keyword);
            }
        case "todo":
            description = this.input.trim().substring("todo".length()).trim();
            if (description.isEmpty()) {
                return "Add description for TODO";
            } else {
                storage.addToFile(input);
                return tasks.addTodo(description);
            }
        case "deadline":
            if (!input.contains("/by")) {
                return "Add date for DEADLINE";
            } else {
                String[] parts = input.split("/by");
                description = parts[0].trim().substring("deadline".length()).trim();
                String deadline = parts[1].trim();
                if (description.isEmpty() || deadline.isEmpty()) {
                    return "Add description/deadline date for DEADLINE";
                } else {
                    storage.addToFile(input);
                    return tasks.addDeadline(description, deadline);
                }
            }
        case "event":
            if (!input.contains("/from") || !input.contains("/to")) {
                return "Add dates for EVENT";
            } else {
                String[] parts = input.split("/from| /to");
                description = parts[0].trim().substring("event".length()).trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    return "Add description/start date/end date for EVENT";
                } else {
                    storage.addToFile(input);
                    return tasks.addEvent(description, start, end);
                }
            }
        case "delete":
            index = Integer.parseInt(words[1]);
            storage.removeFromFile(index);
            return tasks.deleteTask(index);
        case "mark":
            index = Integer.parseInt(words[1]);
            storage.editLineInFile(index, 1);
            return tasks.markTask(index);
        case "unmark":
            index = Integer.parseInt(words[1]);
            storage.editLineInFile(index, 0);
            return tasks.unmarkTask(index);
        case "list":
            return tasks.list();
        }
        return "Invalid command";
    }
}
