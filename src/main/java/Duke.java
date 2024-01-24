import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Starting and Ending Text
        String start = "Hello! I'm Unknown \n"
                + "What can I do for you? \n";
        String end = "Bye. Hope to see you again soon!\n";

        //Boolean value to indicate whether the user has finished
        boolean finished = false;

        //Array to contain the task list
        ArrayList<Task> taskList = new ArrayList<Task>();

        //Printing of Start text
        PrintingString(start);

        Scanner in = new Scanner(System.in);

        while(!finished) {
            String s = in.next();

            if (s.equalsIgnoreCase("bye")) {
                finished = true;
            } else if (s.equalsIgnoreCase("list")) {
                PrintingList(taskList);
            } else if (s.equalsIgnoreCase("mark")) {
                int num = in.nextInt() - 1;
                taskList.get(num).markAsDone();
                PrintingString("Nice! I've marked this task as done\n" + "  " + taskList.get(num));
            } else if (s.equalsIgnoreCase("unmark")) {
                int num = in.nextInt() - 1;
                taskList.get(num).markAsUndone();
                PrintingString("OK, I've marked this task as not done yet\n" + "  " + taskList.get(num));
            } else if (s.equalsIgnoreCase("todo")) {
                String out = in.nextLine();
                taskList.add(new ToDos(out));
                PrintingAdd(taskList.get(taskList.size() - 1), taskList.size());
            } else if (s.equalsIgnoreCase("deadline")) {
                String out = in.nextLine();
                String[] split = out.split("/");
                taskList.add(new Deadlines(split[0], split[1].substring(3)));
                PrintingAdd(taskList.get(taskList.size() - 1), taskList.size());
            } else if (s.equalsIgnoreCase("event")) {
                String out = in.nextLine();
                String[] split = out.split("/");
                taskList.add(new Events(split[0], split[1].substring(5), split[2].substring(3)));
                PrintingAdd(taskList.get(taskList.size() - 1), taskList.size());
            } else {
                String out = s + in.nextLine();
                taskList.add(new Task(out));
                PrintingString("added: " + out + "\n");
            }
        }

        //Printing of End text
        PrintingString(end);
    }

    private static void PrintingString(String str) {
        //Function to add the line in front and behind the text
        String lnBreak = "_______________________________________________________________\n";
        System.out.println(lnBreak + str + lnBreak);
    }

    private static void PrintingList(ArrayList<Task> lst) {
        //Function to produce the string for the list to be printed
        String out = "Here are the tasks in your list:\n";
        for(int i = 1; i < lst.size() + 1; i++) {
            out += i + "." + lst.get(i - 1) + "\n";
        }
        PrintingString(out);
    }

    private static void PrintingAdd(Task task, int size) {
        PrintingString("Got it. I've added this task: \n" + task + "\nNow you have " + size + " tasks in the list.\n");
    }
}
