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
        Task[] taskList = new Task[100];
        //Number of task in the list
        int taskNum = 0;

        //Printing of Start text
        PrintingString(start);

        Scanner in = new Scanner(System.in);

        while(!finished) {
            String s = in.next();

            if (s.equalsIgnoreCase("bye")) {
                finished = true;
            } else if (s.equalsIgnoreCase("list")) {
                PrintingList(taskList, taskNum);
            } else if (s.equalsIgnoreCase("mark")) {
                int num = in.nextInt() - 1;
                taskList[num].markAsDone();
                PrintingString("Nice! I've marked this task as done\n" + "  " + taskList[num]);
            } else if (s.equalsIgnoreCase("unmark")) {
                int num = in.nextInt() - 1;
                taskList[num].markAsUndone();
                PrintingString("OK, I've marked this task as not done yet\n" + "  " + taskList[num]);
            } else {
                String out = s + in.nextLine();
                taskList[taskNum] = new Task(out);
                PrintingString("added: " + out + "\n");
                taskNum++;
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

    private static void PrintingList(Task[] lst, int size) {
        //Function to produce the string for the list to be printed
        String out = "Here are the tasks in your list:\n";
        for(int i = 1; i < size + 1; i++) {
            out += i + "." + lst[i - 1];
        }
        PrintingString(out);
    }
}
