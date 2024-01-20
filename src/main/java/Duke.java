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
            String curCommand = scanner.nextLine();
            System.out.println(hLine);
            switch (curCommand) {
                case "bye":
                    continueIter = false;
                    break;
                case "list":
                    System.out.println(taskList);
                    break;
                default:
                    Task curTask = new Task(curCommand);
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
