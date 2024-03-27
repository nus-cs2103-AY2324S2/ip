//import SamuelBot.Deadline;
//import SamuelBot.Task;
//import SamuelBot.Todo;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Duke_Level5 {
//    private List<Task> taskList;
//
//    public Duke_Level5() {
//        this.taskList = new ArrayList<>();
//    }
//    public void greet(){
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//        System.out.println("What can I do for you?");
//    }
//    public void run() {
//        this.greet();
//        Scanner scanner = new Scanner(System.in);
//        String input;
//        do {
//            input = scanner.nextLine().trim();
//            processInput(input);
//        } while (!input.equals("bye"));
//
//        System.out.println("Bye. Hope to see you again soon!");
//    }
//
//    private void processInput(String input) {
//        String[] tokens = input.split("\\s+", 2);
//
//        switch (tokens[0]) {
//            case "bye":
//                break;
//            case "list":
//                listTasks();
//                break;
//            case "done":
//                try {
//                    int index = Integer.parseInt(tokens[1]);
//                    markTaskAsDone(index);
//                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//                    System.out.println("Invalid command format.");
//                }
//                break;
//            case "undone":
//                try {
//                    int index = Integer.parseInt(tokens[1]);
//                    markTaskAsUndone(index);
//                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
//                    System.out.println("Invalid command format.");
//                }
//                break;
//            case "todo":
//                if (tokens.length == 1 || tokens[1].isEmpty()) {
//                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
//                } else {
//                    try {
//                        String description = tokens[1].trim();
//                        addTodoTask(description);
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("Please provide a description for the task.");
//                    }
//                }
//                break;
//            case "deadline":
//                if (tokens.length == 1 || tokens[1].isEmpty()) {
//                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
//                }
//                else{
//                    try {
//                        String[] parts = tokens[1].split("/by", 2);
//                        String description = parts[0].trim();
//                        String by = parts[1].trim();
//                        addDeadlineTask(description, by);
//                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
//                        System.out.println("Please provide a description and deadline for the task.");
//                    }
//                }
//                break;
//            case "event":
//                if (tokens.length == 1 || tokens[1].isEmpty()) {
//                    System.out.println("OOPS!!! The description of a event cannot be empty.");
//                }
//                else {
//                    try {
//                        String[] parts = tokens[1].split("/from", 2);
//                        String description = parts[0].trim();
//                        String[] fromTo = parts[1].split("/to", 2);
//                        String from = fromTo[0].trim();
//                        String to = fromTo[1].trim();
//                        addEventTask(description, from, to);
//                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
//                        System.out.println("Please provide a description, start time, and end time for the event.");
//                    }
//                }
//                break;
//            default:
//                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                break;
//        }
//    }
//
//
//    private void markTask(int index) {
//        try {
//            Task task = taskList.get(index - 1);
//            task.markAsDone();
//            System.out.println("Nice! I've marked this task as done:");
//            System.out.println(task);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Invalid task number.");
//        }
//    }
//
//    private void unmarkTask(int index) {
//        try {
//            Task task = taskList.get(index - 1);
//            task.markAsUndone();
//            System.out.println("OK, I've marked this task as not done yet:");
//            System.out.println(task);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Invalid task number.");
//        }
//    }
//
//
//    private void listTasks() {
//        if (taskList.isEmpty()) {
//            System.out.println("You have no tasks in your list.");
//        } else {
//            System.out.println("Here are the tasks in your list:");
//            for (int i = 0; i < taskList.size(); i++) {
//                System.out.println((i + 1) + "." + taskList.get(i));
//            }
//        }
//    }
//
//    private void markTaskAsDone(int index) {
//        try {
//            Task task = taskList.get(index - 1);
//            task.markAsDone();
//            System.out.println("Nice! I've marked this task as done:");
//            System.out.println(task);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Invalid task number.");
//        }
//    }
//
//    private void markTaskAsUndone(int index) {
//        try {
//            Task task = taskList.get(index - 1);
//            task.markAsUndone();
//            System.out.println("OK, I've marked this task as not done yet:");
//            System.out.println(task);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.println("Invalid task number.");
//        }
//    }
//
//    private void addTodoTask(String description) {
//        Task todoTask = new Todo(description);
//        taskList.add(todoTask);
//        System.out.println("Got it. I've added this task:");
//        System.out.println(todoTask);
//        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//    }
//
//    private void addDeadlineTask(String description, String by) {
//        Task deadlineTask = new Deadline(description, by);
//        taskList.add(deadlineTask);
//        System.out.println("Got it. I've added this task:");
//        System.out.println(deadlineTask);
//        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//    }
//
//    private void addEventTask(String description, String from, String to) {
//        Task eventTask = new Event(description, from, to);
//        taskList.add(eventTask);
//        System.out.println("Got it. I've added this task:");
//        System.out.println(eventTask);
//        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
//    }
//
//    public static void main(String[] args) {
//        Duke_Level5 duke = new Duke_Level5();
//        duke.run();
//    }
//}