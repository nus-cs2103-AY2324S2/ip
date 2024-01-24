import java.util.Scanner;
import java.util.ArrayList;

public class Derek {
    public static final String INDENT = "     ";

    public static final String LINE =  "____________________________________________________________";

    private static final String WELCOMEMESSAGE = String.join(
        "\n",
        "Hello! I'm DEREK",
        INDENT + "What can I do for you?"
    );

    private static final String EXITMESSAGE = "Bye. Hope to see you again soon!";

    private static final String EXITCOMMAND = "bye";

    private static TaskList taskList;

    private static void print(String msg) {
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + msg);
        System.out.println(INDENT + LINE + "\n");
    }

    private static void printTaskAdded(String msg) {
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + "Added Task:");
        System.out.println(INDENT + "  " + msg);
        System.out.println(INDENT + "Now you have " + taskList.getNumberTasks() + " tasks in the list.");
        System.out.println(INDENT + LINE + "\n");
}

public static void main(String[] args) {

        taskList = new TaskList();

        print(WELCOMEMESSAGE);

        // User input loop
        Scanner scanner = new Scanner(System.in);

        String userPrompt = "";
        while(true) {
            userPrompt = scanner.nextLine();

            // use enums later
            if (EXITCOMMAND.equalsIgnoreCase(userPrompt)) {
                break;
            }

            String[] userArgs = userPrompt.split("\\s+", 2);
            String command = userArgs[0];

            if ("list".equalsIgnoreCase(command)) {
                taskList.printTasks();
                continue;
            }

            if ("mark".equalsIgnoreCase(command)) {
                int idx = Integer.parseInt(userArgs[1]) - 1;
                String response = taskList.markTaskDone(idx);
                print(response);
                continue;
            }

            if ("unmark".equalsIgnoreCase(command)) {
                int idx = Integer.parseInt(userArgs[1]) - 1;
                String response = taskList.markTaskUndone(idx);
                print(response);
                continue;
            }

            if ("todo".equalsIgnoreCase(command)) {
                // handle error here
                String description = userArgs[1];
                String response = taskList.addTask(new ToDo(description));
                printTaskAdded(response);
                continue;
            }

            if ("deadline".equalsIgnoreCase(command)) {
                // handle error here
                String[] deadlineDetails = userArgs[1].split(" /by ", 2);
                String description = deadlineDetails[0];
                String due = deadlineDetails[1];
                String response = taskList.addTask(new Deadline(description, due));
                printTaskAdded(response);
                continue;
            }

            if ("event".equalsIgnoreCase(command)) {
                // handle error here
                String[] eventDetails = userArgs[1].split(" /from ", 2);
                String description = eventDetails[0];
                String[] timings = eventDetails[1].split(" /to ", 2);
                String start = timings[0];
                String end = timings[1];
                String response = taskList.addTask(new Event(description, start, end));
                printTaskAdded(response);
                continue;
            }

            print("invalid prompt: " + userPrompt);
        }

        scanner.close();

        print(EXITMESSAGE);

        return;
    }
}
