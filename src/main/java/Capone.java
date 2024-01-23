import java.util.Objects;
import java.util.Scanner;

public class Capone {
    public static void printWelcomeMsg() {
        String logo = "░█▀▀░█▀█░█▀█░█▀█░█▀█░█▀▀░\n"
                + "░█░░░█▀█░█▀▀░█░█░█░█░█▀▀░\n"
                + "░▀▀▀░▀░▀░▀░░░▀▀▀░▀░▀░▀▀▀░";
        System.out.printf("Hello! I'm \n%s\nWhat can I do for you?\n%n", logo);
    }

    public static void processInputs() {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Read the user input
        String input = scanner.nextLine();

        while (!Objects.equals(input, "bye")) {
            // Print user's input.
            System.out.println(input);

            // Read the user's next input.
            input = scanner.nextLine();
        }

        // If user entered "bye", exit program.
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {

        Capone.printWelcomeMsg();

        Capone.processInputs();
    }
}