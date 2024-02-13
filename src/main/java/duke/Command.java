package duke;
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
    public void execute(TaskList tasks, Storage storage) throws IOException {
        String description;
        String keyword;
        int index;
        String[] words = input.split("\\s+");
        switch (command) {
        case "find":
            keyword = this.input.trim().substring("find".length()).trim();
            if (keyword.isEmpty()) {
                System.out.println("Add keyword to search for");
            } else {
                tasks.find(keyword);
            }
            break;
        case "todo":
            description = this.input.trim().substring("todo".length()).trim();
            if (description.isEmpty()) {
                System.out.println("Add description for TODO");
            } else {
                tasks.addTodo(description);
                storage.addToFile(input);
            }
            break;
        case "deadline":
            if (!input.contains("/by")) {
                System.out.println("Add date for DEADLINE");
            } else {
                String[] parts = input.split("/by");
                description = parts[0].trim().substring("deadline".length()).trim();
                String deadline = parts[1].trim();
                if (description.isEmpty() || deadline.isEmpty()) {
                    System.out.println("Add description/deadline date for DEADLINE");
                } else {
                    tasks.addDeadline(description, deadline);
                    storage.addToFile(input);
                }
            }
            break;
        case "event":
            if (!input.contains("/from") || !input.contains("/to")) {
                System.out.println("Add dates for EVENT");
            } else {
                String[] parts = input.split("/from| /to");
                description = parts[0].trim().substring("event".length()).trim();
                String start = parts[1].trim();
                String end = parts[2].trim();
                if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    System.out.println("Add description/start date/end date for EVENT");
                } else {
                    tasks.addEvent(description, start, end);
                    storage.addToFile(input);
                }
            }
            break;
        case "delete":
            index = Integer.parseInt(words[1]);
            storage.removeFromFile(index);
            tasks.deleteTask(index);
            break;
        case "mark":
            index = Integer.parseInt(words[1]);
            storage.editLineInFile(index, 1);
            tasks.markTask(index);
            break;
        case "unmark":
            index = Integer.parseInt(words[1]);
            storage.editLineInFile(index, 0);
            tasks.unmarkTask(index);
            break;
        case "list":
            tasks.list();
            break;
        }
    }
}
