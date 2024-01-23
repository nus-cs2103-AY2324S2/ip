import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        System.out.println("Hello! I'm Bert!");
        System.out.println("What can I do for you?");

        do {
            userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                break;
            }
            System.out.println(userInput);
        } while (true);

        System.out.println("Bye! Hope to see you again!");
        scanner.close();
    }
}
