import java.util.Scanner;

public class Duke {
    public void greet() {
        System.out.println("Hello! I'm Bot\nWhat can I do for you? \n");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon! \n");
    }

    public void echo(String input) {
        System.out.println(input + "\n");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            bot.echo(input);
            input = sc.nextLine();
        }

        sc.close();

        bot.exit();
    }
}
