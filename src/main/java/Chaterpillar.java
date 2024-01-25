public class Chaterpillar {
    public static void greet() {
        System.out.println("Hello! I'm Chaterpillar");
        System.out.println("What can I do for you?");
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void print_horizontal_line() {
        String line = "-".repeat(50);
        System.out.println(line);
    }
    public static void main(String[] args) {
        print_horizontal_line();
        greet();
        print_horizontal_line();
        exit();
        print_horizontal_line();
    }
}
