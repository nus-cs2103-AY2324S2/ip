import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm fakegpt\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.toLowerCase().equals("bye")) {
            System.out.println(userInput);
            userInput = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
