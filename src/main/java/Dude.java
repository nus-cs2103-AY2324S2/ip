public class Dude {
    static final String spacer = "____________________________________________________________\n";
    static final String name = "Dude";

    private static String getGreeting() {
        return spacer + "Hello! I'm Dude\nWhat can I do for you?\n" + spacer;
    }
    private static String getGoodbye() {
        return spacer + "Bye. Hope to see you again soon!\n" + spacer;
    }
    public static void main(String[] args) {
        System.out.println(getGreeting());
        System.out.println(getGoodbye());
    }
}
