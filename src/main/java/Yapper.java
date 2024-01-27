import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Yapper {
    private static final String FILE_PATH = "./src/main/java/data/Yapper.txt";
    public static void main(String[] args) {
        String chatbotName = "Yapper";
        List<Task> tasks = loadTasksFromFile();

        Scanner userScanner = new Scanner(System.in);
        System.out.println(" Hello! I'm " + chatbotName + ". I talk a lot, hence my name.");
        System.out.println(" What can I do for you?");

        while (true) {
            System.out.println("User: ");
            String userInput = userScanner.nextLine();
            try {
                processUserInput(userInput, tasks);
                saveTasksToFile(tasks);
            } catch (YapperException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Processes the various user inputs.
     *
     * @param userInput User input in string format.
     * @param tasks List of tasks recorded thus far based on user inputs.
     * @throws YapperException If any of user inputs is invalid.
     */
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
            Todo newTask = new Todo(userInput.substring(5), false);
            tasks.add(newTask);
            System.out.println(" Got it. I've added this task:");
            System.out.println("   " + newTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        } else if (userInput.startsWith("deadline")) {
            try {
                String[] parts = userInput.substring(9).split("/by");
                Deadline newTask = new Deadline(parts[0].trim(), false, parts[1].trim());
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
                Event newTask = new Event(parts[0].trim(), false, parts[1].trim(), parts[2].trim());
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

    /**
     * Prints the list of tasks.
     *
     * @param tasks List of tasks based on user inputs.
     * @return List of tasks.
     * */
     private static void printTaskList(List<Task> tasks) {
         for (int i = 0; i < tasks.size(); i++) {
             System.out.println((i + 1) + "." + tasks.get(i));
         }
     }

    /**
     * Returns list of tasks that has been recorded in the file based on user input.
     *
     * @return List of tasks as recorded in the file.
     */
    private static List<Task> loadTasksFromFile(){
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(TaskParser.parseTask(line));
            }
        } catch (IOException | YapperException e){
            System.out.println("Error loading tasks from file. Creating a new task list.");
            tasks = new ArrayList<>();
        }
        return tasks;
     }

    /**
     * Saves the tasks from user input in the file.
     *
     * @param tasks Task input extracted from user input.
     */

    private static void saveTasksToFile(List<Task> tasks){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))){
            for (Task task : tasks){
                writer.write(task.toFileString());
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Error saving tasks to file :(");
        }
     }
}



