import java.util.ArrayList;
import java.util.Scanner;

public class Luke {
    public static void main(String[] args) {
        // Greetings
        System.out.println("__________________________________________");
        System.out.println("Hello! I'm Luke");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");

        // user inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        ArrayList<Task> list = new ArrayList<>();

        // Conditions
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("__________________________________________");
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) != null) {
                        System.out.println(i + 1 + "." + list.get(i));

                    }
                }
                System.out.println("__________________________________________");

            } else if (input.contains("mark")) {
                String[] instruction = input.split(" ");
                String markOrUnmark = instruction[0];
                int index = Integer.parseInt(instruction[1]) - 1;  // array is 0-indexed

                if (markOrUnmark.equals("mark")) {
                    list.get(index).markAsDone();
                    System.out.println("__________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(list.get(index));
                    System.out.println("__________________________________________");

                } else if (markOrUnmark.equals("unmark")) {
                    list.get(index).markAsUndone();
                    System.out.println("__________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(list.get(index));
                    System.out.println("__________________________________________");

                }

            } else {
                String[] instruction = input.split(" ");
                String type = instruction[0];

                if (type.equals("todo")) {
                    // 5 is the index after "todo ", so starts from index 5
                    String description = input.substring(5);
                    list.add(new Todo(description));

                } else if (type.equals("deadline")) {
                    // 9 is the index after "deadline ", so starts from index 9
                    // -1 to remove the space before "/by"
                    String description = input.substring(9, input.indexOf("/by") - 1);
                    // +4 to remove the "/by " and start from the index after "/by "
                    String by = input.substring(input.indexOf("/by") + 4);
                    list.add(new Deadline(description, by));

                } else if (type.equals("event")) {
                    // 6 is the index after "event ", so starts from index 6
                    // -1 to remove the space before "/from"
                    String description = input.substring(6, input.indexOf("/from") - 1);
                    // +6 to remove the "/from " and start from the index after "/from "
                    // -1 to remove the space before "/to"
                    String from = input.substring(input.indexOf("/from") + 6, input.indexOf("/to") - 1);
                    // +4 to remove the "/to " and start from the index after "/to "
                    String to = input.substring(input.indexOf("/to") + 4);
                    list.add(new Event(description, from, to));

                }

                System.out.println("__________________________________________");
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + list.get(list.size() - 1));
                if (list.size() == 1) {
                    System.out.println("Now you have " + list.size() + " task in the list.");
                } else {
                    System.out.println("Now you have " + list.size() + " tasks in the list.");
                }
                System.out.println("__________________________________________");

            }
            input = sc.nextLine();

        }

        // Bye and exits
        System.out.println("__________________________________________");
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println("__________________________________________");

    }
}
