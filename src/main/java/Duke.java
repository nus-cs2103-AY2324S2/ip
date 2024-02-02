import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private List<Task> todolist = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private static final String SEP = "\t__________________________________________";
    public static void main(String[] args) {
        // hi
        System.out.println(SEP);
        System.out.println("\tHello! I'm JOSEPH JOSHTUR!!!");
        System.out.println("\tWhat can I do for you?");
        System.out.println(SEP);

        Duke duke = new Duke();
        duke.handleInput();

        // bye
        System.out.println(SEP);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(SEP);
    }

    public void handleInput() {
        String input;
        String[] parts;
        String command;
        String details;
        int taskNumber = -1;
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
                try {
                    saveData(todolist);
                } catch (IOException e) {
                    System.out.println("?");
                }

                break;
            case "mark":
                taskNumber = Integer.parseInt(details) - 1;
                todolist.get(taskNumber).markAsDone(true);
                output = "Nice! I've marked this task as done:\n\t" + todolist.get(taskNumber).toString();
                break;
            case "unmark":
                taskNumber = Integer.parseInt(details) - 1;
                todolist.get(taskNumber).markAsDone(false);
                output = "OK, I've marked this task as not done yet:\n\t" + todolist.get(taskNumber).toString();
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
                    deadlineName = d[0].trim();
                    by = d[1].trim();

                } catch (DukeException e) {
                    output = e.toString();
                    break;
                }
                Task deadline = new Deadline(deadlineName, by);
                addItem(deadline);
                output = "added deadline: " + deadline.toString();
                break;
            case "event":
                String eventName = details.split("/from ")[0].trim();
                String from = details.split("/from ")[1].split(" /to")[0].trim();
                String to = details.split("/to ")[1].trim();
                Task event = new Event(eventName, from, to);
                addItem(event);
                output = "added event: " + event.toString();
                break;
            case "delete":
                taskNumber = Integer.parseInt(details) - 1;
                Task t = removeItem(taskNumber);
                output = "Noted. I've removed this task:" + t.toString();
                break;

            default:
                DukeException e = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                output = e.toString();
            }

            if (!output.isEmpty()) {
                System.out.println(SEP);
                System.out.println("\t" + output);
                System.out.println(SEP);
            }
        }
    }

    public void addItem(Task item) {
        todolist.add(item);
    }

    public Task removeItem(int index) {
        return todolist.remove(index);
    }

    public void saveData(List<Task> todolist) throws IOException {
        String entry;
        java.nio.file.Path path = java.nio.file.Paths.get("data", "duke.txt");
        FileWriter fw = new FileWriter(path.toString(), false);
        for (Task t : todolist) {
            entry = t.getDataString() + "\n";
            fw.write(entry);
        }
        fw.close();
    }
}
