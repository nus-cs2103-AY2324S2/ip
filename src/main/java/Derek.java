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

    private static void print(String msg) {
        System.out.println(INDENT + LINE);
        System.out.println(INDENT + msg);
        System.out.println(INDENT + LINE + "\n");
    }

    public static void main(String[] args) {

        TaskList taskList = new TaskList();

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

            String[] userArgs = userPrompt.split("\\s+");
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

            taskList.addTask(new Task(userPrompt));
            print(userPrompt);
        }

        scanner.close();

        print(EXITMESSAGE);

        return;
    }
}
