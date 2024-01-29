import java.util.Scanner;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        String logo = "▀█▀ ▄▀█ █▀ █▄▀ █▄█ ▄▀█ █▀█ █▀█ █▀▀ █▀█\n"
                + "░█░ █▀█ ▄█ █░█ ░█░ █▀█ █▀▀ █▀▀ ██▄ █▀▄\n";

        System.out.println("*YAP* Good morning YAPPER! *YAP*\nGreetings from\n" + logo);
    }

    public void exit() {
        String bye = "█▀▀ █▀█ █▀█ █▀▄ █▄▄ █▄█ █▀▀ █\n"
                + "█▄█ █▄█ █▄█ █▄▀ █▄█ ░█░ ██▄ ▄\n";

        System.out.println("Stoppin' the YAP...\n" + bye);
        sc.close();
    }

    public String readCommand() {
        return sc.nextLine();
    }
    public void triggerAddMessage(Task task) {
        if (task == null) {
            return;
        }
        System.out.println("Added task:\n" + task);
    }

    public void triggerDeleteMessage(Task task) {
        if (task == null) {
            return;
        }
        System.out.println("Okay, I'll stop yapping about this task:\n" + task);
    }
}
