import java.util.Scanner;

public class TheCount {
    public static void main(String[] args) {
        // Creates a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Creates standard replies
        Reply greeting = new Greeting();
        Reply goodbye = new Goodbye();

        // Prints greeting
        greeting.displayMessage();

        // Wait for user input
        String userInput = scanner.nextLine();
        // Checks for exit condition
        while (!userInput.equals("bye")) {
            Reply replyToUser = new Reply(userInput);
            replyToUser.displayMessage();
            userInput = scanner.nextLine();
        }
        scanner.close();

        // Prints goodbye for exit
        goodbye.displayMessage();
    }
}
