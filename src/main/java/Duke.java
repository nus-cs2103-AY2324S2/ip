import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("____________________");
        System.out.println("Hello! I'm Waffles");
        System.out.println("What can I do for you?");
        System.out.println("____________________");

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
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________");
                    break;
                }
            }

        }

        sc.close();

    }

    private static void listTasks() {
        System.out.println("____________________");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String output = String.format("%d. %s", i + 1, task);
            System.out.println(output);
        }
        System.out.println("____________________");
    }

    public static void markTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsDone();
        System.out.println("Nice!, I've marked this task as done:");
        System.out.println(t);
    }

    public static void unmarkTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
    }

    public static void addToDoTask(String taskToAdd) {
        Task toDo = new ToDo(taskToAdd);
        taskList.add(toDo);

        System.out.println("____________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(toDo);
        String output = String.format("Now you have %d tasks in the list.", taskList.size());
        System.out.println(output);
    }

    public static void addDeadline(String deadlineToAdd) {
        String[] deadlineArgs = deadlineToAdd.split(" /by ");
        Task deadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
        taskList.add(deadline);
        System.out.println("____________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        String output = String.format("Now you have %d tasks in the list.", taskList.size());
        System.out.println(output);
    }

    public static void addEvent(String eventToAdd) {
        String[] eventArgs = eventToAdd.split(" /from ");
        String[] eventTime = eventArgs[1].split(" /to ");
        String startTime = eventTime[0];
        String endTime = eventTime[1];
        Task event = new Event(eventArgs[0], startTime, endTime);
        taskList.add(event);
        System.out.println("____________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        String output = String.format("Now you have %d tasks in the list.", taskList.size());
        System.out.println(output);
    }

}
