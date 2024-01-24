import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Mona {
    public static void main(String[] args) {
        String introduction = "  ____________________________________________________________\n"
                + "   Hello! I'm Mona\n"
                + "   What can I do for you?\n"
                + "  ____________________________________________________________\n";
        String farewell = "  ____________________________________________________________\n"
                + "   Bye. Hope to see you again soon!\n"
                + "  ____________________________________________________________";
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        System.out.println(introduction);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(farewell);
                break;
            }
            if (input.equals("list")) {
                System.out.println("  ____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    Task currTask = tasks.get(i);
                    System.out.println("    " + (i + 1) + ". " + currTask);
                }
                System.out.println("  ____________________________________________________________");
            } else if (input.startsWith("todo")) {
                String details = input.substring(5);
                Todo newTask = new Todo(details);
                tasks.add(newTask);
                String response = "  ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     " + newTask + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
            } else if (input.startsWith("deadline")) {
                String details = input.substring(9);
                String[] parts = details.split(" /by ");
                Deadline newTask = new Deadline(parts[0], parts[1]);
                tasks.add(newTask);
                String response = "  ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     " + newTask + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
            } else if (input.startsWith("event")) {
                String[] details = input.substring(6).split(" /from ");
                String[] startAndEnd = details[1].split(" /to ");
                Event newTask = new Event(details[0], startAndEnd[0], startAndEnd[1]);
                tasks.add(newTask);
                String response = "  ____________________________________________________________\n"
                        + "     Got it. I've added this task:\n"
                        + "     " + newTask + "\n"
                        + "     Now you have " + tasks.size() + " tasks in the list.\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = tasks.get(taskIndex);
                currTask.markAsDone();
                String response = "  ____________________________________________________________\n"
                        + "     Nice! I've marked this task as done: \n"
                        + "     " + currTask + "\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
            } else if (input.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                Task currTask = tasks.get(taskIndex);
                currTask.markAsNotDone();
                String response = "  ____________________________________________________________\n"
                        + "     OK, I've marked this task as not done yet: \n"
                        + "     " + currTask + "\n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
            } else {
                String response = "  ____________________________________________________________\n"
                        + "     added: " + input + "\n"
                        + "  ____________________________________________________________\n";
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(response);
            }
        }

    }
}
