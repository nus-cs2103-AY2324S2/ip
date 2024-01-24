import java.util.Scanner;

public class TheCount {
    public static void main(String[] args) {
        // Creates a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Creates a list of tasks
        TaskList tasks = new TaskList();

        // Creates standard replies
        Reply greeting = new Greeting();
        Reply goodbye = new Goodbye();

        // Prints greeting
        greeting.displayMessage();

        // Wait for user input
        String userInput = scanner.nextLine();
        // Checks for exit condition
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                // Prints list of tasks
                tasks.printList();
            } else {
                // Adds task to list
                Task task = new Task(userInput);
                tasks.add(task);
                task.displayMessage();
            }
            userInput = scanner.nextLine();
        }
        scanner.close();

        // Prints goodbye for exit
        goodbye.displayMessage();
    }
}
