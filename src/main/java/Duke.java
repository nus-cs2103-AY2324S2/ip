public class Duke {
    public static void main(String[] args) {
        String botName = "KokBot";
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        welcome(botName);
        farewell();
    }

    public static void welcome(String botName) {
        System.out.println(String.format("""
                ____________________________________________________________
                 Hello! I'm %s
                 What can I do for you?
                 """, botName));
    }

    public static void farewell() {
        System.out.println("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                 """);
    }
}
