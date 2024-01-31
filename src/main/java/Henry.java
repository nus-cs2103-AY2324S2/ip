import java.io.IOException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Henry {
    private Ui ui;
    private enum CommandType {
        LIST, UNMARK, MARK, DELETE, TODO, DEADLINE, EVENT, BYE, UNKNOWN
    }
    private static ArrayList<Task> items = new ArrayList<Task>();
    public static void loadTaskFromFile() {
        Path path = Paths.get("data", "henry.txt");
        File file = path.toFile();
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String[] currLine = line.split(" \\| ");
                switch (currLine[0]) {
                case "T":
                    items.add(new Todo(currLine[2]));
                    break;
                case "D":
                    items.add(new Deadline(currLine[2], currLine[3]));
                    break;
                case "E":
                    items.add(new Event(currLine[2], currLine[3], currLine[4]));
                    break;
                default:
                    break;
                }
                if (currLine[1].equals("1")) {
                    items.get(items.size() - 1).markAsDone();
                }
                line = br.readLine();
            }
        } catch (IOException | HenryException e) {
            System.err.println(e);
        }
    }
    public static void saveTasksToFile() {
        Path path = Paths.get("data", "henry.txt");
        File file = path.toFile();
        try {
            FileWriter fw = new FileWriter(file);
            for (Task item : items) {
                fw.write(item.toFileString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void addTask(Task task) {
        items.add(task);
        System.out.println("Added this task");
        System.out.println(items.get(items.size() - 1));
        System.out.printf("Now you have %d tasks in the list :(\n", items.size());
        System.out.println();
    }
    public static void markTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).markAsDone();
        System.out.println("This task is marked as done XD");
        System.out.println(items.get(index));
        System.out.println();
    }
    public static void unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).unmarkAsDone();
        System.out.println("This task is marked as undone :(");
        System.out.println(items.get(index));
        System.out.println();
    }
    public static void deleteTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        System.out.println("This task is deleted :)");
        System.out.println(items.get(index));
        System.out.println();
        items.remove(index);
    }
    private static void handleCommand(CommandType commandType, String params) throws HenryException {
        switch (commandType) {
        case LIST:
            System.out.println("Here is a list of tasks:");
            for (int i = 0; i < items.size(); i = i + 1) {
                System.out.printf("%d. %s\n", i + 1, items.get(i));
            }
            System.out.println();
            break;
        case MARK:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            markTask(Integer.parseInt(params) - 1);
            saveTasksToFile();
            break;
        case UNMARK:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            unmarkTask(Integer.parseInt(params) - 1);
            saveTasksToFile();
            break;
        case TODO:
            if (params.isBlank()) {
                throw new HenryException("No description provided");
            }
            addTask(new Todo(params));
            saveTasksToFile();
            break;
        case DEADLINE:
            if (params.isBlank()) {
                throw new HenryException("No description and /by provided");
            }
            if (!params.contains("/by")) {
                throw new HenryException("When this has to be done by?");
            }
            String[] deadlineParams = params.split(" /by ");
            addTask(new Deadline(deadlineParams[0], deadlineParams[1]));
            saveTasksToFile();
            break;
        case EVENT:
            if (params.isBlank()) {
                throw new HenryException("No description, /from and /to provided");
            }
            if (!params.contains("/from") || !params.contains("/to")) {
                throw new HenryException("Please provide /from and /to");
            }
            String[] eventParams = params.split(" /from | /to ");
            addTask(new Event(eventParams[0], eventParams[1], eventParams[2]));
            saveTasksToFile();
            break;
        case DELETE:
            if (params.isBlank()) {
                throw new HenryException("No index provided");
            }
            deleteTask(Integer.parseInt(params) - 1);
            saveTasksToFile();
            break;
        default:
            try {
                throw new HenryException("I don't understand this command...");
            } catch (HenryException e) {
                System.err.println(e);
            }
            break;
        }
    }
    public Henry() {
        ui = new Ui();
    }
    public void run() {
        loadTaskFromFile();
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String currentLine = scanner.nextLine();
            String[] command = currentLine.split(" ", 2);
            CommandType commandType;
            String params = command.length < 2 ? "" : command[1];

            try {
                commandType = CommandType.valueOf(command[0].toUpperCase());
                if (commandType.equals(CommandType.BYE)) {
                    saveTasksToFile();
                    ui.bye();
                    break;
                }
            } catch (IllegalArgumentException e) {
                commandType = CommandType.UNKNOWN;
            }

            try {
                handleCommand(commandType, params);
            } catch(HenryException e) {
                System.err.println(e);
            }
        }
    }
    public static void main(String[] args) {
        new Henry().run();
    }
}