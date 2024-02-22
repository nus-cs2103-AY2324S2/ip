import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    public static void main(String[] args) {
        File file = new File("data/duke.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        // Load existing tasks from file
        loadTasksFromFile("data/duke.txt", tasks);

        System.out.println("Hello! I'm Bentley\n" + "What can I do for you?\n");

        while (true) {
            try {
                String userInput = scanner.nextLine();

                if (userInput.equals("Bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("List")) {

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                } else if (userInput.startsWith("todo")) {
                    if (userInput.length() <= 5) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    String description = userInput.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new IllegalArgumentException("looks like the description is missing");
                    }
                    tasks.add(new Todo(description));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.startsWith("deadline")) {
                    if (userInput.length() <= 9) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    String[] parts = userInput.substring(9).split("/by");
                    String description = parts[0].trim();
                    String by = parts[1].trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    tasks.add(new Deadline(description, by)); 
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.startsWith("event")) {
                    if (userInput.length() <= 6) {
                        throw new IllegalArgumentException("looks like something is missing (description/ Deadline)");
                    }
                    String[] parts = userInput.substring(6).split("/from");
                    String description = parts[0].trim();
                    String[] eventParts = parts[1].trim().split("/to");
                    String from = eventParts[0].trim();
                    String to = eventParts[1].trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        throw new IllegalArgumentException(
                                "looks like something is missing (description/ start date/ end date)");
                    }
                    tasks.add(new Event(description, from, to));
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (userInput.startsWith("mark")) {
                    System.out.println(" Nice! I've marked this task as done:");
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    tasks.get(taskNumber - 1).markAsDone();

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                } else if (userInput.startsWith("unmark")) {
                    System.out.println(" OK, I've marked this task as not done yet:");
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    tasks.get(taskNumber - 1).markAsUndone();

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }

                } else if (userInput.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    if (taskNumber > 0 && taskNumber <= tasks.size()) {
                        Task removedTask = tasks.remove(taskNumber - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("Invalid task number. Please provide a valid task number.");
                    }

                } else {
                    System.out.println("please input a valid task code");
                }
                writeTasksToFile("data/duke.txt", tasks);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        try {
            FileWriter writer = new FileWriter("data/duke.txt");
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
            System.out.println("Tasks written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    // Method to load tasks from file
    private static void loadTasksFromFile(String fileName, ArrayList<Task> tasks) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String taskDescription = scanner.nextLine();
                tasks.add(new Task(taskDescription));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    // Method to write tasks to file
    private static void writeTasksToFile(String fileName, ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
            System.out.println("Tasks written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
