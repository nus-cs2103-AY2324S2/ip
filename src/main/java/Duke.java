import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String input;
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object

        greet();

        // Active
        while (true){
            // Take user input
            input = scanner.nextLine();  // Read user input

            if (input.equals("bye")) {
                break;
            } else {
                echo(input);
            }
        }

        exit();
    }

    public static void greet() {
        String text = "\t____________________________________________________________\n"
                + "\tHello! I'm Teemo!\n"
                + "\tWhat can I do for you?\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void exit() {
        String text = "\t____________________________________________________________\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t____________________________________________________________\n";

        System.out.println(text);
    }

    public static void echo(String text) {
        String output = "\t____________________________________________________________\n"
                + "\t" + text + "\n"
                + "\t____________________________________________________________\n";

        System.out.println(output);
    }
}

