import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    public static void main(String[] args) {
        List<Task> taskList = loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);

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
                saveTasksToFile(taskList);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static List<Task> loadTasksFromFile() {
        List<Task> taskList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;

            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromLine(line);
                if (task != null) {
                    taskList.add(task);
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found. Starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("Error reading data file. Starting with an empty task list.");
        }

        return taskList;
    }
    private static void saveTasksToFile(List<Task> taskList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

            for (Task task : taskList) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    private static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            System.out.println("Skipped invalid line in data file: " + line);
            return null;
        }

        String taskType = parts[0];
        int isDone = Integer.parseInt(parts[1]);
        String description = parts[2];

        Task task;

        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "D":
                String by = parts.length > 3 ? parts[3] : "";
                task = new Deadline(description, by);
                break;
            case "E":
                String fromTo = parts.length > 3 ? parts[3] : "";
                String[] fromToArray = fromTo.split(" to ");
                String from = fromToArray.length > 0 ? fromToArray[0] : "";
                String to = fromToArray.length > 1 ? fromToArray[1] : "";
                task = new Event(description, from, to);
                break;
            default:
                System.out.println("Skipped unknown task type in data file: " + taskType);
                return null;
        }

        task.isDone = (isDone == 1);
        return task;
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
        if (userInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty.");
        }
        String[] parts = userInput.split(" ");
        String commandStr = parts[0];

        CommandType command;
        try {
            command = CommandType.valueOf(commandStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("I'm sorry, but I don't understand what that means.");
        }

        switch (command) {
            case LIST:
                displayList(taskList);
                break;
            case TODO:
            case DEADLINE:
            case EVENT:
                addTask(userInput, taskList);
                break;
            case MARK:
                markTask(userInput, taskList);
                break;
            case UNMARK:
                unmarkTask(userInput, taskList);
                break;
            case DELETE:
                deleteTask(userInput, taskList);
                break;
            default:
                throw new IllegalArgumentException("Hey, please choose from the following commands\n" +
                        "if you want to add task, please use todo, deadline or event\n" +
                        "if you want to mark or unmark task, please use mark or unmark\n" +
                        "if you want delete a task, please use delete\n" +
                        "if you want to view the existing task list, please enter list.");
        }
    }

    private static void markTask(String userInput, List<Task> taskList) {
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide a task number.");
        }
        int taskIndex = Integer.parseInt(parts[1]) - 1;
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
        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Please check how many tasks are there in your list.");
        }
        taskList.get(taskIndex).markAsUndone();
        System.out.println("Nice, I've marked this task as undone for you:");
        System.out.println((taskList.get(taskIndex)));
    }
    private static void addTask(String userInput, List<Task> taskList) throws Exception {
        String[] parts = userInput.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("The description of a task cannot be empty.");
        }
        String command = parts[0];
        String taskInfo = parts[1];

        Task newTask;

        switch (command) {
            case "todo":
                newTask = new ToDo(taskInfo);
                break;
            case "deadline":
                String[] details = taskInfo.split("/by", 2);
                if (details.length < 2) {
                    throw new IllegalArgumentException("Invalid format for deadline, please provide a deadline using /by.");
                }
                String deadlineDescription = details[0].trim();
                String deadline = details[1].trim();
                newTask = new Deadline(deadlineDescription, deadline);
                break;
            case "event":
                String[] taskDetails = taskInfo.split("/from", 2);
                if (taskDetails.length < 2) {
                    throw new IllegalArgumentException("Invalid format for event, please provide event details by using /from and /to.");
                }
                String eventDescription = taskDetails[0].trim();
                String dateTimeDetails = taskDetails[1].trim();
                String[] dateTimeSplit = dateTimeDetails.split("/to", 2);
                if (dateTimeSplit.length < 2) {
                    throw new IllegalArgumentException("Invalid format for event, please provide event details by using /from and /to.");
                }
                String from = dateTimeSplit[0].trim();
                String until = dateTimeSplit[1].trim();
                newTask = new Event(eventDescription, from, until);
                break;
            default:
                throw new IllegalArgumentException("I'm sorry, but I don't understand what you intend to do.");
        }

        taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    private static void deleteTask(String userInput, List<Task> taskList) throws Exception{
        String[] parts = userInput.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new IllegalArgumentException("Please provide the task number to delete.");
        }

        int taskIndex = Integer.parseInt(parts[1]) - 1;
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            throw new IndexOutOfBoundsException("Invalid task number. Please check how many tasks your have in the list.");
        }

        Task removedTask = taskList.remove(taskIndex);
        System.out.println("---------------------------");
        System.out.println("I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + taskList.size() + " task(s) left in the list. ");
        System.out.println("---------------------------");
    }
}
