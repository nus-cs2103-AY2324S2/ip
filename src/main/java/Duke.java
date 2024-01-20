import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<String> TASKS = new ArrayList<>();

    public static void main(String[] args) {
        printBanner();
        printGreetings();
        handleInput();
        printExit();
        Duke.SCANNER.close();
    }

    private static void handleInput() {
        String input = Duke.SCANNER.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Duke.print(Duke.getTasks());
            } else {
                String[] messages = { "added: " + input };
                Duke.TASKS.add(input);
                Duke.print(messages);
            }
            input = Duke.SCANNER.nextLine();
        }
    }

    private static String[] getTasks() {
        String[] tasks = new String[Duke.TASKS.size()];
        for (int i = 0; i < Duke.TASKS.size(); i++) {
            tasks[i] = (i + 1) + ". " + Duke.TASKS.get(i);
        }
        return tasks;
    }

    private static void printBanner() {
        System.out.println(" ██████╗ █████╗ ██████╗ ██████╗ ██╗   ██╗");
        System.out.println("██╔════╝██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝");
        System.out.println("██║     ███████║██████╔╝██████╔╝ ╚████╔╝ ");
        System.out.println("██║     ██╔══██║██╔═══╝ ██╔═══╝   ╚██╔╝  ");
        System.out.println("╚██████╗██║  ██║██║     ██║        ██║   ");
        System.out.println(" ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝        ╚═╝   ");
    }

    private static void print(String[] messages) {
        System.out.println("____________________________________________________________");
        for (String msg : messages) {
            System.out.println(" " + msg);
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void printGreetings() {
        String[] messages = {"Hello! I'm Cappy", "What can I do for you?"};
        Duke.print(messages);
    }

    private static void printExit() {
        String[] messages = {"Bye. Hope to see you again soon!"};
        Duke.print(messages);
    }
}
