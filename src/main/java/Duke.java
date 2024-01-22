import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String[] store = new String[100];
        int itemCounts = 0;

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm MazeDeneroBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            String command = scan.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i=0; i<itemCounts; i++) {
                    System.out.println(i+1 + ". " + store[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                store[itemCounts] = command;
                itemCounts++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
