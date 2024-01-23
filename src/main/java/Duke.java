public class Duke {
    private static void line() {
        System.out.println("____________________________________________________________");
    }
    private static void intro() {
        line();
        System.out.println(" Hello I'm NoisyChatter");
        System.out.println(" What can I do for you?");
        line();

    }

    private static void farewell() {
        System.out.println(" Bye. Hope to see you again soon!");
        line();
    }

    public static void main(String[] args) {
        intro();
        farewell();
    }
}

