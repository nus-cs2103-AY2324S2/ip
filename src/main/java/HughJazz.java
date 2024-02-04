import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.IOException;
public class HughJazz {
    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    private static String filePath = "." + File.separator + "data" + File.separator + "duke.txt";
    public static void main(String[] args) {
        greeting();
        try {
            loadTasks();
        } catch (FileNotFoundException e){
            System.out.println("No existing txt file found");
        }
        Scanner scn = new Scanner(System.in);
        String userInput;

        while (true) {
            try {
                userInput = scn.nextLine().trim();
                if("bye".equalsIgnoreCase(userInput)) {
                    break;
                } else{
                    handleInput(userInput);
                }
            } catch (ChatbotException e) {
                System.out.println(e.getMessage());
            }
        }
        ending();
        scn.close();
    }

    private static void handleInput(String userInput) throws ChatbotException {
        if (userInput.toLowerCase().startsWith("todo")) {
            addTask(userInput, "todo");
        } else if (userInput.toLowerCase().startsWith("deadline")) {
            addTask(userInput, "deadline");
        } else if (userInput.toLowerCase().startsWith("event")) {
            addTask(userInput, "event");
        } else if ("list".equalsIgnoreCase(userInput)) {
            printTasks();
        } else if (userInput.toLowerCase().startsWith("mark")) {
            markTask(userInput, true);
        } else if (userInput.toLowerCase().startsWith("unmark")) {
            markTask(userInput, false);
        } else if (userInput.toLowerCase().startsWith("delete")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            deleteTask(taskNumber);
        } else {
            throw new ChatbotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void greeting() {
        System.out.println("Hello! I'm HughJazz");
        System.out.println("What can I do for you?");
    }
    public static void ending() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    private static void addTask(String userInput, String taskType) throws ChatbotException {
        String taskDetails = userInput.substring(taskType.length()).trim();
        if (taskDetails.isEmpty()) {
            throw new ChatbotException("OOPS!!! The description of a " + taskType + " cannot be empty.");
        }
        if (taskCount < MAX_TASKS) {
            Task newTask;
            if (taskType.equals("todo")) {
                newTask = new Todo(taskDetails, false);
            } else if (taskType.equals("deadline")) {
                String[] details = taskDetails.split(" /by ", 2);
                newTask = new Deadline(details[0], false, details[1]);
            } else if (taskType.equals("event")) {
                String[] details = taskDetails.split(" /from ", 2);
                String[] times = details[1].split(" /to ", 2);
                newTask = new Event(details[0], false, times[0], times[1]);
            } else {
                // Handle invalid task type
                System.out.println("Invalid task type");
                return;
            }
            tasks.add(newTask);
            taskCount++;
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + newTask);
            System.out.println("Now you have " + taskCount + " tasks in the list.");
            try {
                saveTasks();
            } catch(IOException e) {
                System.out.print(e);
            }
        }
    }

    private static void deleteTask(int taskNumber) throws ChatbotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new ChatbotException("Invalid task number");
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        try {
            saveTasks();
        } catch(IOException e) {
            System.out.print(e);
        }
        taskCount--;
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + removedTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void markTask(String userInput, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
            if (taskNumber >= 0 && taskNumber < taskCount) {
                if (isDone) {
                    tasks.get(taskNumber).markAsDone();
                    try {
                        saveTasks();
                    } catch(IOException e) {
                        System.out.print(e);
                    }
                    System.out.println("Nice! I've marked this task as done: ");
                }
                else {
                    tasks.get(taskNumber).unmarkAsDone();
                    try {
                        saveTasks();
                    } catch(IOException e) {
                        System.out.print(e);
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                }
                System.out.println("  " + tasks.get(taskNumber).toString());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number");
        }
    }
    private static void printTasks() {
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }

    private static void saveTasks() throws IOException {
        new File("." + File.separator + "data").mkdirs(); // Ensure directory exists
        PrintWriter writer = new PrintWriter(new File(filePath));
        for (Task task : tasks) {
            writer.println(task.toFileFormat());
        }
        writer.close();
    }

    private static void loadTasks() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            taskCount += 1;
            String line = scanner.nextLine();
            decodeTask(line);
        }
        scanner.close();
    }

    private static void decodeTask(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
            case "T":
                tasks.add(new Todo(parts[2], Boolean.parseBoolean(parts[1])));
                break;
            case "D":
                tasks.add(new Deadline(parts[2], Boolean.parseBoolean(parts[1]), parts[3]));
                break;
            case "E":
                String[] time = parts[3].split("-");
                String from = time[0];
                String to  = time[1];
                tasks.add(new Event(parts[2], Boolean.parseBoolean(parts[1]), from, to));
                break;
        }
    }
}

