/**
 * The IreneAI program implements a simple chat application that
 * provides simple feedbacks to the user's inputs
 *
 * @author Chai Ming How
 * @since 2024-02-01
 */

import java.util.ArrayList;
import java.util.Scanner;

public class IreneAI {
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String LINE = "____________________________________________________________";
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String chatbotName = "IreneAI";
        dividingLine(LINE);
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        dividingLine(LINE);

        // Echo message
        while (true) {
            System.out.println("You: ");
            String userInput = scanner.nextLine();
            dividingLine(LINE);

            // Terminating condition
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                dividingLine(LINE);
                break;
            } else if (userInput.startsWith("todo ")) {
                String description = userInput.substring(5);
                tasks.add(new ToDo(description));
                dividingLine(LINE);
                System.out.println("Okie. I've added this task: ");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.split(" /by ");
                String description = parts[0].substring(9);
                String deadline = parts[1];
                tasks.add(new Deadline(description, deadline));
                dividingLine(LINE);
                System.out.println("Okie. I've added this task: ");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.split(" /from ");
                String description = parts[0].substring(6);
                String[] from_to = parts[1].split(" /to ");
                String from = from_to[0];
                String to = from_to[1];
                tasks.add(new Event(description, from, to));
                dividingLine(LINE);
                System.out.println("Okie. I've added this task: ");
                System.out.println("  " + tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.equalsIgnoreCase("list")) {
                dividingLine(LINE);
                System.out.println("Documented tasks: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ": " + tasks.get(i));
                }
                dividingLine(LINE);
            } else if (userInput.startsWith("mark ")) {
                // Index in an ArrayList starts from 0, therefore deducts 1 from actual counting
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                Task task = tasks.get(index);
                task.markAsDone();
                dividingLine(LINE);
                System.out.println("Brilliant! I've marked " + task.getDescription() + " as done : )");
                System.out.println("  " + task);
                dividingLine(LINE);
            } else if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                Task task = tasks.get(index);
                task.markAsNotDone();
                dividingLine(LINE);
                System.out.println("Meow ~ I've marked " + task.getDescription() + " as not done : (");
                System.out.println("  " + task);
                dividingLine(LINE);
            }
        }
    }

    public static void dividingLine(String line){
        System.out.println(line);
    }
}
