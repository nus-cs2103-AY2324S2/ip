public class Duke {
    public static void greetUser() {
        printSeparator();
        System.out.println("Hello! I'm MicroManager");
        System.out.println("What can I do for you?");
        printSeparator();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        printSeparator();
    }

    public static void printSeparator() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        greetUser();
        exit();
    }
}
