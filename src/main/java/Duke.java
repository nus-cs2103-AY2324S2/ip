import java.util.*;
import java.io.*;

public class Duke {
    public static void main(String[] args) throws Exception {
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Yappy\n    What can I do for you?");
        System.out.println("    ____________________________________________________________");

        ArrayList<Task> AL = new ArrayList<>();

        while(true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine();
            String[] token = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i <= AL.size(); i++) {
                    System.out.println("    " + i + ".[" + AL.get(i-1).getStatusIcon() + "] " + AL.get(i-1).getDescription());
                }
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("mark")) {
                AL.get(Integer.parseInt(token[1])-1).markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       [" + AL.get(Integer.parseInt(token[1])-1).getStatusIcon() + "] " + AL.get(Integer.parseInt(token[1])-1).getDescription());
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("unmark")) {
                AL.get(Integer.parseInt(token[1])-1).markAsUndone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       [" + AL.get(Integer.parseInt(token[1])-1).getStatusIcon() + "] " + AL.get(Integer.parseInt(token[1])-1).getDescription());
                System.out.println("    ____________________________________________________________");
            } else {
                Task t = new Task(input);
                AL.add(t);
                System.out.println("    ____________________________________________________________");
                System.out.println("    added: " + input);
                System.out.println("    ____________________________________________________________");
            }
        }

        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
