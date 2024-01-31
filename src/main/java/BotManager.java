public class BotManager {
    private final String name;

    BotManager(String name) {
        this.name = name;
    }

    void greeting() {
        System.out.println("    Hello! I'm " + name);
        System.out.println("    What can I do for you?\n");
    }

    void exit() {
        System.out.println("    Bye. Hope to see you again soon!\n");
    }

    void answer(String prompt) {
        System.out.println("    " + prompt + '\n');
    }
}