import java.util.*;

public class Duke {
    public static void main(String[] args) {
/*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

 */
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Doye\n" + "What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner sc = new Scanner(System.in);
        String order = "";

        while (!order.equals("bye")) {

            order = sc.nextLine();

            System.out.println("____________________________________________________________");
            System.out.println(order);
            System.out.println("____________________________________________________________");
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");

        sc.close();
    }
}
