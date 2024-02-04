import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Asher {
    private static List<Task> tasks = new ArrayList<>();

    public Asher() {
        Asher.tasks = new ArrayList<>();
    }

    private static void getFileContents(String filePath, List<Task> tasks) throws FileNotFoundException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (!created) {
                    throw new IOException("Failed to create file: " + filePath);
                }
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = createTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            } scanner.close();
        } catch (IOException e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    // from the data we have, break it down to get the task listed
    private static Task createTask(String list) {
        Task task;
        String[] splitParts = list.split(" \\| ");

        // invalid format
        if (splitParts.length < 3) {
            return null;
        }

        String type = splitParts[0];
        boolean isCompleted = splitParts[1].equals("1");
        String description = splitParts[2];

        if (type.equals("T")) {
            Todo todo = new Todo(description);
            if (isCompleted) {
                todo.markDone();
            } task = todo;
        } else if (type.equals("D") && splitParts.length == 4) {
            String dueDate = splitParts[3];
            Deadline deadline = new Deadline(description, dueDate);
            if (isCompleted) {
                deadline.markDone();
            } task = deadline;
        } else if (type.equals("E") && splitParts.length == 5) {
            String start = splitParts[3];
            String end = splitParts[4];
            Event event = new Event(description, start, end);
            if (isCompleted) {
                event.markDone();
            } task = event;
        } else {
            task = null;
        }
        return task;
    }

    private static void writeToFile(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("./taskLists.txt");
            for (Task task : tasks) {
                if (task == null) {
                    break;
                }
                String taskString = task.writeToString();
                fw.write(taskString + '\n');
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Cannot write into the file" + e.getMessage());
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm Asher. What can I do for you?");
    }

    private static void exit() {
        writeToFile(tasks);
        System.out.println("Bye. Hope to see you again soon!");

    }

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void addTask(Task task) throws BotException {
        if (tasks.size() < 100) {
            tasks.add(task);
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new BotException("Task List is full, unable to add more.");
        }
    }

    private static Todo createtoDo(String command) throws BotException {
        if (command.length() >= 5) {
            String description = command.substring(5).trim();
            if (!description.isEmpty()) {
                return new Todo(description);
            } else {
                throw new BotException("Todo cannot be empty.");
            }
        } else {
            throw new BotException("Todo Command is invalid!");
        }
    }

    private static Deadline createDeadline(String command) throws BotException {
        int split = command.indexOf("/by");
        if (split == -1) {
            throw new BotException("Due date not found!");
        }
        if (split + 4 >= command.length()) {
            throw new BotException("No such deadline!");
        }

        String description = command.substring(9, split).trim();
        String deadline = command.substring(split + 4).trim();

        if (description.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description and deadline cannot be empty!");
        }
        return new Deadline(description, deadline);
    }

    private static Event createEvent(String command) throws BotException {
        int split1 = command.indexOf("/from");
        int split2 = command.indexOf("/to");
        if (split1 == -1 || split2 == -1) {
            throw new BotException("Start and end time not found!");
        }

        if (split2 + 4 >= command.length()) {
            throw new BotException("End time not found!");
        }

        String description = command.substring(6, split1).trim();
        String startDate = command.substring(split1 + 6, split2).trim();
        String deadline = command.substring(split2 + 4).trim();

        if (description.isEmpty() || startDate.isEmpty() || deadline.isEmpty()) {
            throw new BotException("Description, start time and end time not found!");
        }

        return new Event(description, startDate, deadline);
    }

    private static int getTaskNumber(String task) {
        String[] word = task.split(" ");
        if (word.length == 2) {
            int taskId = Integer.parseInt(word[1]);
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getId() == taskId) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static void markTaskDone(String task) throws BotException {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks.get(taskNumber).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(" "  + tasks.get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    private static void markTaskUndone(String task) throws BotException {
        int taskNumber = getTaskNumber(task);
        if (taskNumber != -1) {
            tasks.get(taskNumber).markUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(taskNumber));
        } else {
            throw new BotException("Invalid Task!");
        }
    }

    private static int getTaskIndexById(int taskId) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId() == taskId) {
                return i;
            }
        }
        return -1;
    }

    private static void updateTaskIds() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).setId(i + 1);
        }
    }

    private static void deleteTask(int taskId) throws BotException{
        int taskIndex = getTaskIndexById(taskId);
        if (taskIndex != -1) {
            Task removedTask = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + removedTask);
            updateTaskIds();
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new BotException("Task not found!");
        }
    }

    public static void processCommand(String command) throws FileNotFoundException, BotException {
        String[] word = command.split(" ");
        String inputType = word[0];
        switch (inputType) {
            case "bye":
                exit();
                break;
            case "list":
                displayTasks();
                break;
            case "mark":
                markTaskDone(command);
                break;
            case "unmark":
                markTaskUndone(command);
                break;
            case "todo":
                addTask(createtoDo(command));
                break;
            case "deadline":
                addTask(createDeadline(command));
                break;
            case "event":
                addTask(createEvent(command));
                break;
            case "delete":
                deleteTask(Integer.parseInt(word[1]));
                break;
            default:
                throw new BotException("Invalid Command!");
        }
    }

    public static void main(String[] args) {
        String dataFile = "./taskLists.txt";

        try {
            Asher.getFileContents(dataFile, Asher.tasks);
        } catch (FileNotFoundException e) {
            System.out.println("File invalid!");
        }

        Asher.greet();

        try (Scanner scanner = new Scanner(System.in)) {
            String command;
            do {
                command = scanner.nextLine();
                Asher.processCommand(command);
            } while (!command.equals("bye"));
        } catch (BotException | FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}