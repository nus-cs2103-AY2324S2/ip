import java.io.PrintWriter;
import java.util.Scanner;

public class Tyrone {
    private static final Scanner reader = new Scanner(System.in);
    private static final PrintWriter writer = new PrintWriter(System.out, true);
    private static final TaskList taskList = new TaskList();
    public static void main(String[] args) {
        String logo = "\t████████╗██╗   ██╗██████╗  ██████╗ ███╗   ██╗███████╗\n" +
                "\t╚══██╔══╝╚██╗ ██╔╝██╔══██╗██╔═══██╗████╗  ██║██╔════╝\n" +
                "\t   ██║    ╚████╔╝ ██████╔╝██║   ██║██╔██╗ ██║█████╗\n" +
                "\t   ██║     ╚██╔╝  ██╔══██╗██║   ██║██║╚██╗██║██╔══╝\n" +
                "\t   ██║      ██║   ██║  ██║╚██████╔╝██║ ╚████║███████╗\n" +
                "\t   ╚═╝      ╚═╝   ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝\n";
        writer.println(logo +
                "\tYo, what's crackin' fam! I'm Tyrone, your digital homie.\n" +
                "\tWhat's the word? So I can help you out today.\n" +
                "\n\t____________________________________________________________\n");

        while (true) {
            String cmd = reader.nextLine();
            if (cmd.equals("bye")) {
                handleByeCommand();
                break;
            } else if (cmd.equals("list")) {
                handleListCommand();
            } else if (cmd.startsWith("todo")) {
                handleTodoCommand(cmd.substring(5));
            } else if (cmd.startsWith("deadline")) {
                handleDeadlineCommand(cmd.substring(9));
            } else if (cmd.startsWith("event")) {
                handleEventCommand(cmd.substring(6));
            } else if (cmd.startsWith("mark")) {
                handleMarkCommand(cmd);
            } else if (cmd.startsWith("unmark")) {
                handleUnmarkCommand(cmd);
            } else {
                writer.println(Tyrone.formatStringOutput("Hey homie, I think ya tripping... command entered is invalid."));
            }
        }
    }

    public static void handleByeCommand() {
        Tyrone.writer.println(Tyrone.formatStringOutput("Peace out! Crossin' my fingers for a speedy reunion, ya feel?"));
    }

    public static void handleListCommand() {
        Tyrone.writer.println(Tyrone.formatStringOutput(
                "Peep the lineup, here's the rundown of tasks on your list:\n" + "\t" + taskList.toString()));
    }

    public static void handleTodoCommand(String params) {
        ToDo newItem = new ToDo(params);
        Tyrone.taskList.addItem(newItem);
        Tyrone.writer.println(Tyrone.formatStringOutput("Got it added homie:\n" + "\t\t" + newItem.toString() + "\n\tNow you have " + taskList.getListSize() + " in the list."));
    }

    public static void handleDeadlineCommand(String params) {
        String[] inputs = params.split("/by");
        String description = inputs[0].trim();
        String deadlineDateTime = inputs[1].trim();
        Deadline newItem = new Deadline(description, deadlineDateTime);
        Tyrone.taskList.addItem(newItem);
        Tyrone.writer.println(Tyrone.formatStringOutput("Got it added homie:\n" + "\t\t" + newItem.toString() + "\n\tNow you have " + taskList.getListSize() + " in the list."));
    }

    public static void handleEventCommand(String params) {
        int fromIndex = params.indexOf("/from");
        int toIndex = params.indexOf("/to");
        String description = params.substring(0, fromIndex).trim();
        String startDateTime = params.substring(fromIndex + 6, toIndex);
        String endDateTime = params.substring(toIndex + 4);
        Event newItem = new Event(description, startDateTime, endDateTime);
        Tyrone.taskList.addItem(newItem);
        Tyrone.writer.println(Tyrone.formatStringOutput("Got it added homie:\n" + "\t\t" + newItem.toString() + "\n\tNow you have " + taskList.getListSize() + " in the list."));
    }

    public static void handleMarkCommand(String cmd) {
        int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
        taskList.markItemDone(index);
        writer.println(Tyrone.formatStringOutput("Dope! Check it, I've tagged this task as handled:\n" +
                "\t\t" + taskList.getItemToString(index)));
    }

    public static void handleUnmarkCommand(String cmd) {
        int index = Integer.parseInt(cmd.split(" ")[1]) - 1;
        taskList.unmarkItemDone(index);
        writer.println(Tyrone.formatStringOutput(
                "A'ight, I've stamped this task as still in the works:\n" +
                        "\t\t" + taskList.getItemToString(index)));
    }

    public static String formatStringOutput(String content) {
        return ("\n\t____________________________________________________________\n" +
                "\t" + content + "\n" +
                "\n\t____________________________________________________________\n");
    }
}
