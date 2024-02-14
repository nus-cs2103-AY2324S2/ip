public class Duke {
    public static void greet() {
        String name = "Cal";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        System.out.println(logo);
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void main(String[] args) {
        greet();
        exit();
    }
}
