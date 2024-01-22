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
        String markTxt = "\t" + "Nice! I've marked this task as done:\n";
        String unmarkTxt = "\t" + "OK, I've marked this task as not done yet:\n";


        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        Task[] store = new Task[100];
        int numItems = 0;

        System.out.println(introTxt);

        while (!exit) {
            String cmd = sc.nextLine();

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
            else if (cmd.substring(0, Math.min(cmd.length(), 5)).equals("mark ")) {
                int updateIndex = Integer.parseInt(cmd.split(" ")[1]);
                if (updateIndex <= numItems) store[updateIndex - 1].mark();
                System.out.println(
                        lineTxt + markTxt +
                                "\t\t" + store[updateIndex - 1] + "\n" +
                                lineTxt
                );
            }

            // command unmark to uncheck task
            else if (cmd.substring(0, Math.min(cmd.length(), 7)).equals("unmark ")) {
                int updateIndex = Integer.parseInt(cmd.split(" ")[1]);
                if (updateIndex <= numItems) store[updateIndex - 1].unmark();
                System.out.println(
                        lineTxt + unmarkTxt +
                                "\t\t" + store[updateIndex - 1] + "\n" +
                                lineTxt
                );
            }

            // else add new task
            else {
                store[numItems++] = new Task(cmd);
                System.out.println(
                        lineTxt + "\t" + "added: " + cmd + "\n" + lineTxt
                );
            }
        }
    }
}
