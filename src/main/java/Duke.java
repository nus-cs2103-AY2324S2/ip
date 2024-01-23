public class Duke {
    private static String lineBreak = "______________________________________________";
    public static void main(String[] args) {
        greet();
        exit();
    }

    public static void greet() {
        System.out.println(lineBreak);
        System.out.println(" Hello! I'm SnowBoy\n" + " What can I do for you?");
        System.out.println(lineBreak);
    }

    public static void exit() {
        System.out.println(lineBreak);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}
