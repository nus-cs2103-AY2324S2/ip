import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Georgie";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }

            System.out.println(" " + userInput);

        }

        scanner.close();
    }
}