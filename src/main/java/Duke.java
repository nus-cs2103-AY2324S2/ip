import java.util.Objects;
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
        String invalidTxt = "\tSorry, what do you mean?\n";
        String noDescTxt = "\tPlease provide the description of your ";
        String deadlineFormTxt = "\tSorry! Please use the given format for deadline tasks:\n" +
                "\t\tdeadline (description) /by (due date/time)\n";
        String eventFormTxt = "\tSorry! Please use the given format for event tasks:\n" +
                "\t\tevent (description) /from (start) /to (end)\n";
        String noStartTxt = "\tHey, please let me know the start date/time for this task!";
        String noEndTxt = "\tHey, please let me know the end date/time for this task!";

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
                try {
                    if (fullCmd.length() < 5) throw new DukeException("Description Blank");
                    String name = fullCmd.substring(5);
                    if (name.trim().isEmpty()) throw new DukeException("Description Blank");
                    Task newTask = new ToDo(name);
                    store[numItems++] = newTask;
                    System.out.println(
                            lineTxt + addTaskTxt +
                                    "\t\t" + newTask + "\n" + "\tNow you have " +
                                    numItems + " tasks in the list.\n"
                                    + lineTxt
                    );
                } catch (DukeException e) {
                    System.out.println(lineTxt + noDescTxt + "todo task!\n" + lineTxt);
                }
            }

            else if (cmd.equals("deadline")) {
                try {
                    String[] fullCmdArr = fullCmd.split("/");
                    if (fullCmdArr.length != 2) throw new DukeException("Improper Format");
                    if (!fullCmdArr[1].substring(0, Math.min(3, fullCmdArr[1].length())).equals("by ")) throw new DukeException("Improper Format");

                    String name = fullCmdArr[0].substring(9);
                    String dueDate = fullCmd.split("/")[1].substring(3);
                    if (name.trim().isEmpty()) throw new DukeException("Description Blank");
                    if (dueDate.trim().isEmpty()) throw new DukeException("End Blank");

                    Task newTask = new Deadline(name, dueDate);
                    store[numItems++] = newTask;
                    System.out.println(
                            lineTxt + addTaskTxt +
                                    "\t\t" + newTask + "\n"
                                    + "\tNow you have " +
                                    numItems + " tasks in the list.\n"
                                    + lineTxt
                    );
                } catch (DukeException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + deadlineFormTxt + lineTxt);
                    if (errorMsg.equals("Description Blank")) System.out.println(lineTxt + noDescTxt + "deadline task!\n" + lineTxt);
                    if (errorMsg.equals("End Blank")) System.out.println(lineTxt + noEndTxt + "\n" + lineTxt);
                }
            }

            else if (cmd.equals("event")) {
                try {
                    String[] fullCmdArr = fullCmd.split("/");
                    if (fullCmdArr.length != 3) throw new DukeException("Improper Format");
                    if (!fullCmdArr[1].substring(0, Math.min(5, fullCmdArr[1].length())).equals("from ")) throw new DukeException("Improper Format");
                    if (!fullCmdArr[2].substring(0, Math.min(3, fullCmdArr[2].length())).equals("to ")) throw new DukeException("Improper Format");

                    String name = fullCmdArr[0].substring(6);
                    String start = fullCmdArr[1].substring(5);
                    String end = fullCmdArr[2].substring(3);
                    if (name.trim().isEmpty()) throw new DukeException("Description Blank");
                    if (start.trim().isEmpty()) throw new DukeException("Start Blank");
                    if (end.trim().isEmpty()) throw new DukeException("End Blank");

                    Task newTask = new Event(name, start, end);
                    store[numItems++] = newTask;
                    System.out.println(
                            lineTxt + addTaskTxt +
                                    "\t\t" + newTask + "\n"
                                    + "\tNow you have " +
                                    numItems + " tasks in the list.\n"
                                    + lineTxt
                    );
                } catch (DukeException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + eventFormTxt + lineTxt);
                    if (errorMsg.equals("Description Blank")) System.out.println(lineTxt + noDescTxt + "deadline task!\n" + lineTxt);
                    if (errorMsg.equals("Start Blank")) System.out.println(lineTxt + noStartTxt + "\n" + lineTxt);
                    if (errorMsg.equals("End Blank")) System.out.println(lineTxt + noEndTxt + "\n" + lineTxt);
                }
            }

            else {
                System.out.println(lineTxt + invalidTxt + lineTxt);
            }
        }
    }
}
