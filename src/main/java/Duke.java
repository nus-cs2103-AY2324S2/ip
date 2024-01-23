import java.util.*;

public class Duke {

    private final List<Task> store;
    private Integer numItems;
    private static final String lineTxt = "\t____________________________________________________________\n";
    private static final String noDescTxt = "\tPlease provide the description of your ";
    private static final String noEndTxt = "\tHey, please let me know the end date/time for this task!";
    private static final String outBoundsTxt = "\tHmm looks like this task number doesn't exist!\n";

    private Duke() {
        this.store = new ArrayList<>();
        this.numItems = 0;
    }

    private void exit() {
        String outroTxt = lineTxt + "\t Bye. Hope to see you again soon!\n" + lineTxt;
        System.out.println(outroTxt);
    }

    private void list() {
        StringBuilder listingTxt = new StringBuilder("\tHere are the tasks in your list:\n");
        for (int i = 0; i < numItems; i++) {
            listingTxt.append("\t ").append(i + 1).append(".").append(store.get(i)).append("\n");
        }
        System.out.println(lineTxt + listingTxt + lineTxt);
    }

    private void mark(String fullCmd, boolean taskComplete) {
        String markTxt = "\tNice! I've marked this task as done:\n";
        String unmarkTxt = "\tOK, I've marked this task as not done yet:\n";
        String markFormTxt = "\tSorry! To mark or unmark tasks, please do\n" +
                "\t\t(un)mark (number)\n";

        try {
            if (fullCmd.split(" ")[1].trim().isEmpty()) throw new DukeException("Improper Format");
            int updateIndex = Integer.parseInt(fullCmd.split(" ")[1]);
            if (updateIndex > numItems || updateIndex <= 0) throw new DukeException("Number Out of Bounds");
            Task updateTask = store.get(updateIndex - 1);

            if (taskComplete) updateTask.mark();
            else updateTask.unmark();
            String toPrint = taskComplete ? markTxt : unmarkTxt;
            System.out.println(lineTxt + toPrint +
                            "\t\t" + updateTask + "\n" +
                            lineTxt);
        } catch (DukeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + markFormTxt + lineTxt);
            if (errorMsg.equals("Number Out of Bounds")) System.out.println(lineTxt + outBoundsTxt + lineTxt);
        } catch (NumberFormatException e) {
            System.out.println(lineTxt + "\tInvalid number given! :(\n" + lineTxt);
        }
    }

    private void toDo(String fullCmd) {
        try {
            if (fullCmd.length() < 5) throw new DukeException("Description Blank");
            String name = fullCmd.substring(5);
            if (name.trim().isEmpty()) throw new DukeException("Description Blank");
            Task newTask = new ToDo(name);
            addTaskPrint(newTask);
        } catch (DukeException e) {
            System.out.println(lineTxt + noDescTxt + "todo task!\n" + lineTxt);
        }
    }

    private void deadline(String fullCmd) {
        String deadlineFormTxt = "\tSorry! Please use the given format for deadline tasks:\n" +
                "\t\tdeadline (description) /by (due date/time)\n";

        try {
            String[] fullCmdArr = fullCmd.split("/");
            if (fullCmdArr.length != 2) throw new DukeException("Improper Format");
            if (!fullCmdArr[1].substring(0, Math.min(3, fullCmdArr[1].length())).equals("by ")) throw new DukeException("Improper Format");

            String name = fullCmdArr[0].substring(9);
            String dueDate = fullCmd.split("/")[1].substring(3);
            if (name.trim().isEmpty()) throw new DukeException("Description Blank");
            if (dueDate.trim().isEmpty()) throw new DukeException("End Blank");

            Task newTask = new Deadline(name, dueDate);
            addTaskPrint(newTask);

        } catch (DukeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + deadlineFormTxt + lineTxt);
            if (errorMsg.equals("Description Blank")) System.out.println(lineTxt + noDescTxt + "deadline task!\n" + lineTxt);
            if (errorMsg.equals("End Blank")) System.out.println(lineTxt + noEndTxt + "\n" + lineTxt);
        }
    }

    private void event(String fullCmd) {
        String eventFormTxt = "\tSorry! Please use the given format for event tasks:\n" +
                "\t\tevent (description) /from (start) /to (end)\n";
        String noStartTxt = "\tHey, please let me know the start date/time for this task!";

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
            addTaskPrint(newTask);

        } catch (DukeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + eventFormTxt + lineTxt);
            if (errorMsg.equals("Description Blank")) System.out.println(lineTxt + noDescTxt + "deadline task!\n" + lineTxt);
            if (errorMsg.equals("Start Blank")) System.out.println(lineTxt + noStartTxt + "\n" + lineTxt);
            if (errorMsg.equals("End Blank")) System.out.println(lineTxt + noEndTxt + "\n" + lineTxt);
        }
    }

    private void delete(String fullCmd) {
        String deleteTxt = "\tNoted. I will remove this task for you:\n";
        String deleteFormTxt = "\tDid you mean to delete the task? Please do this:\n" +
                "\t\tdelete (number)\n";
        try {
            if (fullCmd.split(" ")[1].trim().isEmpty()) throw new DukeException("Improper Format");
            int deleteIndex = Integer.parseInt(fullCmd.split(" ")[1]);
            if (deleteIndex > numItems || deleteIndex <= 0) throw new DukeException("Number Out of Bounds");

            Task toRemove = store.get(deleteIndex - 1);
            store.remove(toRemove);
            numItems--;
            System.out.println(lineTxt + deleteTxt + "\t\t" + toRemove + "\n" +
                    "\tNow you have " + numItems + " tasks in the list.\n" +
                    lineTxt
            );
        } catch (DukeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.equals("Improper Format")) System.out.println(lineTxt + deleteFormTxt + lineTxt);
            if (errorMsg.equals("Number Out of Bounds")) System.out.println(lineTxt + outBoundsTxt + lineTxt);
        } catch (NumberFormatException e) {
            System.out.println(lineTxt + "\tInvalid number given! :(\n" + lineTxt);
        }
    }

    private void addTaskPrint(Task newTask) {
        numItems++;
        store.add(newTask);
        String addTaskTxt = "\tGot it. I've added this task:\n";
        System.out.println(lineTxt + addTaskTxt + "\t\t" + newTask + "\n"
                + "\tNow you have " + numItems + " tasks in the list.\n"
                + lineTxt
        );
    }

    public static void main(String[] args) {

        String introTxt = lineTxt +
                "\t Hello! I'm Megatron\n" +
                "\t What can I do for you?\n" +
                lineTxt;
        System.out.println(introTxt);

        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            String fullCmd = sc.nextLine();
            String cmd = fullCmd.split(" ")[0];

            if (cmd.equals("bye")) {
                duke.exit();
                exit = true;
            }
            else if (cmd.equals("list")) duke.list();
            else if (cmd.equals("mark")) duke.mark(fullCmd, true);
            else if (cmd.equals("unmark")) duke.mark(fullCmd, false);
            else if (cmd.equals( "todo")) duke.toDo(fullCmd);
            else if (cmd.equals( "deadline")) duke.deadline(fullCmd);
            else if (cmd.equals( "event")) duke.event(fullCmd);
            else if (cmd.equals( "delete")) duke.delete(fullCmd);
            else {
                String invalidTxt = "\tSorry, what do you mean?\n";
                System.out.println(lineTxt + invalidTxt + lineTxt);
            }
        }
    }
}
