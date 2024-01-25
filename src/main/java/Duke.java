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
            } else if(input.equals("blah")) {
                throw new DukeException("SOMETHING WENT WRONG!! Invalid input.");
            } else if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                for (int i = 1; i <= AL.size(); i++) {
                    System.out.println("       " + i + "." + AL.get(i-1));
                }
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("mark")) {
                AL.get(Integer.parseInt(token[1])-1).markAsDone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + AL.get(Integer.parseInt(token[1])-1));
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("unmark")) {
                AL.get(Integer.parseInt(token[1])-1).markAsUndone();
                System.out.println("    ____________________________________________________________");
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       " + AL.get(Integer.parseInt(token[1])-1));
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("deadline")) {
//                Task[] tasks = new Task[100];
                String[] tokenD = input.split("/");
                Task d = new Deadline(tokenD[0].substring(9), tokenD[1].substring(3));
                AL.add(d);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + d);
                System.out.println("     Now you have " + AL.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("todo")) {
                if(input.substring(4).trim().isEmpty()) {
                    throw new DukeException("SOMETHING WENT WRONG!! No description found for your todo.");
                }
                Task t = new Todos(input.substring(4).trim());
                AL.add(t);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + t);
                System.out.println("     Now you have " + AL.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else if(token[0].equals("event")) {
                String[] tokenE = input.split("/");
                Task e = new Events(tokenE[0].substring(6), tokenE[1].substring(5), tokenE[2].substring(3));
                AL.add(e);
                System.out.println("    ____________________________________________________________");
                System.out.println("     Got it. I've added this task:");
                System.out.println("       " + e);
                System.out.println("     Now you have " + AL.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else {
                Task n = new Task(input);
                AL.add(n);
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
