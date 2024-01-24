import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    String divider = "--------------------------------------------------";
    ArrayList<Task> taskList = new ArrayList<Task>();

    public boolean parseCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        System.out.println(this.divider);

        if (Objects.equals(command, "bye")) {
            System.out.println("Bye. Hope to see you again soon!");
            return false;
        } else if (Objects.equals(command, "list")) {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + ". " + taskList.get(i).description);
            }
        } else {
            this.taskList.add(new Task(command));
            System.out.println("added: " + command);
        }

        System.out.println(this.divider);
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
