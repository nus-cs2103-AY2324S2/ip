import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Duke {
    public static final String DIVIDER = "────────────────────────────────────────────────────────────";
    public static final String GREETING = "Hello! I'm Seiki\nHow may I assist you today?";
    public static final String FAREWELL = "Goodbye! If you ever need assistance in the future, don't hesitate to reach out. Take care!";
    public static final String LOGO = "  _____      _  _     _\n"
                                    + " / ____|    (_)| | _ (_)\n"
                                    + "| (___  ___  _ | |/ / _\n"
                                    + " \\___ \\/ _ \\| ||   / | |\n"
                                    + " ____) | __/| || | \\ | |\n"
                                    + "|_____/\\___||_||_|\\_\\|_|\n";
    public static TaskList taskList = new TaskList();

    public static void start() {
        System.out.println(DIVIDER);
        System.out.println(LOGO);
        System.out.println(GREETING);
        System.out.println(DIVIDER);
    }

    public static void end() {
        System.out.println(FAREWELL);
    }

    public static String getTaskName(StringTokenizer st) {
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            sb.append(" ").append(st.nextToken());
        }
        return sb.toString();
    }
    public static void main(String[] args) throws IOException {
        start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String input = st.nextToken();
            System.out.println(DIVIDER);
            if (input.equalsIgnoreCase("bye")) {
                end();
                System.out.println(DIVIDER);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                taskList.printList();
            } else if (input.equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(st.nextToken());
                Task task = taskList.getTaskByNumber(taskNumber);
                task.markAsDone();
                System.out.println("The following task has been marked.");
                System.out.println("→ " + task);
            } else if (input.equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(st.nextToken());
                Task task = taskList.getTaskByNumber(taskNumber);
                task.markAsNotDone();
                System.out.println("The following task has not been unmarked.");
                System.out.println("→ " + task);
            } else {
                String taskName = input + getTaskName(st);
                Task newTask = new Task(taskName);
                taskList.addTask(newTask);
                System.out.println("The following task has been added:");
                System.out.println("→ " + taskName);
            }
            System.out.println(DIVIDER);
        }
    }
}
