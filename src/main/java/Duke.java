public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke.greet();
    }

    public static void greet() {
        Duke.line();
        System.out.println("Hello! I'm Anita MaxWynn");
        System.out.println("What can I do for you?");
        Duke.line();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.line();
    }

    public static void line() {
        System.out.println("____________________________________________________________");
    }
}
