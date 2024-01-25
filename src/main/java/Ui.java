import java.util.Scanner;

public abstract class Ui {
    private static final String startLine = "----------------"
            + "------------[Rep"
            + "ort]------------"
            + "----------------\n";

    private static final String endLine = "----------------"
            + "------------[End"
            + "ing]------------"
            + "----------------\n";
    private static void printStartLine() {
        System.out.print(Ui.startLine);
    }

    private static void printEndLine() {
        System.out.print(Ui.endLine);
    }
    public static void start() {
        Ui.printStartLine();
        final String greetings = String.format( "Hello! I'm %s\n"
                + "What can I do for you?\n", Duke.botName);
        System.out.print(greetings);
        Ui.printEndLine();
    }

    public static void end() {
        final String endings = "Bye. It is an honor to serve you.\n"
                + "Hope to see you again soon!";
        System.out.println(endings);
        Ui.printEndLine();
    }

    public static void readCommand() {
        Scanner sc = new Scanner(System.in);
        boolean terminate = false;
        while (true) {
            final String command = sc.nextLine();
            Ui.printStartLine();
            switch (command) {
                case "bye":
                    terminate = true;
                    break;
                case "list":
                    Duke.itemStorage.listItem();
                    break;
                default:
                    Duke.itemStorage.storeItem(command);
            }
            if (terminate) {
                break;
            }
            Ui.printEndLine();
        }
    }
}
