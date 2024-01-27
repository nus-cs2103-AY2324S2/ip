import java.io.IOException;
import java.util.Scanner;

public class Duke {
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
                echoInput(scanner);
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
    private static void echoInput(Scanner scanner) {

        String input = scanner.nextLine();
        // Handle inputs
        if (input.equals("list") || input.equals("blah")) {
            printDivider(90);
            System.out.println(input);
            printDivider(90);
        } else if (input.equals("bye")) {
            throw new ExitProgramException("Fair winds to ye, me hearty! May the tide carry ye safely until our paths cross again.");
        } else {
            throw new IllegalArgumentException("Invalid input. Expected: 'list', 'blah' or 'bye'");
        }
    }
}

// Custom Exit Loop Exception class
class ExitProgramException extends RuntimeException {
    public ExitProgramException(String message) {
        super(message);
    }
}