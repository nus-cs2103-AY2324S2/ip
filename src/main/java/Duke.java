import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String MARK_DONE_MESSAGE = "Nice! I've marked this task as done:%n%s";
    private static final String MARK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:%n%s";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:%n%s%nNow you have %d tasks in the list.";
    private static final String LIST_TASK_MESSAGE = "Here are the tasks in your list:%s";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greet();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String userInput = sc.nextLine();
            String[] splitInputs = userInput.split("\\s+");
            String command = splitInputs[0];

            if (splitInputs.length > 1) {
                String argument = userInput.substring(command.length() + 1);
                if (command.equalsIgnoreCase("mark")) {
                    markTask(argument);
                } else if (command.equalsIgnoreCase("unmark")) {
                    unmarkTask(argument);
                } else if (command.equalsIgnoreCase("todo")) {
                    addToDoTask(argument);
                } else if (command.equalsIgnoreCase("deadline")) {
                    addDeadline(argument);
                } else if (command.equalsIgnoreCase("event")) {
                    addEvent(argument);
                }

            } else {
                if (command.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (command.equalsIgnoreCase("bye")) {
                    System.out.println(GOODBYE_MESSAGE);
                    break;
                }
            }

        }

        sc.close();

    }

    private static void listTasks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%n%d.%s", i + 1, task));
        }
        String output = String.format(LIST_TASK_MESSAGE, sb);
        System.out.println(output);
    }

    public static void markTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsDone();
        String output = String.format(MARK_DONE_MESSAGE, t);
        System.out.println(output);
    }

    public static void unmarkTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsUndone();
        String output = String.format(MARK_UNDONE_MESSAGE, t);
        System.out.println(output);

    }

    public static void addToDoTask(String taskToAdd) {
        Task toDo = new ToDo(taskToAdd);
        taskList.add(toDo);
        String output = String.format(ADD_TASK_MESSAGE, toDo, taskList.size());
        System.out.println(output);
    }

    public static void addDeadline(String deadlineToAdd) {
        String[] deadlineArgs = deadlineToAdd.split(" /by ");
        Task deadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        taskList.add(deadline);
        String output = String.format(ADD_TASK_MESSAGE, deadline, taskList.size());
        System.out.println(output);
    }

    public static void addEvent(String eventToAdd) {
        String[] eventArgs = eventToAdd.split(" /from ");
        String[] eventTime = eventArgs[1].split(" /to ");
        String startTime = eventTime[0];
        String endTime = eventTime[1];
        Task event = new Event(eventArgs[0], startTime, endTime);
        taskList.add(event);
        String output = String.format(ADD_TASK_MESSAGE, event, taskList.size());
        System.out.println(output);
    }

    public static void greet() {
        String output = String.format("Hello! I'm Waffles!%nWhat can I do for you?");
        System.out.println(output);
    }

}
