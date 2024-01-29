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
                        System.out.println(i + 1 + ".[" + list.get(i).printStatus() + "] " + list.get(i));

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
                    System.out.println("[" + list.get(index).printStatus() + "] " + list.get(index));
                    System.out.println("__________________________________________");

                } else if (markOrUnmark.equals("unmark")) {
                    list.get(index).markAsUndone();
                    System.out.println("__________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + list.get(index).printStatus() + "] " + list.get(index));
                    System.out.println("__________________________________________");

                }

            } else {
                list.add(new Task(input));
                System.out.println("__________________________________________");
                System.out.println("added: " + input);
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
