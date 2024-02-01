import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I'm Bentley\n" + "What can I do for you?\n");

        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            } else {
                System.out.println(userInput);
                System.out.println();

            }
        }

        scanner.close();
    }
}