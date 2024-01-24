import java.util.Objects;

public class Lindi {
    public static void printSeparator() {
        System.out.println("-".repeat(50));
    }
    public static void greeting() {
        String name = "Lindi";  // Log It N Do It -> LINDI

        printSeparator();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printSeparator();
    }
    public static void goodByeAndExit() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
        System.exit(0); // exit with code 0, terminates program
    }

    public static void chatLoop() {
        String userInput;
        while (true) { // This will not be an infinite loop, because goodByeAndExit() terminates the program when called
            userInput = System.console().readLine(); // Note that System.console() does not work with IDE run
            printSeparator();
            if (Objects.equals(userInput, "bye")) {
                goodByeAndExit();
            } else {
                System.out.println("You said this: " + userInput);
            }
            printSeparator();
        }
    }
    public static void main(String[] args) {
        greeting();
        chatLoop();
        goodByeAndExit();
    }
}
