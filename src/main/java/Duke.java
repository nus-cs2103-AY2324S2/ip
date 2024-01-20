public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage(logo);
        printMessage("Hello! I'm Toothless :D\nWhat can I do for you?");
        printMessage("Bye. Hope to see you again soon!");
    }

    public static void printMessage(String message) {
        System.out.println(message);
        String line = "____________________________________________________________";
        System.out.println(line);
    }
}
