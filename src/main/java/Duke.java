public class Duke {
    public static void main(String[] args) {

        printBreak();
        System.out.println("Hello! I'm Klara");
        System.out.println("What can I do for you?");
        printBreak();
        System.out.println("Bye. Hope to see you again soon!");
        printBreak();

        //        String logo = " ____        _        \n"
        //                + "|  _ \\ _   _| | _____ \n"
        //                + "| | | | | | | |/ / _ \\\n"
        //                + "| |_| | |_| |   <  __/\n"
        //                + "|____/ \\__,_|_|\\_\\___|\n";
        //        System.out.println("Hello from\n" + logo);
    }

    private static void printBreak() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }


}
