import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Georgie";
        String[] tasks = new String[100];
        int taskCounter = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                for (int i = 0; i < taskCounter; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                tasks[taskCounter] = userInput;
                taskCounter++;
                System.out.println("added: " + userInput);
            }
        }

        scanner.close();
    }
}