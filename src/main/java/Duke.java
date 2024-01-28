import java.util.Scanner;

public class Duke {
    private static final String NAME = "Arctic";
    private static final Integer BORDER_LEN = 60;

    private static String duplicateChar(Character c, Integer num) {
        return String.valueOf(c).repeat(num);
    }

    private static String getBorder() {
        return duplicateChar('_', BORDER_LEN);
    }

    private static void userGreeting () {
        System.out.println(getBorder());
        System.out.printf("Hello! I'm %s\n", NAME);
        System.out.println("What can I do for you?");
        System.out.println(getBorder());
    }

    private static void userFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(getBorder());
    }

    private static void echoCommandOutput(String command) {
        System.out.println(getBorder());
        System.out.println(command);
        System.out.println(getBorder());
    }

    private static void handleCommands() {
        Scanner scnr = new Scanner(System.in);
        while (true) {
            String command = scnr.nextLine();
            if (command.equals("bye")) {
                echoCommandOutput("Bye. Hope to see you again soon!");
                break;
            }
            echoCommandOutput(command);
        }
    }

    public static void main(String[] args) {
        userGreeting();
        userFarewell();

        handleCommands();
    }
}
