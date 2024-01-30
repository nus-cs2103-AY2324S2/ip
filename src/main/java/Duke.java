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
            try{
                processCommand(userInput, taskList);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
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

    private static void processCommand(String userInput, List<Task> taskList) throws Exception {
        if(userInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty.");
        }
        String[] parts = userInput.split(" ");
        String command = parts[0];

        try{
            if (userInput.equals("list")) {
                displayList(taskList);
            } else if(command.equals("todo") || command.equals("deadline") || command.equals("event")){
                addTask(userInput, taskList);
            } else if(command.equals("mark")) {
                markTask(userInput, taskList);
            } else if (command.equals("unmark")) {
                unmarkTask(userInput, taskList);
            } else if (command.equals("delete")) {
                deleteTask(userInput, taskList);
            } else {
                throw new DukeException("Hey, please choose from the following commands\n" +
                        "if you want to add task, please use todo, deadline or event\n" +
                        "if you want to mark or unmark task, please use mark or unmark\n" +
                        "if you want delete a task, please use delete\n" +
                        "if you want to view the existing task list, please enter list.");
            }
        }catch (DukeException e) {
            System.out.println("------------------------------");
            System.out.println(e.getMessage());
            System.out.println("------------------------------");
        }
    }
    private static void markTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        int taskIndex = Integer.valueOf(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Please check how many tasks are there in your list.");
        }
        taskList.get(taskIndex).markAsDone();
        System.out.println("Nice, I've marked this task as done for you:");
        System.out.println((taskList.get(taskIndex)));
    }

    private static void unmarkTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        int taskIndex = Integer.valueOf(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Please check how many tasks are there in your list.");
        }
        taskList.get(taskIndex).markAsUndone();
        System.out.println("Nice, I've marked this task as undone for you:");
        System.out.println((taskList.get(taskIndex)));
    }
    private static void addTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts[1];

        try{
            if (command.equals("todo")){
                if(parts.length < 2 || parts[1].trim().isEmpty()){
                    throw new IllegalArgumentException("The description of a todo task cannot be empty.");
                }
                ToDo newTask = new ToDo(taskInfo);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + taskList.size() + " task(s) left in the list.");
            } else if (command.equals("deadline")) {
                String[] details = taskInfo.split("/by", 2);
                if (details.length != 2) {
                    throw new IllegalArgumentException("Invalid format for deadline, please include a task description and date/time details.");
                }
                String taskDescription = details[0].trim();
                String deadline = details[1].trim();
                Deadline newTask = new Deadline(taskDescription, deadline);
                taskList.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask);
                System.out.println("Now you have " + taskList.size() + " task(s) left in the list.");
            } else if (command.equals("event")) {
                String[] taskDetails = taskInfo.split("/from", 2);
                if (taskDetails.length != 2) {
                    throw new IllegalArgumentException("Invalid format for event, please include a task description and date/time details.");
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
                System.out.println("Now you have " + taskList.size() + " task(s) left in the list.");
            }
        }catch (Exception e) {
            System.out.println("------------------------------");
            System.out.println("OOPS!!!" + e.getMessage());
            System.out.println("------------------------------");
        }
    }
    private static void deleteTask(String userInput, List<Task> taskList) throws Exception{
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide the task number to delete.");
        }

        int taskIndex = Integer.valueOf(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Invalid task number. Please provide a valid task number.");
        }

        Task removedTask = taskList.remove(taskIndex);
        System.out.println("---------------------------");
        System.out.println("I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + taskList.size() + " task(s) left in the list. ");
        System.out.println("---------------------------");
    }
}
