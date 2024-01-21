import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String hLine = "\t____________________________________________________________";
        String welcome = "\tHello! I'm TalkingBot\n\tWhat can I do for you?";
        System.out.println(hLine);
        System.out.println(welcome);
        System.out.println(hLine);

        Scanner scanner = new Scanner(System.in);
        boolean continueIter = true;

        TaskList taskList = new TaskList();
        while (continueIter) {
            String entry = scanner.nextLine();
            String[] curCommand = entry.split(" ");
            System.out.println(hLine);
            switch (curCommand[0]) {
                case "bye":
                    continueIter = false;
                    break;
                case "list":
                    System.out.println(taskList);
                    break;
                case "mark":
                case "unmark":
                    int markIdxInt = Integer.valueOf(curCommand[1]);
                    Task modifiedTask = taskList.getTask(markIdxInt - 1);
                    if (curCommand[0].equals("mark")) {
                        System.out.println("\tNice! I've marked this task as done:");
                        modifiedTask.setDone(true);
                        System.out.println("\t" + modifiedTask);
                    } else {
                        System.out.println("\tAlright, I've marked this task as undone.");
                        modifiedTask.setDone(false);
                        System.out.println("\t" + modifiedTask);
                    }
                    taskList.setTask(markIdxInt - 1, modifiedTask);
                    break;
                default:
                    Task curTask = new Task(entry);
                    taskList.addTask(curTask);
                    System.out.println("\tadded: " + curTask);
            }
            System.out.println(hLine);
        }

        String bye = "Bye. Hope to see you again soon!";
        System.out.println(hLine);
        System.out.println(bye);
        System.out.println(hLine);
        scanner.close();
    }
}
