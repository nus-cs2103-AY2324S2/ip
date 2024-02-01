/**
 * The IreneAI program implements a simple chat application that
 * provides simple feedbacks to the user's inputs
 *
 * @author Chai Ming How
 * @since 2024-02-01
 */

import java.util.Scanner;

public class IreneAI {
    public static void main(String[] args){
        final int MAX_TASKS = 100;
        String[] tasks = new String[MAX_TASKS];
        int taskCount = 0;
        Scanner scanner = new Scanner(System.in);

        String chatbotName = "IreneAI";
        String line = "____________________________________________________________";
        dividingLine(line);
        System.out.println(" Hello! I'm " + chatbotName);
        System.out.println(" What can I do for you?");
        dividingLine(line);

        // Echo message
        while (true) {
            System.out.println("You: ");
            String userInput = scanner.nextLine();
            dividingLine(line);

            // Terminating condition
            if (userInput.equalsIgnoreCase("bye")) {
                // Print the farewell message
                System.out.println(" Bye. Hope to see you again soon!");
                dividingLine(line);
                break;
            }

            if (userInput.equalsIgnoreCase("list")) {
                dividingLine(line);
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ": " + tasks[i]);
                }
                dividingLine(line);
            } else {
                tasks[taskCount++] = userInput;
                dividingLine(line);
                System.out.println("added: " + userInput);
                dividingLine(line);
            }
        }
    }

    public static void dividingLine(String line){
        System.out.println(line);
    }
}
