import java.util.*;

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
        String deleteTxt = "\tNoted. I will remove this task for you:\n";
        String outBoundsTxt = "\tHmm looks like this task number doesn't exist!\n";
        String addTaskTxt = "\tGot it. I've added this task:\n";
        String invalidTxt = "\tSorry, what do you mean?\n";
        String noDescTxt = "\tPlease provide the description of your ";
        String markFormTxt = "\tSorry! To mark or unmark tasks, please do\n" +
                "\t\t(un)mark (number)\n";
        String deadlineFormTxt = "\tSorry! Please use the given format for deadline tasks:\n" +
                "\t\tdeadline (description) /by (due date/time)\n";
        String eventFormTxt = "\tSorry! Please use the given format for event tasks:\n" +
                "\t\tevent (description) /from (start) /to (end)\n";
        String deleteFormTxt = "\tDid you mean to delete the task? Please do this:\n" +
                "\t\tdelete (number)\n";
        String noStartTxt = "\tHey, please let me know the start date/time for this task!";
        String noEndTxt = "\tHey, please let me know the end date/time for this task!";


        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        List<Task> store = new ArrayList<Task>();
        int numItems = 0;

        System.out.println(introTxt);

        while (!exit) {
            String fullCmd = sc.nextLine();
            String cmd = fullCmd.split(" ")[0];

            // command bye to exit bot
            if (cmd.equals("bye")) {
                System.out.println(outroTxt);
                exit = true;
            }

            // command list to list all tasks
            else if (cmd.equals("list")) {
                String toPrint = listingTxt;
                for (int i = 0; i < numItems; i++) {
                    toPrint += "\t " + (i + 1) + "." + store.get(i) + "\n";
                }
                System.out.println(lineTxt + toPrint + lineTxt);
            }

            // command mark to check task
            else if (cmd.equals("mark")) {
                try {
                    if (fullCmd.split(" ")[1].trim().isEmpty()) throw new DukeException("Improper Format");
                    int updateIndex = Integer.parseInt(fullCmd.split(" ")[1]);

                    if (updateIndex <= numItems && updateIndex > 0) store.get(updateIndex - 1).mark();
                    else throw new DukeException("Number Out of Bounds");

                    System.out.println(
                            lineTxt + markTxt +
                                    "\t\t" + store.get(updateIndex - 1) + "\n" +
                                    lineTxt
                    );
                } catch (DukeException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + markFormTxt + lineTxt);
                    if (errorMsg.equals("Number Out of Bounds")) System.out.println(lineTxt + outBoundsTxt + lineTxt);
                } catch (NumberFormatException e) {
                    System.out.println(lineTxt + "\tInvalid number given! :(\n" + lineTxt);
                }
            }

            // command unmark to uncheck task
            else if (cmd.equals("unmark")) {
                try {
                    if (fullCmd.split(" ")[1].trim().isEmpty()) throw new DukeException("Improper Format");
                    int updateIndex = Integer.parseInt(fullCmd.split(" ")[1]);

                    if (updateIndex <= numItems && updateIndex > 0) store.get(updateIndex - 1).unmark();
                    else throw new DukeException("Number Out of Bounds");

                    System.out.println(
                            lineTxt + unmarkTxt +
                                    "\t\t" + store.get(updateIndex - 1) + "\n" +
                                    lineTxt
                    );
                } catch (DukeException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + markFormTxt + lineTxt);
                    if (errorMsg.equals("Number Out of Bounds")) System.out.println(lineTxt + outBoundsTxt + lineTxt);
                } catch (NumberFormatException e) {
                    System.out.println(lineTxt + "\tInvalid number given! :(\n" + lineTxt);
                }
            }

            // else add new task
            else if (cmd.equals("todo")) {
                try {
                    if (fullCmd.length() < 5) throw new DukeException("Description Blank");
                    String name = fullCmd.substring(5);
                    if (name.trim().isEmpty()) throw new DukeException("Description Blank");
                    Task newTask = new ToDo(name);
                    numItems++;
                    store.add(newTask);
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
                    numItems++;
                    store.add(newTask);
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
                    numItems++;
                    store.add(newTask);
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

            else if (cmd.equals("delete")) {
                try {
                    if (fullCmd.split(" ")[1].trim().isEmpty()) throw new DukeException("Improper Format");
                    int deleteIndex = Integer.parseInt(fullCmd.split(" ")[1]);
                    if (deleteIndex > numItems || deleteIndex <= 0) throw new DukeException("Number Out of Bounds");

                    Task toRemove = store.get(deleteIndex - 1);
                    store.remove(toRemove);
                    numItems--;
                    System.out.println(
                            lineTxt + deleteTxt +
                                    "\t\t" + toRemove + "\n" +
                                    "\tNow you have " +
                                    numItems + " tasks in the list.\n"
                                    + lineTxt
                    );
                } catch (DukeException e) {
                    String errorMsg = e.getMessage();
                    if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + deleteFormTxt + lineTxt);
                    if (errorMsg.equals("Number Out of Bounds")) System.out.println(lineTxt + outBoundsTxt + lineTxt);
                } catch (NumberFormatException e) {
                    System.out.println(lineTxt + "\tInvalid number given! :(\n" + lineTxt);
                }


            }

            else {
                System.out.println(lineTxt + invalidTxt + lineTxt);
            }
        }
    }
}
