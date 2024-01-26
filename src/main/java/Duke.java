import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();

        System.out.println("Hello! I'm Duke101\n"
                + "What can I do for you?");

        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            if (userInput.equals("list")) {
                displayList(taskList);
            } else {
                processCommand(userInput, taskList);
            }
        }
        scanner.close();
    }
    private static void displayList(List<Task> taskList) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");

        if (taskList.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1 + task.getStatusIcon() + "." + task.description));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void processCommand(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        String command= parts[0];
        if(command.equals("mark")) {
            int taskIndex = Integer.valueOf(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsDone();
                System.out.println("Nice, I've marked this task as done for you:");
                System.out.println("[" + (taskList.get(taskIndex).getStatusIcon() + "]" + " " + taskList.get(taskIndex).description));
            }
        } else if (command.equals("unmark")) {
            int taskIndex = Integer.valueOf(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsUndone();
                System.out.println("Okay, I've marked this task as not done for you:");
                System.out.println("[" + (taskList.get(taskIndex).getStatusIcon() + "]" + " " + taskList.get(taskIndex).description));
            }
        } else {
            Task newTask = new Task(userInput);
            taskList.add(newTask);
            System.out.println("added: " + newTask.description);
        }
    }
}
