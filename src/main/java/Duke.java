public class Duke {
    public static void main(String[] args) {
        String hLine = "________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greetMsg = "Hello! I'm Hatsune Miku!\n"
                + " What can I do for you?";
        String exitMsg = "Bye. Hope to see you again soon!";

        System.out.println("Hello from\n" + logo);
        System.out.println(hLine);
        System.out.println(greetMsg);
        System.out.println(hLine);
        System.out.println(exitMsg);
        System.out.println(hLine);
    }
}
