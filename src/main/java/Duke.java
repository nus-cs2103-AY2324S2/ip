import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        String introMessage = " /\\_/\\\n" +
                "\t( o.o )\n" +
                "\t > ^ <\n" +
                "\tNya-ice to meet you! I'm Toothless :D\n" +
                "\tWhat can I do for you?";
        printMessage(introMessage);

        while (isRunning) {
            String userInput = scanner.nextLine();

            if (userInput.strip().toLowerCase().equals("bye")) {
                isRunning = false;
            } else {
                printMessage(userInput);
            }
        }

        printMessage("Bye. Purr-lease chat again soon!");
    }

    public static void printMessage(String message) {
        String line = "\t____________________________________________________________";
        System.out.println(line);
        System.out.println("\t" + message);
        System.out.println(line);
    }
}
