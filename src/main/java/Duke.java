public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        greet();


        exit();
    }

    public static void greet() {
        String text = "____________________________________________________________\n"
                + "Hello! I'm Teemo!\n"
                + "What can I do for you?\n"
                + "____________________________________________________________\n";

        System.out.println(text);
    }

    public static void exit() {
        String text = "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!"
                + "____________________________________________________________\n";

        System.out.println(text);
    }
}

