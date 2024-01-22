import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {

        // templates
        String lineTxt = "\t____________________________________________________________\n";
        String introTxt = lineTxt +
                "\t Hello! I'm Megatron\n" +
                "\t What can I do for you?\n" +
                lineTxt;
        String outroTxt = lineTxt +
                "\t Bye. Hope to see you again soon!\n" +
                lineTxt;
        String listingTxt = "\tHere are the tasks in your list:\n";
        String markTxt = "\tNice! I've marked this task as done:\n";
        String unmarkTxt = "\tOK, I've marked this task as not done yet:\n";
        String addTaskTxt = "\tGot it. I've added this task:\n";

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        Task[] store = new Task[100];
        int numItems = 0;

        System.out.println(introTxt);

        while (!exit) {
            String fullCmd = sc.nextLine();
            String cmd = fullCmd.split(" ")[0];

            // command bye to exit bot
            if(cmd.equals("bye")) {
                System.out.println(outroTxt);
                exit = true;
            }

            // command list to list all tasks
            else if (cmd.equals("list")) {
                String toPrint = listingTxt;
                for (int i = 0; i < numItems; i++) {
                    toPrint += "\t " + (i + 1) + "." + store[i] + "\n";
                }
                System.out.println(lineTxt + toPrint + lineTxt);
            }

            // command mark to check task
            else if (cmd.equals("mark")) {
                int updateIndex = Integer.parseInt(fullCmd.split(" ")[1]);
                if (updateIndex <= numItems) store[updateIndex - 1].mark();
                System.out.println(
                        lineTxt + markTxt +
                                "\t\t" + store[updateIndex - 1] + "\n" +
                                lineTxt
                );
            }

            // command unmark to uncheck task
            else if (cmd.equals("unmark")) {
                int updateIndex = Integer.parseInt(fullCmd.split(" ")[1]);
                if (updateIndex <= numItems) store[updateIndex - 1].unmark();
                System.out.println(
                        lineTxt + unmarkTxt +
                                "\t\t" + store[updateIndex - 1] + "\n" +
                                lineTxt
                );
            }

            // else add new task
            else if (cmd.equals("todo")) {
                Task newTask = new ToDo(fullCmd.substring(5));
                store[numItems++] = newTask;
                System.out.println(
                        lineTxt + addTaskTxt +
                                "\t\t" + newTask + "\n"
                        + "\tNow you have " +
                                numItems + " tasks in the list.\n"
                                + lineTxt
                );
            }

            else if (cmd.equals("deadline")) {
                String name = fullCmd.substring(9)
                        .split("/")[0];
                String dueDate = fullCmd.split("/")[1].substring(3);
                Task newTask = new Deadline(name, dueDate);
                store[numItems++] = newTask;
                System.out.println(
                        lineTxt + addTaskTxt +
                                "\t\t" + newTask + "\n"
                                + "\tNow you have " +
                                numItems + " tasks in the list.\n"
                                + lineTxt
                );
            }

            else if (cmd.equals("event")) {
                String name = fullCmd.substring(6)
                        .split("/")[0];
                String start = fullCmd.split("/")[1].substring(5);
                String end = fullCmd.split("/")[2].substring(3);
                Task newTask = new Event(name, start, end);
                store[numItems++] = newTask;
                System.out.println(
                        lineTxt + addTaskTxt +
                                "\t\t" + newTask + "\n"
                                + "\tNow you have " +
                                numItems + " tasks in the list.\n"
                                + lineTxt
                );
            }

            else {
                System.out.println("Invalid Command");
            }
        }
    }
}
