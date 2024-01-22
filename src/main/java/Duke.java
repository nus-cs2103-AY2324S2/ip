import java.util.*;

public class Duke {
    private static final String BOT_NAME = "Felix";
    public static void printHorizontalLine(int length) {
        for (int i = 0; i < length; i++) System.out.print('_');
        System.out.println();
    }
    public static void printIntroduction() {
        printHorizontalLine(60);
        System.out.printf("Hello! I'm %s\nWhat can I do for you?\n",BOT_NAME);
        printHorizontalLine(60);
    }

    public static void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine(60);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        boolean loop = true;
        printIntroduction();
        while (loop) {
            String line = scanner.nextLine();
            printHorizontalLine(60);
            if (line.equals("bye")) {
                loop = false;
                printGoodbye();
            } else {
                System.out.println(line);
                printHorizontalLine(60);
            }
        }
    }
}
