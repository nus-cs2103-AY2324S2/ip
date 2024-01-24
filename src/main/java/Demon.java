import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demon {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Hello Master, I'm Demon 👿\nWhat can I do for you today?");
        String input = sc.nextLine();
        // list to store actions specified by user.
        List<Task> list = new ArrayList<>();

        System.out.println("Entered: '" + input + "'");
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println("---------------------------------------------------------");
                System.out.print("List of things to do 📑:\n");
                for (int i = 1; i <= list.size(); i++) {
                    Task item = list.get(i-1);
                    System.out.println("\t" + i + "." + item.toString());
                }
                System.out.println("--------------------------------------------------------");
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();
            } else if (input.split(" ",2)[0].equalsIgnoreCase("unmark")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    Task item = list.get(num-1);
                    item.markNotDone();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Sure Master, I've marked this task as not done ❌:");
                    System.out.println(item.toString());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(e + ". Please provide valid integer.");
                } finally {
                    input = sc.nextLine();
                }
            } else if (input.split(" ",2)[0].equalsIgnoreCase("mark")) {
                try {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    Task item = list.get(num-1);
                    item.markDone();
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Sure Master, I've marked this task as done ✅:");
                    System.out.println(item.toString());
                    System.out.println("--------------------------------------------------------");
                    System.out.println("Anything else? Please let me know: ");
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println(e + ". Please provide valid integer.");
                } finally {
                    input = sc.nextLine();
                }

            } else if (input.split(" ",2)[0].equalsIgnoreCase("deadline")) {
                String deadline = input.split(" ", 2)[1];
                String description = deadline.split("/")[0];
                String by = deadline.split("/")[1].split(" ",2)[1];
                Deadline item_deadline = new Deadline(description, by);
                list.add(item_deadline);
                System.out.println("--------------------------------------------------------");
                System.out.println("Yes Master, I've added this task: ");
                System.out.println(item_deadline.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("--------------------------------------------------------");
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();
            } else if (input.split(" ",2)[0].equalsIgnoreCase("todo")) {
                String toDo = input.split(" ",2)[1];
                Todo item_toDo = new Todo(toDo);
                list.add(item_toDo);
                System.out.println("--------------------------------------------------------");
                System.out.println("Yes Master, I've added this task: ");
                System.out.println(item_toDo.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("--------------------------------------------------------");
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();

            } else if (input.split(" ",2)[0].equalsIgnoreCase("event")) {
                String details = input.split(" ",2)[1];
                String description = details.split("/", 2)[0];
                String from = details.split("/from", 2)[1].split("/to")[0];
                String to = details.split("/from", 2)[1].split("/to")[1];
                Event item_event = new Event(description, from, to);
                list.add(item_event);
                System.out.println("--------------------------------------------------------");
                System.out.println("Yes Master, I've added this task: ");
                System.out.println(item_event.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                System.out.println("--------------------------------------------------------");
                System.out.println("Anything else? Please let me know: ");
                input = sc.nextLine();
            }
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("Bye Master 👋, hope you had a great time, see you ♥!");
        System.out.println("--------------------------------------------------------");
    }
}
