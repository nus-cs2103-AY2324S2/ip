import java.util.Scanner;

public class Duke {

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Scanner scanner;

    public Duke() {
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Hello from\n" + logo);
        System.out.println("Hey there! This is Chitty-Chatty\n" + "What can I do for you?\n");

        while (true) {
            String input = scanner.nextLine();
            System.out.println("  " + input);

            if (input.equals("bye")) {
                exit();
                break;
            }
        }
    }

    public void exit() {
        System.out.println("  " + "Bye. Have a great day ahead!");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}

