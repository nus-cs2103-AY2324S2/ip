import java.util.Scanner;
import task.taskList;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm KAI\n" + "What can i do for you?\n");
        taskList tasklist = new taskList();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                break;
            }
            Command.process(line, tasklist);
        }
        scanner.close();
        System.out.println("Bye Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
