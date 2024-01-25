public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        greet();
        exit();
    }
    static String name = "Alfred";
    public static void greet(){
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
    }

    public static void exit(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again");
        System.out.println("____________________________________________________________");
    }
}
