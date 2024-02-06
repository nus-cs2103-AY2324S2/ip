import java.util.Scanner;

/**
 * Represents the user interface of the ChatBro.
 */
public class Ui {
    public static void printWelcome() {
        System.out.println("_________________________\n"
                + " __  __       __\n"
                + " \\ \\/ /__    / /\n"
                + "  \\  / _ \\  /_/ \n"
                + "  /_/\\___/ (_)\n\n"
                + "I'm ChatBro!\n"
                + "What can I do for you bro?\n"
                + "Use the available commands: list, bye, mark, unmark, delete, OR\n"
                + "create a new task (todo, deadline, event) to store in your list bro.\n"
                + "_________________________\n");
    }
    public static void printLine() {
        System.out.println("_________________________");
    }
    public static void printBye() {
        printLine();
        System.out.println("Hasta la vista bro!");
        printLine();
    }
    public static void printError(String error) {
        printLine();
        System.out.println(error);
        printLine();
    }
}
