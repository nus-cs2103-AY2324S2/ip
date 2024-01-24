import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Mona {
    public static void main(String[] args) throws MonaException{
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
                if (input.length() < 6) {
                    String response = "  ____________________________________________________________\n"
                            + "     OOPS!!! The description of a todo cannot be empty \n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
                    throw new MonaException("Mona needs a description in order to add the todo task...");
                }
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
                if (input.length() < 10) {
                    String response = "  ____________________________________________________________\n"
                            + "     OOPS!!! The description of a deadline cannot be empty \n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
                    throw new MonaException("Mona needs a description in order to add the deadline task...");
                }
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
                if (input.length() < 7) {
                    String response = "  ____________________________________________________________\n"
                            + "     OOPS!!! The description of an event cannot be empty \n"
                            + "  ____________________________________________________________\n";
                    System.out.println(response);
                    throw new MonaException("Mona needs a description in order to add the event task...");
                }
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
                        + "     OOPS!!! I'm sorry, but I don't know what that means :< \n"
                        + "  ____________________________________________________________\n";
                System.out.println(response);
                throw new MonaException("Mona does not recognise this command...");
            }
        }

    }
}
