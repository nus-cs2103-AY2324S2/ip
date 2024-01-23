import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        List<Task> tasks = new ArrayList<>();

        String chatBotName = "Nicky";
        pw.println("____________________________________________________________");
        pw.println("Hello! I'm " + chatBotName + "\nWhat can I do for you?");
        pw.println("____________________________________________________________");
        pw.flush();

        while (true) {
            String currentResponse = br.readLine();
            pw.println("____________________________________________________________");

            if (currentResponse.equals("bye")) {
                break;
            } else if (currentResponse.startsWith("mark ")) {
                int index = Integer.parseInt(currentResponse.substring(5)) - 1;
                tasks.get(index).markAsDone();
                pw.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
            } else if (currentResponse.startsWith("unmark ")) {
                int index = Integer.parseInt(currentResponse.substring(7)) - 1;
                tasks.get(index).markAsNotDone();
                pw.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
            } else if (currentResponse.equals("list")) {
                pw.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    pw.println((i + 1) + ". " + tasks.get(i));
                }
            }  else {
                    if (currentResponse.startsWith("todo ")) {
                    String description = currentResponse.substring(5);
                    Todo newTodo = new Todo(description);
                    tasks.add(newTodo);
                    pw.println("Got it. I've added this task:\n  " + newTodo);
                } else if (currentResponse.startsWith("deadline ")) {
                    String[] parts = currentResponse.substring(9).split(" /by ");
                    String description = parts[0];
                    String by = parts[1];
                    Deadline newDeadline = new Deadline(description, by);
                    tasks.add(newDeadline);
                    pw.println("Got it. I've added this task:\n  " + newDeadline);
                } else if (currentResponse.startsWith("event ")) {
                    String[] parts = currentResponse.substring(6).split(" /from ");
                    String description = parts[0];
                    String[] timeParts = parts[1].split(" /to ");
                    String from = timeParts[0];
                    String to = timeParts[1];
                    Event newEvent = new Event(description, from, to);
                    tasks.add(newEvent);
                    pw.println("Got it. I've added this task:\n  " + newEvent);
                }
                pw.println("Now you have " + tasks.size() + " tasks in the list.");
            }
            pw.println("____________________________________________________________");
            pw.flush();
        }

        br.close();
        pw.println("Bye. Hope to see you again soon!");
        pw.println("____________________________________________________________");
        pw.flush();
        pw.close();
    }
}