import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Duke.greet();

        Scanner sc = new Scanner(System.in);
        boolean input = true;
        while (input) {
            String message = sc.next();
            if (message.equals("bye")) {
                input = false;
            }
            Duke.echo(message);
        }

        Duke.exit();

    }

    public static void greet() {
        String logo = "▀█▀ ▄▀█ █▀ █▄▀ █▄█ ▄▀█ █▀█ █▀█ █▀▀ █▀█\n"
                + "░█░ █▀█ ▄█ █░█ ░█░ █▀█ █▀▀ █▀▀ ██▄ █▀▄\n";

        System.out.println("Greetings from\n" + logo);
    }

    public static void exit() {
        String bye = "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";

        System.out.println("Exiting TaskYapper...\n" + bye);
    }

    public static void echo(String message) {
        System.out.println(message);
    }
}
