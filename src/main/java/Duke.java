import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void exitMessage() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        String exitMessage = "Bye. Hope to see you again soon!";
        System.out.println(exitMessage);
    }

    public static void greeting() {
        String divider = "-----------------------------------------------------";
        System.out.println(divider);
        String welcomeMessage = "Hello! I'm ShaunBot";
        String greet = "What can I do for you?";
        System.out.println(welcomeMessage);
        System.out.println(greet);

    }
    public static void main(String[] args) {
        String divider = "-----------------------------------------------------";
        Duke.greeting();

        Duke.exitMessage();
    }
}
