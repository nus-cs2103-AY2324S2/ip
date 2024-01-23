import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm Dune");
        System.out.println("What can I do for you?");
        System.out.println("");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String text = scanner.nextLine();  // Read user input
            if (text.equals("bye")) {
                break;
            }
            System.out.println("    " + text);  // Output user input
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
