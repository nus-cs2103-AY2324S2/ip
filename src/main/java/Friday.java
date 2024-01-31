import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;

public class Friday {
    private static final String DATA_FILE_PATH = "./src/main/java/data/Friday.txt";
    private static final String horizontalLine = "_".repeat(60);
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File f = new File(DATA_FILE_PATH);

        try {
            if (!f.exists()) {
                if (f.createNewFile()) {
                    System.out.println("Data file created.");
                } else {
                    System.out.println("Error creating data file.");
                }
            } else {
                System.out.println("Loading data from Duke.txt...");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        List<Task> tasks = new ArrayList<>();
        try {
            tasks = loadDataFromFile(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        int counter = tasks.size();
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Friday");
        System.out.println("What can I do for you?");
        System.out.println(horizontalLine);

        String userInput = sc.nextLine().trim();
        String category = userInput.split(" ")[0];
        while (!userInput.equals("bye")) {
            System.out.println(horizontalLine);
            switch (category) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < counter; i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                    break;
                case "mark":
                    String[] toMark = userInput.split(" ");
                    if (toMark.length <= 1) {
                        System.out.println("Error. Unknown number.");
                        break;
                    }
                    int id = Integer.parseInt(userInput.split(" ")[1]);
                    if (id > counter) {
                        System.out.println("Error. Task does not exist.");
                        break;
                    }
                    System.out.println("Nice! I've marked this task as done:");
                    tasks.get(id - 1).markAsDone();
                    System.out.println(tasks.get(id - 1).toString());
                    try {
                        writeToFile(DATA_FILE_PATH, tasks);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "unmark":
                    String[] toUnmark = userInput.split(" ");
                    if (toUnmark.length <= 1) {
                        System.out.println("Error. Unknown number.");
                        break;
                    }
                    int num = Integer.parseInt(userInput.split(" ")[1]);
                    if (num > counter) {
                        System.out.println("Error. Task does not exist.");
                        break;
                    }
                    System.out.println("OK, I've marked this task as not done yet:");
                    tasks.get(num - 1).markAsUndone();
                    System.out.println(tasks.get(num - 1).toString());
                    try {
                        writeToFile(DATA_FILE_PATH, tasks);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "todo":
                    String[] todos = userInput.split(" ");
                    if (todos.length <= 1) {
                        System.out.println("Error. Todo cannot be empty.");
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    Todo t = new Todo(userInput.substring(5));
                    System.out.println(t);
                    tasks.add(t);
                    counter++;
                    System.out.println(taskCounter(counter));
                    try {
                        appendToFile(DATA_FILE_PATH, t.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "deadline":
                    String[] deadlines = userInput.split(" ");
                    if (deadlines.length <= 1) {
                        System.out.println("Error. Deadline cannot be empty.");
                        break;
                    }
                    String description = userInput.substring(8).split("/")[0].trim();
                    if (description.isEmpty()) {
                        System.out.println("Unknown deadline description.");
                        break;
                    }
                    String by = userInput.split("/")[1].substring(2).trim();
                    if (by.isEmpty()) {
                        System.out.println("Unknown deadline.");
                        break;
                    }
                    System.out.println("Got it. I've added this task:");
                    Deadline d = new Deadline(description, by);
                    System.out.println(d);
                    tasks.add(d);
                    counter++;
                    System.out.println(taskCounter(counter));
                    try {
                        appendToFile(DATA_FILE_PATH, d.toString() + System.lineSeparator());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "event":
                    String[] events = userInput.split(" ");
                    if (events.length <= 1) {
                        System.out.println("Error. Event cannot be empty.");
                        break;
                    }
                    String input = userInput.substring(5).trim();
                    String descr = input.split("/")[0].trim();
                    if (descr.isEmpty()) {
                        System.out.println("Unknown event description.");
                        break;
                    }
                    String from = input.split("/")[1].substring(4).trim();
                    if (from.isEmpty()) {
                        System.out.println("Unknown event start time.");
                        break;
                    }
                    String to = input.split("/")[2].substring(2).trim();
                    if (to.isEmpty()) {
                        System.out.println("Unknown event end time.");
                        break;
                    }
                    Event e = new Event(descr, from, to);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(e);
                    tasks.add(e);
                    counter++;
                    System.out.println(taskCounter(counter));
                    try {
                        appendToFile(DATA_FILE_PATH, e.toString() + System.lineSeparator());
                    } catch (IOException err) {
                        System.out.println(err.getMessage());
                    }
                    break;
                case "delete":
                    String[] toDelete = userInput.split(" ");
                    if (toDelete.length <= 1) {
                        System.out.println("Error. Unknown number.");
                        break;
                    }
                    int j = Integer.parseInt(userInput.split(" ")[1]);
                    if (j > counter) {
                        System.out.println("Error. Task does not exist.");
                        break;
                    }
                    System.out.println("Noted. I have removed this task:");
                    System.out.println(tasks.get(j - 1));
                    tasks.remove(j - 1);
                    counter--;
                    System.out.println(taskCounter(counter));
                    try {
                        writeToFile(DATA_FILE_PATH, tasks);
                    } catch (IOException err) {
                        System.out.println(err.getMessage());
                    }
                    break;
                default:
                    System.out.println("HUH? What do you mean?");
                    break;
            }
            System.out.println(horizontalLine);
            userInput = sc.nextLine().trim();
            category = userInput.split(" ")[0];
        }
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
        sc.close();
    }

    private static String taskCounter(int counter) {
        if (counter <= 1) {
            return "Now you have " + counter + " task in the list.";
        } else {
            return "Now you have " + counter + " tasks in the list.";
        }
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void writeToFile(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }
    private static List<Task> loadDataFromFile(File file) throws IOException {
        List<Task> taskList = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String taskData = fileScanner.nextLine();
                taskList.add(Task.parseTask(taskData));
                System.out.println("loaded");
            }
        }
        if (taskList.isEmpty()) {
            // If the file was empty, instantiate a new task list
            taskList = new ArrayList<>();
        }
        return taskList;
    }
}