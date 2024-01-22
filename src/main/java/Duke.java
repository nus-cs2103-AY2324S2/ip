import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String botName = "KokBot";
        String[] store = new String[100];
        int next = 0;
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
        while (true) {
            if (input.equals("bye")){
                break;
            } else if (input.equals("list")) {
                printList(store, next);
            } else {
                store[next] = input;
                next++;
                echo(input);
            }
            lineBreak();
            input = scanner.nextLine();
        }
        farewell();
    }

    public static void lineBreak() {
        System.out.println("____________________________________________________________\n");
    }

    public static void printList(String[] store, int next) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < next; i++) {
            System.out.println(String.format("%d. %s", i+1, store[i]));
        }
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
                 added: %s""", text));
    }

    public static void farewell() {
        System.out.println("""
                ____________________________________________________________
                 Bye. Hope to see you again soon!
                ____________________________________________________________
                 """);
    }
}
