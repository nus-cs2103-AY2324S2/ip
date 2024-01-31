import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws DukeException {
        // String home = System.getProperty("user.home");
        // Path path = Paths.get(home, "OneDrive","Documents", "NUS",
        //         "Y2S2", "CS2103", "Individual Project", "duke.txt");
        String relativePath = "data/duke.txt";
        Path path = Paths.get(relativePath);
        String logo =  " _____ _               _\n"
                +       "/  __ (_)             | |\n"
                +       "| /  \\/_  ___ __ _  __| | __ _\n"
                +       "| |   | |/ __/ _` |/ _` |/ _` |\n"
                +       "| \\__/\\ | (_| (_| | (_| | (_| |\n"
                +       " \\____/_|\\___\\__,_|\\__,_|\\__,_|\n";
        System.out.println("Hello from\n" + logo);
        String horizontalLine = "-".repeat(60);
        greeting("Cicada", horizontalLine);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type your command and press Enter. Type 'bye' to quit.");
        List<Task> tasks = loadTasksFromFile(path);
        boolean exitLoop = false;
        while (!exitLoop) {
            String command = scanner.next();
            String input = scanner.nextLine();
            switch (command) {
            case "bye":
                ending(horizontalLine);
                exitLoop = true;
                break;
            case "list":
                listTasks(tasks, horizontalLine);
                break;
            case "mark":
                try {
                    markTask(input, tasks, horizontalLine);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Unable to parse the input as an integer. Please put a number after " + command);
                }
                break;
            case "unmark":
                try {
                    unmarkTask(input, tasks, horizontalLine);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Unable to parse the input as an integer. Please put a number after " + command);
                }
                break;
            case "todo":
                try {
                    Task newTodo = createTask(input, "todo");
                    tasks.add(newTodo);
                    System.out.println(horizontalLine);
                    newTodo.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "deadline":
                try {
                    Task newDeadline = createTask(input, "deadline");
                    tasks.add(newDeadline);
                    System.out.println(horizontalLine);
                    newDeadline.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "event":
                try {
                    Task newEvent = createTask(input, "event");
                    tasks.add(newEvent);
                    System.out.println(horizontalLine);
                    newEvent.displayTask(tasks.size());
                    System.out.println(horizontalLine);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
                break;
            case "delete":
                try {
                    delete(input, tasks, horizontalLine);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Unable to parse the input as an integer. "
                            + "Please put a number after 'delete'.");
                }
                break;
            case "save":
                save(path, tasks);
                break;
            default:
                System.err.println("OOPS! This command doesn't exist. Retry!");
                break;
            }
        }
        scanner.close();
    }

    public static List<Task> loadTasksFromFile(Path filePath) {
        boolean directoryExists = Files.exists(filePath);
        List<Task> tasks = new ArrayList<>();
        if (directoryExists) {
            try {
                List<String> lines = Files.readAllLines(filePath);
                for (String line : lines) {
                    try (Scanner scanner = new Scanner(line)) {
                        if (scanner.hasNext()) {
                            String taskType = scanner.next();
                            switch (taskType) {
                            case "T":
                                tasks.add(convertToTodo(line));
                                break;
                            case "D":
                                tasks.add(convertToDeadline(line));
                                break;
                            case "E":
                                tasks.add(convertToEvent(line));
                                break;
                            }
                        }
                    }
                }
                System.out.println("The file is loaded");
            } catch (IOException e) {
                System.err.println("There is IO Exception for readAllLines.");
            }
        }
        return tasks;
    }

    public static ToDo convertToTodo(String string) {
        String[] parts = string.split("\\|");
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        return new ToDo(description, status);
    }

    public static Deadline convertToDeadline(String string) {
        String[] parts = string.split("\\|");
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String deadline = parts[3].trim();
        return new Deadline(description, status, deadline);
    }

    public static Event convertToEvent(String string) {
        String[] parts = string.split("\\|");
        boolean status = parts[1].trim().equals("1");
        String description = parts[2].trim();
        String start = parts[3].trim();
        String end = parts[4].trim();
        return new Event(description, status, start, end);
    }

    public static void listTasks(List<Task> tasks, String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int j = i + 1;
            System.out.println(j + "." + tasks.get(i).toString());
        }
        System.out.println(horizontalLine);
    }

    public static void markTask(String number, List<Task> tasks, String horizontalLine) throws DukeException {
        number = number.trim();
        if (number.isBlank()) {
            throw new DukeException("Please state which task you want to mark.");
        }
        int index = Integer.parseInt(number) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Please look carefully. This task is not inside the task list.");
        }
        tasks.get(index).markDone(horizontalLine);
    }

    public static void unmarkTask(String number, List<Task> tasks, String horizontalLine) throws DukeException {
        number = number.trim();
        if (number.isBlank()) {
            throw new DukeException("Please state which task you want to unmark.");
        }
        int index = Integer.parseInt(number) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Please look carefully. This task is not inside the task list.");
        }
        tasks.get(index).unmark(horizontalLine);
    }

    public static Task createTask(String secondParts, String taskType) throws DukeException {
        Task newTask = null;
        if (secondParts.isBlank()) {
            throw new DukeException("OOPS! The description of a " + taskType + " cannot be empty.");
        }
        secondParts = secondParts.trim();
        switch (taskType) {
        case "todo":
            newTask = new ToDo(secondParts);
            break;
        case "deadline":
            if (!secondParts.contains("by")) {
                throw new DukeException("OOPS! 'by' keyword is missing. You are required "
                        + "to state the deadline using the 'by' keyword.");
            }
            String[] instruction = secondParts.split(" by ", 2);
            if (instruction.length < 2) {
                throw new DukeException("OOPS! You forget to write do the task by when");
            }
            String description = instruction[0];
            String deadline = instruction[1];
            newTask = new Deadline(description, deadline);
            break;
        case "event":
            if (!secondParts.contains("from") || !secondParts.contains("to")) {
                throw new DukeException("OOPS! 'from' and/or 'to' keywords are missing. You are required to "
                        + "state the starting and ending time using these two keywords.");
            }
            String[] e_instruction = secondParts.split(" from ", 2);
            if (e_instruction.length < 2) {
                throw new DukeException("OOPS! You forget to write the starting time of this event.");
            }
            String e_description = e_instruction[0];
            String[] subInstruction = e_instruction[1].split(" to ", 2);
            if (subInstruction.length < 2) {
                throw new DukeException("OOPS! You forget to write the ending time of this event.");
            }
            String from = subInstruction[0];
            String to = subInstruction[1];
            newTask = new Event(e_description, from, to);
            break;

        }
        return newTask;
    }
    public static void delete(String number, List<Task> tasks, String horizontalLine) throws DukeException {
        number = number.trim();
        if (number.isBlank()) {
            throw new DukeException("Please state which task you want to delete.");
        }
        int index = Integer.parseInt(number) - 1;
        if (index >= tasks.size() || index < 0) {
            throw new DukeException("Please look carefully. This task is not inside the task list.");
        }
        String taskStr = tasks.get(index).toString();
        tasks.remove(index);
        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:\n  " + taskStr);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    public static void save(Path path, List<Task> tasks) {
        try {
            List<String> taskStrList = new ArrayList<String>();
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }
            for (Task task : tasks) {
                String str = task.convertToFileFormat();
                taskStrList.add(str);
            }
            String tasksStr = String.join("\n", taskStrList);
            Files.writeString(path, tasksStr);
            System.out.println("It is successfully saved!");
        } catch (IOException e) {
            System.err.println("There are some error in saving. Try again");
        }
    }
    public static void greeting(String chatbotName, String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm " + chatbotName + "\nWhat can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void ending(String horizontalLine) {
        System.out.println(horizontalLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }
}
