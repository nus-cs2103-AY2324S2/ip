public class Duke {

    private String name = "NotDuke";

    private void greet() {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.exit();
    }
}
