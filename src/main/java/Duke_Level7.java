import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke_Level7 {
    private static final String FILE_PATH = "./data/duke.txt";
    private List<Task> taskList;

    public Duke_Level7() {
        this.taskList = new ArrayList<>();
        loadTasksFromFile();
    }

    private void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split("\\|");
                    String type = parts[0].trim();
                    boolean isDone = Integer.parseInt(parts[1].trim()) == 1;
                    String description = parts[2].trim();
                    switch (type) {
                        case "T":
                            taskList.add(new Todo(description));
                            break;
                        case "D":
                            String by = parts[3].trim();
                            taskList.add(new Deadline(description, by));
                            break;
                        case "E":
                            String from = parts[3].trim();
                            String to = parts[4].trim();
                            taskList.add(new Event(description, from, to));
                            break;
                        default:
                            System.out.println("Unknown task type: " + type);
                            break;
                    }
                }
                scanner.close();
            } else {
                System.out.println("No existing data file found. Starting with an empty task list.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : taskList) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void run() {
        this.greet();
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine().trim();
            processInput(input);
            saveTasksToFile(); // Call saveTasksToFile() after processing each input
        } while (!input.equals("bye"));

        System.out.println("Bye. Hope to see you again soon!");
    }

    private void processInput(String input) {
        String[] tokens = input.split("\\s+", 2);
        switch (tokens[0]) {
            case "delete":
                try {
                    deleteTask(Integer.parseInt(tokens[1]));
                } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e ){
                    System.out.println("Sorry, you need to input a valid integer index");
                }
                break;
            case "bye":
                break;
            case "list":
                listTasks();
                break;
            case "mark": // Added case for marking task as done
                try {
                    int index = Integer.parseInt(tokens[1]);
                    markTask(index);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid command format.");
                }
                break;
            case "unmark": // Added case for marking task as undone
                try {
                    int index = Integer.parseInt(tokens[1]);
                    unmarkTask(index);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid command format.");
                }
                break;
            case "todo":
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    try {
                        String description = tokens[1].trim();
                        addTodoTask(description);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please provide a description for the task.");
                    }
                }
                break;
            case "deadline":
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }
                else{
                    try {
                        String[] parts = tokens[1].split("/by", 2);
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        addDeadlineTask(description, by);
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Please provide a description and deadline for the task.");
                    }
                }
                break;
            case "event":
                if (tokens.length == 1 || tokens[1].isEmpty()) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                }
                else {
                    try {
                        String[] parts = tokens[1].split("/from", 2);
                        String description = parts[0].trim();
                        String[] fromTo = parts[1].split("/to", 2);
                        String from = fromTo[0].trim();
                        String to = fromTo[1].trim();
                        addEventTask(description, from, to);
                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Please provide a description, start time, and end time for the event.");
                    }
                }
                break;
            default:
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }
    }

    private void markTask(int index) {
        try {
            Task task = taskList.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(int index) {
        try {
            Task task = taskList.get(index - 1);
            task.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        }
    }

    private void listTasks() {
        if (taskList.isEmpty()) {
            System.out.println("You have no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + "." + taskList.get(i));
            }
        }
    }

    private void addTodoTask(String description) {
        Task todoTask = new Todo(description);
        taskList.add(todoTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(todoTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void deleteTask(int index) {
        try {
            Task removedTask = taskList.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");

            // Print the updated task list
            listTasks();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid task number.");
        }
    }

    private void addDeadlineTask(String description, String by) {
        Task deadlineTask = new Deadline(description, by);
        taskList.add(deadlineTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadlineTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    private void addEventTask(String description, String from, String to) {
        Task eventTask = new Event(description, from, to);
        taskList.add(eventTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(eventTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke_Level7 duke = new Duke_Level7();
        duke.run();
    }
}
