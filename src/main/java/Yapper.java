import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Yapper {
    public static void main(String[] args) {
        String chatbotName = "Yapper";
        List<Task> tasks = new ArrayList<>();
        Scanner userScanner = new Scanner(System.in);
        System.out.println(" Hello! I'm " + chatbotName + ". I talk a lot, hence my name.");
        System.out.println(" What can I do for you?");

        while (true) {
            System.out.println("User: ");
            String userInput = userScanner.nextLine();
            try {
                processUserInput(userInput, tasks);
            } catch (YapperException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void processUserInput(String userInput, List<Task> tasks) throws YapperException {
        if (userInput.equalsIgnoreCase("list")) {
            System.out.println(" Here are the tasks in your list:");
            printTaskList(tasks);
        }
         else if (userInput.startsWith("mark")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" " + tasks.get(taskNumber));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to mark as done.");
            }
        } else if (userInput.startsWith("unmark")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks.get(taskNumber));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to mark as not done.");
            }
        } else if (userInput.startsWith("todo")) {
            if (userInput.length() <= 5) {
                throw new YapperException("The description of a todo cannot be empty!");
            }
            Todo newTask = new Todo(userInput.substring(5));
            tasks.add(newTask);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + newTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        } else if (userInput.startsWith("deadline")) {
            try {
                String[] parts = userInput.substring(9).split("/by");
                Deadline newTask = new Deadline(parts[0].trim(), parts[1].trim());
                tasks.add(newTask);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new YapperException("Please provide a valid deadline task format.");
            }
        } else if (userInput.startsWith("event")) {
            try {
                String[] parts = userInput.substring(6).split("/from|/to");
                Event newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                tasks.add(newTask);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new YapperException("Please provide a valid event task format.");
            }
        } else if (userInput.startsWith("delete")) {
            try {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task removedTask = tasks.remove(taskNumber);
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removedTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new YapperException("Please provide a valid task number to delete.");
            }
        } else if (userInput.equalsIgnoreCase("bye")){
            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            }
        } else {
            throw new YapperException("Sorry but I don't know what you're yapping about :(");
        }
    }
     private static void printTaskList(List<Task> tasks) {
         for (int i = 0; i < tasks.size(); i++) {
             System.out.println((i + 1) + "." + tasks.get(i));
         }
     }
}


