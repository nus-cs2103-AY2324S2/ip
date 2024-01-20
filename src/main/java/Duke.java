import java.util.Scanner;

public class Duke {
    private static final Scanner SCANNER = new Scanner(System.in);

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
            String[] messages = {input};
            Duke.print(messages);
            input = Duke.SCANNER.nextLine();
        }
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
        String[] messages = {"Hello! I'm Duke", "What can I do for you?"};
        Duke.print(messages);
    }

    private static void printExit() {
        String[] messages = {"Bye. Hope to see you again soon!"};
        Duke.print(messages);
    }
}
