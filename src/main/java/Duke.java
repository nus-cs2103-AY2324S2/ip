public class Duke {
    public void greet() {
        System.out.println("Hello! I'm Bot\nWhat can I do for you? \n");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon! \n");
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        bot.greet();
        bot.exit();
    }
}
