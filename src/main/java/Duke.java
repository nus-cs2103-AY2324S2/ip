import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] store = new String[100];
        Boolean[] mark = new Boolean[100];
        String[] status = new String[100];
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
                    String checkbox = mark[i - 1] ? "[X] " : "[ ] ";
                    System.out.println("     " + i + "." + status[i - 1] + checkbox + store[i - 1]);
                }
                System.out.println("    ____________________________________________________________");
            } else if (input.contains("mark")) {
                String[] token = input.split(" ");
                int markIndex = Integer.parseInt(token[1]) - 1;

                if (token[0].equals("mark")) {
                    mark[markIndex] = true;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + status[markIndex] + "[X] " + store[markIndex]);
                    System.out.println("    ____________________________________________________________");
                } else if (token[0].equals("unmark")) {
                    mark[markIndex] = false;
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + status[count] + "[ ] " + store[markIndex]);
                    System.out.println("    ____________________________________________________________");
                }
            } else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
                String time = "";
                String[] token = input.split("/");

                if (input.contains("todo")) {
                    token[0] = input.replace("todo ", "");
                    status[count] = "[T]";
                } else if (input.contains("deadline")) {
                    input = input.replace("deadline ", "");
                    status[count] = "[D]";
                    time = "(" + token[1].replace("by", "by:") + ")";
                } else if (input.contains("event")) {
                    input = input.replace("event ", "");
                    status[count] = "[E]";
                    time = "(" + token[1].replace("from", "from:") + token[2].replace("to", "to:") + ")";
                }

                token[0] = token[0] + time;
                store[count] = token[0];

                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + status[count] + "[ ] " + token[0]);
                System.out.println("     Now you have " + (count + 1) + " tasks in the list.");
                System.out.println("    ____________________________________________________________");

                count++;
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
