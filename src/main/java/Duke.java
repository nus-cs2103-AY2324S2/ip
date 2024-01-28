import java.io.IOException;
import java.security.PrivilegedActionException;
import java.util.Scanner;

public class Duke {
    private static final int TASKS_MAX = 100;
    private static Task[] tasks = new Task[TASKS_MAX];
    private static int taskCount = 0;
    public static void main(String[] args) {
        String logo = " _  _   __    ____  ____ \n" +
                "( \\/ ) /__\\  (  _ \\(  _ \\\n" +
                " \\  / /(__)\\  )   / )   /\n" +
                " (__)(__)(__)(_)\\_)(_)\\_)\n";

        System.out.println(logo);
        printDivider(90);
        System.out.println("Ahoy! I be Yarr \nWhat be yer command, me heartie?");
        printDivider(90);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                handleInput(scanner);
            } catch (IllegalArgumentException e) {
                printDivider(90);
                System.out.println(e.getMessage());
                printDivider(90);
            } catch (ExitProgramException e) {
                printDivider(90);
                System.out.println(e.getMessage());
                printDivider(90);
                break;
            }
        }

    }
    private static void printDivider(int length){
        char divider = 0x2500 ;
        for (int i = 0; i < length; i++) {
            System.out.print(divider);
        }
        System.out.println();
    }

    // Solution below adapted from https://www.geeksforgeeks.org/ways-to-read-input-from-console-in-java/
    private static void handleInput(Scanner scanner) {
        String input = scanner.nextLine();
        String[] wordArray = input.split(" ", 0);
        // Handle inputs
        if (input.equals("bye")) {
            throw new ExitProgramException("Fair winds to ye, me hearty! May the tide carry ye safely until our paths cross again.");
        } else if (input.equals("list")) {
            printDivider(90);
            System.out.println("Behold, yer roster of endeavors!");
            for (int i = 0; i < taskCount; i++) {
                String tempNum = Integer.toString(i + 1);
                System.out.println(tempNum + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            }
            printDivider(90);
        } else if (wordArray[0].equals("mark") || wordArray[0].equals("unmark")) {
            if (wordArray.length != 2) {
                throw new IllegalArgumentException("Expected 2 arguments");
            } else {
                try {
                    int tempIndex = Integer.parseInt(wordArray[1]);
                    if (tempIndex > taskCount) {
                        throw new IllegalArgumentException("Expected integer between 1 and " + Integer.toString(taskCount) + " as the second argument");
                    } else {
                        printDivider(90);
                        if (wordArray[0].equals("mark")) {
                            tasks[tempIndex - 1].markAsDone();
                            System.out.println("X marks the spot. I've crossed this task of yer list, me heartie!");
                        } else {
                            tasks[tempIndex - 1].markAsNotDone();
                            System.out.println("The winds be shiftin', and I be lettin' this task sail with the breeze unmarked.");
                        }
                        System.out.println("[" + tasks[tempIndex - 1].getStatusIcon() + "] " + tasks[tempIndex - 1].getDescription());
                        printDivider(90);
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Expected integer between 1 and " + Integer.toString(taskCount) + " as the second argument");
                }
            }
        }
        else {
            printDivider(90);
            tasks[taskCount] = new Task(input);
            taskCount += 1;
            System.out.println("added: " + input);
            printDivider(90);
        }
    }
}

// Custom Exit Loop Exception class
class ExitProgramException extends RuntimeException {
    public ExitProgramException(String message) {
        super(message);
    }
}