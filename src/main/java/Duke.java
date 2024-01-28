import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String NAME = "Arctic";
    private static final Integer BORDER_LEN = 60;
    private static final ArrayList<String> TASKS = new ArrayList<>();

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
        System.out.println(getBorder());
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(getBorder());
    }

    private static void printCommandOutput(String output) {
        System.out.println(getBorder());
        System.out.printf(output);
        System.out.println(getBorder());
    }

    private static void handleCommands() {
        Scanner scnr = new Scanner(System.in);
        while (true) {
            String command = scnr.nextLine();

            if (command.equals("list")) {
                StringBuilder output  = new StringBuilder();
                for (int i = 0; i < TASKS.size(); i++) {
                    output.append(String.format("%d. %s\n", i + 1, TASKS.get(i)));
                }
                printCommandOutput(output.toString());
            } else if (command.equals("bye")) {
                break;
            } else {
                TASKS.add(command);
                printCommandOutput(String.format("added: %s\n", command));
            }
        }
    }

    public static void main(String[] args) {
        userGreeting();

        handleCommands();

        userFarewell();
    }
}
