import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    String divider = "--------------------------------------------------";
    ArrayList<Task> taskList = new ArrayList<Task>();

    public String[] splitCommand(String command) {
        String words[] = command.split("\\s+");
        String currentString = "";
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            if (i == 0) {
                result.add(words[i]);
                continue;
            }
            if (words[i].charAt(0) == '/') {
                result.add(currentString);
                currentString = "";
                continue;
            }
            currentString += words[i] + " ";
        }
        result.add(currentString);
        return result.toArray(new String[0]);
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
                    System.out.println(i + 1 + "." + currentTask);
                }
                System.out.println(this.divider);
                break;
            case "mark":
                String markMessage = "Nice! I've marked this task as done:\n  ";
                int index = Integer.parseInt(splitCommand(command)[1].replaceAll("\\s", ""));
                Task currentTask = this.taskList.get(index - 1);
                currentTask.changeDone();
                System.out.println(markMessage + currentTask);
                System.out.println(this.divider);
                break;
            case "unmark":
                markMessage = "Ok, I've marked this task as not done yet:\n  ";
                index = Integer.parseInt(splitCommand(command)[1].replaceAll("\\s", ""));
                currentTask = this.taskList.get(index - 1);
                currentTask.changeDone();
                System.out.println(markMessage + currentTask);
                System.out.println(this.divider);
                break;
            case "todo":
                Todo currentTodo = new Todo(splitCommand(command)[1]);
                this.taskList.add(currentTodo);
                System.out.println("Got it. I've added this task:\n  " + currentTodo);
                System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
                System.out.println(this.divider);
                break;
            case "deadline":
                Deadline currentDeadline = new Deadline(splitCommand(command)[1], splitCommand(command)[2]);
                this.taskList.add(currentDeadline);
                System.out.println("Got it. I've added thi task:\n  " + currentDeadline);
                System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
                System.out.println(this.divider);
                break;
            case "event":
                Event currentEvent = new Event(splitCommand(command)[1], splitCommand(command)[2], splitCommand(command)[3]);
                this.taskList.add(currentEvent);
                System.out.println("Got it. I've added thi task:\n  " + currentEvent);
                System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
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
