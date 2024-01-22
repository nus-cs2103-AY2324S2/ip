import java.util.Scanner;
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
        Scanner scanner = new Scanner(System.in);
        lineBreak();
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            echo(input);
            lineBreak();
            input = scanner.nextLine();
        }
        farewell();
    }

    public static void lineBreak() {
        System.out.println("____________________________________________________________\n");
    }
    public static void welcome(String botName) {
        System.out.println(String.format("""
                ____________________________________________________________
                 Hello! I'm %s
                 What can I do for you?
                 """, botName));
    }

    public static void echo(String text) {
        System.out.println(String.format("""
                ____________________________________________________________
                 %s""", text));
    }

    public static void farewell() {
        System.out.println("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                 """);
    }
}
