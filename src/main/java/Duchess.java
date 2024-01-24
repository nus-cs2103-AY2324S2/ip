import java.util.Scanner;

public class Duchess {
    public static void main(String[] args) {
        printHorizontalLine();

        printOpeningGreeting();

        printHorizontalLine();

        printEcho();

        printHorizontalLine();

    }

    //Prints an echo of user's input, exits if user inputs "bye"
    public static void printEcho() {
        Scanner scanner = new Scanner(System.in);

        // Loop to read user input
        while (true) {
            String userInput = scanner.nextLine();

            // Display the user input
            printHorizontalLine();
            System.out.println(" " + userInput);
            printHorizontalLine();

            // Check if the user wants to exit
            if (userInput.equalsIgnoreCase("bye")) {
                printClosingGreeting();
                break;  // Exit the loop
            }
        }

        // Close the scanner
        scanner.close();
    }

    //Print opening greeting
    public static void printOpeningGreeting() {
        String logo = " ____            __     \n"
                + "|  _ \\ _   ______| |      ___  ___  ___ \n"
                + "| | | | | | |  __| |__  /  _ \\/ __|/ __|  \n"
                + "| |_| | |_| | |__| ___ |   __/\\__ \\\\__ \\  \n"
                + "|____/ \\__,_|____|_| |_|\\ ___||___/|___/\n";
        System.out.println(logo);
        printHorizontalLine();
        System.out.println("Hello! I'm Duchess.");
        System.out.println("What can I do for you today?");
    }


    //Prints closing greeting
    public static void printClosingGreeting() {
        printHorizontalLine();
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    //Prints a Horizontal Line of 50 dashes
    public static void printHorizontalLine() {
        int lineLength = 50; // Specify the length of the line

        // Print the horizontal line
        for (int i = 0; i < lineLength; i++) {
            System.out.print("_");
        }

        System.out.println();
    }
}
