public class Duke {
    static void breakLine() {
        System.out.println("---------------------------------------");
    }
    static void greet() {
        System.out.println("Hello! I'm teletubby"  + "\nWhat can I do for you?");
        breakLine();
    }
    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        breakLine();
    }
    public static void main(String[] args) {
        Duke.breakLine();
        Duke.greet();
        Duke.exit();
    }
}
