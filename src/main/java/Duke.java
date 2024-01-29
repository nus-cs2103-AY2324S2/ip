import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    private final List<Task> store;
    private Integer numItems;
    private static final String TXT_LINE = "\t____________________________________________________________\n";
    private static final String TXT_NODESC = "\tPlease provide the description of your ";
    private static final String TXT_NOEND = "\tHey, please let me know the end date/time for this task!";
    private static final String TXT_OUTBOUNDS = "\tHmm looks like this task number doesn't exist!\n";

    private static final String FILEPATH = "../data/duke.txt";

    private Duke() {
        this.store = new ArrayList<>();
        this.numItems = 0;
    }

    private void exit() {
        String outroTxt = TXT_LINE + "\t Bye. Hope to see you again soon!\n" + TXT_LINE;
        System.out.println(outroTxt);
    }

    private void list() {
        StringBuilder listingTxt = new StringBuilder("\tHere are the tasks in your list:\n");
        for (int i = 0; i < numItems; i++) {
            listingTxt.append("\t ").append(i + 1).append(".").append(store.get(i)).append("\n");
        }
        System.out.println(TXT_LINE + listingTxt + TXT_LINE);
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
            System.out.println(TXT_LINE + toPrint +
                            "\t\t" + updateTask + "\n" +
                    TXT_LINE);
            saveTasks();
        } catch (DukeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.equals("Improper Format")) System.out.println(TXT_LINE + markFormTxt + TXT_LINE);
            if (errorMsg.equals("Number Out of Bounds")) System.out.println(TXT_LINE + TXT_OUTBOUNDS + TXT_LINE);
        } catch (NumberFormatException e) {
            System.out.println(TXT_LINE + "\tInvalid number given! :(\n" + TXT_LINE);
        } catch (IOException e) {
            System.out.println(e);
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
            System.out.println(TXT_LINE + TXT_NODESC + "todo task!\n" + TXT_LINE);
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
            if (errorMsg.equals("Improper Format")) System.out.println(TXT_LINE + deadlineFormTxt + TXT_LINE);
            if (errorMsg.equals("Description Blank")) System.out.println(TXT_LINE + TXT_NODESC + "deadline task!\n" + TXT_LINE);
            if (errorMsg.equals("End Blank")) System.out.println(TXT_LINE + TXT_NOEND + "\n" + TXT_LINE);
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
            if (errorMsg.equals("Improper Format")) System.out.println(TXT_LINE + eventFormTxt + TXT_LINE);
            if (errorMsg.equals("Description Blank")) System.out.println(TXT_LINE + TXT_NODESC + "deadline task!\n" + TXT_LINE);
            if (errorMsg.equals("Start Blank")) System.out.println(TXT_LINE + noStartTxt + "\n" + TXT_LINE);
            if (errorMsg.equals("End Blank")) System.out.println(TXT_LINE + TXT_NOEND + "\n" + TXT_LINE);
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
            System.out.println(TXT_LINE + deleteTxt + "\t\t" + toRemove + "\n" +
                    "\tNow you have " + numItems + " tasks in the list.\n" +
                    TXT_LINE
            );
            saveTasks();
        } catch (DukeException e) {
            String errorMsg = e.getMessage();
            if (errorMsg.equals("Improper Format")) System.out.println(TXT_LINE + deleteFormTxt + TXT_LINE);
            if (errorMsg.equals("Number Out of Bounds")) System.out.println(TXT_LINE + TXT_OUTBOUNDS + TXT_LINE);
        } catch (NumberFormatException e) {
            System.out.println(TXT_LINE + "\tInvalid number given! :(\n" + TXT_LINE);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private void addTaskPrint(Task newTask) {
        try {
            numItems++;
            store.add(newTask);
            String addTaskTxt = "\tGot it. I've added this task:\n";
            System.out.println(TXT_LINE + addTaskTxt + "\t\t" + newTask + "\n"
                    + "\tNow you have " + numItems + " tasks in the list.\n"
                    + TXT_LINE
            );
            saveTasks();
        } catch(IOException e) {
            System.out.println(e);
        }
    }

    private void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(FILEPATH);
        String toSave = "";
        for (Task task : store) {
            toSave = toSave + task.getType() + ";;;"
                    + (task.getStatus() ? 1 : 0) + ";;;"
                    + task.getDetails() + "\n";
        }

        fw.write(toSave);
        fw.close();
    }

    private void initiate() {
        try {
            File f = new File(FILEPATH);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] taskLine = s.nextLine().split(";;;");
                String[] details = taskLine[2].split(":::");
                if (Objects.equals(taskLine[0], "T")) store.add(new ToDo(details[0]));
                else if (Objects.equals(taskLine[0], "D")) store.add(new Deadline(details[0], details[1]));
                else if (Objects.equals(taskLine[0], "E")) store.add(new Event(details[0], details[1], details[2]));

                if (Objects.equals(taskLine[1], "1")) store.get(numItems).mark();
                numItems++;
            }
        } catch (FileNotFoundException e) {
			try {
                File yourFile = new File(FILEPATH);
                yourFile.getParentFile().mkdirs();
                yourFile.createNewFile();
			} catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
		}
	}

    public static void main(String[] args) {

        String introTxt = TXT_LINE +
                "\t Hello! I'm Megatron\n" +
                "\t What can I do for you?\n" +
                TXT_LINE;
        System.out.println(introTxt);

        Duke duke = new Duke();
        duke.initiate();
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
                System.out.println(TXT_LINE + invalidTxt + TXT_LINE);
            }
        }
    }


}
