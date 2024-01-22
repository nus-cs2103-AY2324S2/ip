import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String input;
        String line = "_________________________________";
        System.out.println(line);
        System.out.println("Hello! I'm ChatterPal!");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("list");
            } else if (input.equals("blah")) {
                System.out.println("blah");
            }
            input = sc.nextLine();
        }
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
