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
            if (userInput.equalsIgnoreCase("list")){
                System.out.println(" Here are the tasks in your list:");
                for(int i = 0; i < tasks.size(); i++){
                    System.out.println("" + (i+1) + ". " + tasks.get(i).toString());
                }
            } else if (userInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" " + tasks.get(taskNumber));
            } else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(taskNumber).markAsNotDone();
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks.get(taskNumber));
            } else if (userInput.startsWith("todo")) {
                Todo newTask = new Todo(userInput.substring(5));
                tasks.add(newTask);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.substring(9).split("/by");
                Deadline newTask = new Deadline(parts[0].trim(), parts[1].trim());
                tasks.add(newTask);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.substring(6).split("/from|/to");
                Event newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                tasks.add(newTask);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + newTask);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else {
                Task newTask= new Task(userInput);
                tasks.add(newTask);
                System.out.println("Added: " + userInput);
            }

            if (userInput.equalsIgnoreCase("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
        }
        userScanner.close();
    }
}
