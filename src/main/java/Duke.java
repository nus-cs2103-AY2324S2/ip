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
                Task todo = new Todo(details);
                output = "added todo: " + todo.toString();
                addItem(todo);
                break;
            case "deadline":
                String deadlineName = details.split("/by")[0];
                String by = details.split("/by")[1];
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
                break;
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
