import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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

    public static void main(String[] args) throws IOException {
        start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            ArrayList<String> inputArr = new ArrayList<>(Arrays.asList(br.readLine().split(" ")));
            String input = inputArr.remove(0);

            System.out.println(DIVIDER);
            if (input.equalsIgnoreCase("bye")) {
                end();
                System.out.println(DIVIDER);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                taskList.printList();
            } else if (input.equalsIgnoreCase("mark")) {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                Task task = taskList.getTaskByNumber(taskNumber);
                task.markAsDone();
                System.out.println("The following task has been marked.");
                System.out.println("→ " + task);
            } else if (input.equalsIgnoreCase("unmark")) {
                int taskNumber = Integer.parseInt(inputArr.get(0));
                Task task = taskList.getTaskByNumber(taskNumber);
                task.markAsNotDone();
                System.out.println("The following task has not been unmarked.");
                System.out.println("→ " + task);
            } else if (input.equalsIgnoreCase("todo")) {
                String taskName = String.join(" ", inputArr);
                ToDo newTask = new ToDo(taskName);
                taskList.addTask(newTask);
                System.out.println("The following task has been added:");
                System.out.println("→ " + newTask);
                System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");
            } else if (input.equalsIgnoreCase("deadline")) {
                String taskName = String.join(" ", inputArr.subList(0, inputArr.indexOf("/by")));
                String dateTime = String.join(" ", inputArr.subList(inputArr.indexOf("/by") + 1, inputArr.size()));
                Deadline newTask = new Deadline(taskName, dateTime);
                taskList.addTask(newTask);
                System.out.println("The following task has been added:");
                System.out.println("→ " + newTask);
                System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");
            } else if (input.equalsIgnoreCase("event")) {
                String taskName = String.join(" ", inputArr.subList(0, inputArr.indexOf("/from")));
                String startDateTime = String.join(" ", inputArr.subList(inputArr.indexOf("/from") + 1, inputArr.indexOf("/to")));
                String endDateTime = String.join(" ", inputArr.subList(inputArr.indexOf("/to") + 1, inputArr.size()));
                Event newTask = new Event(taskName, startDateTime, endDateTime);
                taskList.addTask(newTask);
                System.out.println("The following task has been added:");
                System.out.println("→ " + newTask);
                System.out.println("You have a total of " + taskList.getTaskCount() + " tasks in the list.");
            }
            System.out.println(DIVIDER);
        }
    }
}
