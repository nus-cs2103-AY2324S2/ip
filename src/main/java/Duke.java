import java.util.Scanner;

public class Duke {
    static void greeting(String botName) {
        System.out.println("Hello! I'm " + botName + "\n"
                + "What can I do for you?");
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void Echo() {
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.next();
            if (userInput.equals("bye")) {
                isExit = true;
            } else {
                System.out.println(userInput);
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String botName = "Zizhen";
        greeting(botName);

        Echo();

        exit();

    }
}
