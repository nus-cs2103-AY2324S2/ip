import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    String divider = "--------------------------------------------------";
    ArrayList<Task> taskList = new ArrayList<Task>();

    public String[] splitCommand(String command) {
        return command.split("\\s+");
    }

    public boolean parseCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        System.out.println(this.divider);

        switch (splitCommand(command)[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                return false;
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < this.taskList.size(); i++) {
                    Task currentTask = this.taskList.get(i);
                    System.out.println(i + 1 + "." + currentTask.display + " " + currentTask.description);
                }
                System.out.println(this.divider);
                break;
            case "mark":
                String markMessage = "Nice! I've marked this task as done:\n  ";
                int index = Integer.parseInt(splitCommand(command)[1]);
                Task currentTask = this.taskList.get(index - 1);
                currentTask.changeDone();
                System.out.println(markMessage + currentTask.display + " " + currentTask.description);
                System.out.println(this.divider);
                break;
            case "unmark":
                markMessage = "Ok, I've marked this task as not done yet:\n  ";
                index = Integer.parseInt(splitCommand(command)[1]);
                currentTask = this.taskList.get(index - 1);
                currentTask.changeDone();
                System.out.println(markMessage + currentTask.display + " " + currentTask.description);
                System.out.println(this.divider);
                break;
            default:
                this.taskList.add(new Task(command));
                System.out.println("added: " + command);
                System.out.println(this.divider);
        }

        return true;
    }


    public static void main(String[] args) {
        Duke theGiantPeach = new Duke();

        System.out.println(theGiantPeach.divider);
        System.out.println("Hello! I'm TheGiantPeach\nWhat can I do for you?");
        System.out.println(theGiantPeach.divider);

        while (true) {
            if (!theGiantPeach.parseCommand()) {
                break;
            }
        }

        System.out.println(theGiantPeach.divider);
    }
}
