import java.util.Scanner;
import java.util.ArrayList;

public class Derek {
    public static final String INDENT = "     ";
    public static final String LINE =  "____________________________________________________________";
    private static final String WELCOMEMESSAGE = String.join(
        "\n",
        INDENT + LINE,
        INDENT + "Hello! I'm DEREK",
        INDENT + "What can I do for you?",
        INDENT + LINE,
        ""
    );
    private static final String EXITMESSAGE = String.join(
        "\n",
        INDENT + LINE,
        INDENT + "Bye. Hope to see you again soon!",
        INDENT + LINE,
        ""
    );

    private static final String EXITCOMMAND = "bye";

    public static void main(String[] args) {

        TaskList taskList = new TaskList();

        System.out.println(WELCOMEMESSAGE);

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
                System.out.println(String.join(
                    "\n",
                    INDENT + LINE,
                    INDENT + response,
                    INDENT + LINE,
                    ""
                ));
                continue;
            }

            if ("unmark".equalsIgnoreCase(command)) {
                int idx = Integer.parseInt(userArgs[1]) - 1;
                String response = taskList.markTaskUndone(idx);
                System.out.println(String.join(
                    "\n",
                    INDENT + LINE,
                    INDENT + response,
                    INDENT + LINE,
                    ""
                ));
                continue;
            }

            taskList.addTask(new Task(userPrompt));

            System.out.println(String.join(
                "\n",
                INDENT + LINE,
                INDENT + "added: " + userPrompt,
                INDENT + LINE,
                ""
            ));
        }

        scanner.close();

        System.out.println(EXITMESSAGE);
    }
}
