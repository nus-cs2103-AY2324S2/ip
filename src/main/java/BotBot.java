public class BotBot {
    public static void main(String[] args) {
        BotBot.greet();
        BotBot.exit();
    }
    private static void divider() {
        System.out.println("###############################\n");
    }
    public static void greet() {
        BotBot.divider();
        System.out.println("I am BotBot!\nWhat can I do for you?\n");
        BotBot.divider();
    }
    public static void exit() {
        System.out.println("Goodbye! See you soon!\n");
        BotBot.divider();
    }
}
