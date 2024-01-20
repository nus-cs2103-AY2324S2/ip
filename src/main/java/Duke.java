public class Duke {
    private static String name = "The Cat that Lives in your Walls";

    private static void line() {
        for (int i = 0; i < 72; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }

    private static void hello() {
        line();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        line();
    }

    private static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
        line();
    }

    public static void main(String[] args) {
        hello();
        bye();
    }
}
