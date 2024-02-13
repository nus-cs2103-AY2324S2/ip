package duke;
import java.util.Scanner;

class Ui {
    private static final String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    static void printLogo() {
        System.out.println("Hello from\n" + logo);
    }

    static void printWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm CharmBot ");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    static void printGoodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
    
    static String getUserCommand(Scanner scanner) {
        return scanner.nextLine();
    }

    static void printMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }
}