import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] store = new String[100];
        Boolean[] mark = new Boolean[100];
        Arrays.fill(mark, false);
        int count = 0;

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Artemis");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            System.out.println();
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i <= count; ++i) {
                    String checkedbox = mark[i - 1] ? "[X] " : "[ ] ";
                    System.out.println("     " + i + "." + checkedbox + store[i - 1]);
                }
                System.out.println("    ____________________________________________________________");
            } else if (input.contains("mark")) {
                String[] token = input.split(" ");
                int markIndex = Integer.parseInt(token[1]) - 1;

                if (token[0].equals("mark")) {
                    mark[markIndex] = true;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       [X] " + store[markIndex]);
                    System.out.println("    ____________________________________________________________");
                } else if (token[0].equals("unmark")) {
                    mark[markIndex] = false;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       [ ] " + store[markIndex]);
                    System.out.println("    ____________________________________________________________");
                }
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     added: " + input);
                store[count] = input;
                count++;
                System.out.println("    ____________________________________________________________");
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }
}
