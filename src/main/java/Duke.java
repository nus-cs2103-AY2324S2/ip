import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE = "____________________________________________________________";

    private static void sayGreetings() {
        System.out.println(LINE);
        System.out.println("Hello! I'm SKY");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    private static void sayBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    private static void echo(String input) {
        System.out.println(input);
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        sayGreetings();
        while (true) {
            String input = sc.nextLine();
            System.out.println(LINE);
            if (input.equals("bye")) {
                break;
            }
            echo(input);
        }
        sayBye();
    }
}
