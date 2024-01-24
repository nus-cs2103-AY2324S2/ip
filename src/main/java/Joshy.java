import java.util.Objects;
import java.util.Scanner;

public class Joshy {
    public static void main(String[] args) {

        // instantiate new tasklist
        Task[] taskList = new Task[100];
        int counter = 0;

        // Introduction
        System.out.println("   ______________________________________________");
        System.out.println("   Hello! I'm Joshy");
        System.out.println("   What can I do for you?");
        System.out.println("   ______________________________________________");

        // Read the text entered by user line by line
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // runs the program until user inputs "bye"
        while (!Objects.equals(input, "bye")) {
            System.out.println("   ______________________________________________");

            // delimited using " "
            String[] tokens = input.split(" ");

            // mark task
            if (Objects.equals(tokens[0], "mark")) {
                int item = Integer.parseInt(tokens[1]) - 1;
                taskList[item].markTask();

                System.out.println("   Nice! I've marked this task as done:");
                System.out.println("      " + taskList[item].getStatus());
            }

            // unmark task
            else if (Objects.equals(tokens[0], "unmark")) {
                int item = Integer.parseInt(tokens[1]) - 1;
                taskList[item].unmarkTask();

                System.out.println("   OK, I've marked this task as not done yet:");
                System.out.println("      " + taskList[item].getStatus());
            }

            // list out items
            else if (Objects.equals(input, "list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println("   " + (i + 1) + ". " + taskList[i].getStatus());
                }
            }

            // normal inputs
            else {
                taskList[counter] = new Task(input);
                counter++;
                System.out.println("   added: " + input);
            }

            System.out.println("   ______________________________________________");

            input = sc.nextLine();
        }

        // Bye message
        System.out.println("   ______________________________________________");
        System.out.println("   Bye. Hope to see you again soon!");
        System.out.println("   ______________________________________________");
    }
}
