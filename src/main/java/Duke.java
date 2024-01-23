import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
                System.out.println("Hello from\n" + logo);
         */
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            if (user_input.equals("bye")) {
                exit();
            } else {
                echo(user_input);
            }
        }
        exit();
    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void echo(String userInput) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + userInput);
        System.out.println("\t____________________________________________________________");
    }
}
