import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Duke {
    private List<Task> todolist = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private static final String sep = "\t__________________________________________";
    public static void main(String[] args) {
        // hi
        System.out.println(sep);
        System.out.println("\tHello! I'm JOSEPH JOSHTUR!!!");
        System.out.println("\tWhat can I do for you?");
        System.out.println(sep);

        Duke duke = new Duke();
        duke.handleInput();

        // bye
        System.out.println(sep);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(sep);
    }

    public void handleInput() {
        String input;
        String[] parts;
        String command;
        String details;
        int taskToMark = -1;
        String output;

        while(true) {
            input = scanner.nextLine();
            parts = input.split(" ", 2);
            command = parts[0];
            details = parts.length > 1 ? parts[1] : "";
            output = "";

            if (command.equals("bye")) {
                break;
            }

            switch (command) {
            case "list":
                if (todolist.isEmpty()) {
                    output = "Todolist is empty";
                    break;
                }
                int i = 1;
                for(Task todo : todolist) {
                    output = output.concat(i + ". " + todo.toString() + "\n\t");
                    i++;
                }
                output = output.trim();
                break;
            case "mark":
                taskToMark = Integer.parseInt(details) - 1;
                todolist.get(taskToMark).markAsDone(true);
                output = "Nice! I've marked this task as done:\n\t" + todolist.get(taskToMark).toString();
                break;
            case "unmark":
                taskToMark = Integer.parseInt(details) - 1;
                todolist.get(taskToMark).markAsDone(false);
                output = "OK, I've marked this task as not done yet:\n\t" + todolist.get(taskToMark).toString();
                break;
            case "todo":
                try {
                    if (details.isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(details);
                    output = "added todo: " + todo.toString();
                    addItem(todo);
                }
                catch (DukeException e) {
                    output = e.toString();
                }
                break;
            case "deadline":
                String deadlineName;
                String by;
                try {
                    String[] d = details.split("/by");
                    if (d.length == 1) {
                        throw new DukeException("Invalid deadline task!");
                    }
                    deadlineName = d[0];
                    by = d[1];

                } catch (DukeException e) {
                    output = e.toString();
                    break;
                }
                Task deadline = new Deadline(deadlineName, by);
                addItem(deadline);
                output = "added deadline: " + deadline.toString();
                break;
            case "event":
                String eventName = details.split("/from ")[0];
                String from = details.split("/from ")[1].split(" /to")[0];
                String to = details.split("/to ")[1];
                Task event = new Event(eventName, from, to);
                addItem(event);
                output = "added event: " + event.toString();
                break;
            default:
                DukeException e = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                output = e.toString();
            }

            if (!output.isEmpty()) {
                System.out.println(sep);
                System.out.println("\t" + output);
                System.out.println(sep);
            }
        }
    }

    public void addItem(Task item) {
        todolist.add(item);
    }
}
