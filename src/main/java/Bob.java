import java.util.Scanner;

public class Bob {
    private static void printFormatted(String[] lines) {
        String horizontalLine = "    ____________________________________________________________\n";
        StringBuilder formatted = new StringBuilder(horizontalLine);
        for (String line : lines) {
            formatted.append("     ");
            formatted.append(line);
            formatted.append('\n');
        }
        formatted.append(horizontalLine);
        System.out.println(formatted.toString());
    }

    private static void printFormatted(String line) {
        printFormatted(new String[]{ line });
    }

    public static void main(String[] args) {
        printFormatted(new String[]{ "Hello! I'm Bob", "What can I do for you?" });

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                break;
            }
            printFormatted(command);
        }

        printFormatted("Bye. Hope to see you again soon!");
    }
}
