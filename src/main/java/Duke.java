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

            if (splitInputs.length > 1) {
                String command = splitInputs[0];
                if (command.equalsIgnoreCase("mark")) {
                    String idx = splitInputs[1];
                    executeMarkTask(idx);
                } else if (command.equalsIgnoreCase("unmark")) {
                    String idx = splitInputs[1];
                    executeUnmarkTask(idx);
                } else {
                    echoAndAddTask(userInput);
                }

            } else {
                String command = splitInputs[0];
                if (command.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (command.equalsIgnoreCase("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________");
                    break;
                } else {
                    echoAndAddTask(userInput);
                }
            }

        }

        sc.close();

    }

    private static void echoAndAddTask(String taskDescription) {
        Task newTask = new Task(taskDescription);
        taskList.add(newTask);
        String output = String.format("added: %s", taskDescription);
        System.out.println("____________________");
        System.out.println(output);
        System.out.println("____________________");

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

    public static String executeMarkTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsDone();
        System.out.println("Nice!, I've marked this task as done:");
        System.out.println(t);
        return t.toString();
    }

    public static String executeUnmarkTask(String taskIndex) {
        Task t = taskList.get(Integer.parseInt(taskIndex) - 1);
        t.markAsUndone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
        return t.toString();
    }

}
