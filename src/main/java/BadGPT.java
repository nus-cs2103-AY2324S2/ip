import java.util.Scanner;

public class BadGPT {
    public static void main(String[] args) {
        String name = "BadGPT";

        System.out.println("____________________________________________________________\n" +
                "Hello! I'm " + name + ".\n" + "What can I do for you?\n" +
                "____________________________________________________________");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String cmd = sc.next();

            // Exits the program if user enters "bye". Else, echoes the input.
            if (cmd.equals("bye")) {
                break;
            } else {
                echo(cmd);
            }
        }

        bye();
    }

    public static void echo(String input) {
        System.out.println("____________________________________________________________\n" +
                input + "\n____________________________________________________________\n");
    }

    public static void bye() {
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
        System.exit(0);
    }
}
