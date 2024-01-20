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
            String markIdxStr = curCommand[1];
            int markIdxInt = Integer.valueOf(markIdxStr);
            System.out.println(hLine);
            switch (curCommand[0]) {
                case "bye":
                    continueIter = false;
                    break;
                case "list":
                    System.out.println(taskList);
                    break;
                case "mark":
                    Task modifiedTask = taskList.getTask(markIdxInt - 1);
                    modifiedTask.setDone(true);
                    taskList.setTask(markIdxInt - 1, modifiedTask);
                case "unmark":
                    Task modifiedTask = taskList.getTask(markIdxInt - 1);
                    modifiedTask.setDone(false);
                    taskList.setTask(markIdxInt - 1, modifiedTask);
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
