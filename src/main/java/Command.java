import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Command {
    protected TaskList tasks;
    protected Storage storage;
    protected String command;

    protected String input;
    public Command(String command, String input) {
        this.command = command;
        this.input = input;
    }

    public Command(){}

    public void execute(TaskList tasks, Storage storage) throws IOException {
        String description;
        int index;
        String[] words = input.split("\\s+");
        switch (command) {
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
