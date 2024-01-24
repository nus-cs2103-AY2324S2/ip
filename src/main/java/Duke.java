import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        Duke.greet();

        Scanner sc = new Scanner(System.in);
        String[] list = new String[100];
        int listSize = 0;
        boolean input = true;
        while (input) {
            String message = sc.nextLine();
            if (message.equals("bye")) {
                input = false;
            } else if (message.equals("yap")) {
                Duke.listYaps(list, listSize);
            } else {
                Duke.echo(message);
                list[listSize] = message;
                listSize++;
            }
        }

        Duke.exit();

    }

    public static void greet() {
        String logo = "▀█▀ ▄▀█ █▀ █▄▀ █▄█ ▄▀█ █▀█ █▀█ █▀▀ █▀█\n"
                + "░█░ █▀█ ▄█ █░█ ░█░ █▀█ █▀▀ █▀▀ ██▄ █▀▄\n";

        System.out.println("*YAP* Good morning YAPPER! *YAP*\nGreetings from\n" + logo);
    }

    public static void exit() {
        String bye = "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";

        System.out.println("Stoppin' the YAP...\n" + bye);
    }

    public static void echo(String message) {
        System.out.println("added: " + message);
    }

    public static void listYaps(String[] list, int listSize) {
        for (int i = 0; i < listSize; i++) {
            System.out.println((i+1)+". "+list[i]);
        }
    }

}
