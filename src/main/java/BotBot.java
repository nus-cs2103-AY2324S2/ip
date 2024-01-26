import java.util.Scanner;

public class BotBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BotBot.greet();

        while (scanner.hasNext()) {
            String next = scanner.next();
            if (next.equals("bye")) {
                BotBot.exit();
                break;
            }
            BotBot.print(next);
            BotBot.divider();
        }

        scanner.close();
    }

    private static void print(String string) {
        System.out.println(string);
    }
    private static void divider() {
        BotBot.print("########################################");
    }
    public static void greet() {
        BotBot.divider();
        BotBot.print("I am BotBot!\nWhat can I do for you?\n");
        BotBot.divider();
    }
    public static void exit() {
        BotBot.print("Goodbye! See you soon!\n");
        BotBot.divider();
    }
}
