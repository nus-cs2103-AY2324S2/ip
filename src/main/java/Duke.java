import java.util.Scanner;

public class Duke {
    private static void line() {
        System.out.println("____________________________________________________________");
    }
    private static void intro() {
        line();
        System.out.println(" Hello I'm NoisyChatter");
        System.out.println(" What can I do for you?");
        line();

    }

    private static void farewell() {
        line();
        System.out.println(" Bye. Hope to see you again soon!");
        line();
    }
    private static void echo(String word) {
        line();
        System.out.println("  " + word);
        line();
    }

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                farewell();
                break;
            }
            else {
                echo(command);
            }
        }
    }
}


