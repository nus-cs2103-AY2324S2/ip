import java.util.Scanner;

public class Bob {
    private static final Scanner SCANNER = new Scanner(System.in);

    private static int numberOfTasks = 0;
    private static final String[] TASKS = new String[100]; // Could make 100 a constant

    private static void printFormatted(String[] lines) {
        String horizontalLine = "    .-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n";
        StringBuilder formatted = new StringBuilder(horizontalLine);
        for (String line : lines) {
            formatted.append("     ");
            formatted.append(line);
            formatted.append('\n');
        }
        formatted.append(horizontalLine);
        System.out.println(formatted);
    }

    private static void printFormatted(String line) {
        printFormatted(new String[]{ line });
    }

    private static void handleList() {
        String[] numberedTasks = new String[numberOfTasks];
        for (int i = 0; i < numberOfTasks; i++) {
            numberedTasks[i] = (i + 1) + ". " + TASKS[i];
        }
        printFormatted(numberedTasks);
    }

    public static void main(String[] args) {
        printFormatted(new String[]{ "yo im bob", "what do you want" });

        while (true) {
            String command = SCANNER.nextLine();

            if (command.equals("bye")) {
                break;
            }

            if (command.equals("list")) {
                handleList();
            } else {
                TASKS[numberOfTasks] = command;
                numberOfTasks++;
                printFormatted("added: " + command);
            }
        }

        printFormatted("ok");
    }
}
