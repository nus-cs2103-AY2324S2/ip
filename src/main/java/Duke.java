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
        String order = sc.nextLine();
        List<String> array = new ArrayList<>();

        while (!order.equals("bye")) {

            if (order.equals("list")) {
                System.out.println("____________________________________________________________");
                for(int i = 0; i < array.size(); i++) {
                    System.out.println((i +1 ) + ": " + array.get(i));
                }
                System.out.println("____________________________________________________________");
                order = sc.nextLine();
            } else {
                array.add(order);
                System.out.println("____________________________________________________________");
                System.out.println("added: " + order);
                System.out.println("____________________________________________________________");
                order = sc.nextLine();
            }
        }

        if (order.equals("bye")) {
            System.out.println("____________________________________________________________");
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
        }
        sc.close();
    }
}
