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
            Duke.print(input);
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

    private static void print(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + msg);
        System.out.println("____________________________________________________________\n");
    }

    private static void printGreetings() {
        String msg = "Hello! I'm Duke\n What can I do for you?";
        Duke.print(msg);
    }

    private static void printExit() {
        String msg = "Bye. Hope to see you again soon!";
        Duke.print(msg);
    }
}
