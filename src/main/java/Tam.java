public class Tam {
    static String dividerText = "____________________________________________________________\n";
    public static void main(String[] args) {
        Tam.greet();
        Tam.exit();
    }

    public static void greet() {
        String greetText = "Hello! I'm Tam the Task Manager!\nWhat can I do for you?\n";
        System.out.print(dividerText);
        System.out.print(greetText);
        System.out.print(dividerText);
    }

    public static void exit() {
        String exitText = "Bye. Hope to see you again soon!\n";
        System.out.print(exitText);
        System.out.print(dividerText);
    }
}
