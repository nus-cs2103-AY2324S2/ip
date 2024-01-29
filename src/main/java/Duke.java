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
            processCommand(userInput, taskList);
        }
        scanner.close();
    }
    private static void displayList(List<Task> taskList) {
        System.out.println("____________________________________________________________");

        if (taskList.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println(((i + 1) + "." + task));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void processCommand(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        String command = parts[0];

        if (userInput.equals("list")) {
            displayList(taskList);
        } else if(command.equals("todo") || command.equals("deadline") || command.equals("event")){
            addTask(userInput,taskList);
        } else if(command.equals("mark")) {
            int taskIndex = Integer.valueOf(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsDone();
                System.out.println("Nice, I've marked this task as done for you:");
                System.out.println((taskList.get(taskIndex)));
            }
        } else if (command.equals("unmark")) {
            int taskIndex = Integer.valueOf(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < taskList.size()) {
                taskList.get(taskIndex).markAsUndone();
                System.out.println("Okay, I've marked this task as not done for you:");
                System.out.println((taskList.get(taskIndex)));
            }
        } else {
            System.out.println("Invalid command. Please use 'list', 'todo', 'deadline', 'event', or 'bye'.");
        }
    }

    private static void addTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts[1];

        if (command.equals("todo")){
            ToDo newTask = new ToDo(taskInfo);
            taskList.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + taskList.size() + "tasks left in the list.");
        } else if (command.equals("deadline")) {
            String[] details = taskInfo.split("/by", 2);
            if (details.length != 2) {
                System.out.println("Invalid format for deadline, please included a task description and date/time details.");
            }
            String taskDescription = details[0].trim();
            String deadline = details[1].trim();
            Deadline newTask = new Deadline(taskDescription, deadline);
            taskList.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + taskList.size() + " tasks left in the list.");
        } else if (command.equals("event")) {
            String[] taskDetails = taskInfo.split("/from", 2);
            if (taskDetails.length != 2) {
                System.out.println("Invalid format for deadline, please included a task description and date/time details.");
            }
            String taskDescription = taskDetails[0].trim();
            String dateTimeDetails = taskDetails[1].trim();
            String[] dateTimeSplit =  dateTimeDetails.split("/to", 2);
            String from = dateTimeSplit[0].trim();
            String until = dateTimeSplit[1].trim();
            Event newTask = new Event(taskDescription, from, until);
            taskList.add(newTask);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + taskList.size() + " tasks left in the list.");
        }
    }
}
