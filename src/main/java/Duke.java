import java.util.Scanner;
public class Duke {

    Scanner scanner = new Scanner(System.in);
    private String name = "NotDuke";

    private void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    private void input() {
        String input = scanner.next();
        if (input.equals("bye")) {
            return;
        }
        echo(input);
        input();
    }

    private void echo(String input) {
        System.out.println(input);
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.input();
        bot.exit();
    }
}
