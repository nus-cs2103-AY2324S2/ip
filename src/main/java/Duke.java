import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public enum Command {
        BYE, LIST, MARK, DELETE, TODO, DEADLINE, EVENT, UNKNOWN
    }

    private static final String FILE_PATH = "./data/duke.txt";

    public static void main(String[] args) {
        String logo = "Chucklbot";
        System.out.println("Hello I'm " + logo + "\nWhat can I do for you? \n ");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();
        loadTasks(store);

        String byeMessage = "Bye! Hope to see you again soon.";
        while (true) {
            Command command = getCommand(sc.next());
            switch (command) {
                case BYE:
                    System.out.println(byeMessage);
                    return;
                case LIST:
                    displayTasks(store);
                    break;
                case MARK:
                    if (sc.hasNextInt()) {
                        int num = sc.nextInt();
                        store.get(num - 1).setStatus();
                    } else {
                        System.out.println("Please enter a valid task number for marking.");
                        sc.next(); // Consume the non-integer input
                    }
                    break; // Add break statement
                case DELETE:
                    if (sc.hasNextInt()) {
                        int num = sc.nextInt();
                        delete(store, num);
                    } else {
                        System.out.println("Please enter a valid task number for deletion.");
                        sc.next(); // Consume the non-integer input
                    }
                    break; // Add break statement
                default:
                    addList(store, command, sc);
            }
            saveTasks(store);
        }


    }

    private static void loadTasks(ArrayList<Task> store) {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();

                    // Try to process the line, and handle any potential exceptions
                    try {
                        Task task = parseTaskFromLine(line);
                        store.add(task);
                    } catch (Exception e) {
                        System.out.println("Error loading task from file. Corrupted data detected. Skipping line.");
                        // Optionally, log the exception for further investigation
                        e.printStackTrace();
                    }
                }
                fileScanner.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    private static Task parseTaskFromLine(String line) throws IOException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IOException("Invalid task format. Skipping line.");
        }

        String type = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];

        Task task;
        switch (type) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new IOException("Invalid deadline format. Skipping line.");
                }
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case "E":
                if (parts.length < 4) {
                    throw new IOException("Invalid event format. Skipping line.");
                }
                String[] eventParts = parts[3].split(" from ");
                if (eventParts.length < 2) {
                    throw new IOException("Invalid event format. Skipping line.");
                }
                String start = eventParts[0];
                String end = eventParts[1];
                task = new Event(description, start, end);
                break;
            default:
                throw new IOException("Invalid task type in file. Skipping line.");
        }

        if (isDone) {
            task.setStatus();
        }

        return task;
    }

    private static void saveTasks(ArrayList<Task> store) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist
            }

            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : store) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static Command getCommand(String input) {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    // if list entered
    public static void displayTasks(ArrayList<Task> store) {
        System.out.println("Here are the items in your list :) ");
        for (int i = 0; i < store.size(); i++) {
            System.out.println((i + 1) + ". " + store.get(i));
        }
    }

    public static void addList(ArrayList<Task> store, Command added, Scanner sc) {
        Task toBeAdded;

        switch (added) {
            case DEADLINE:
                // Read the entire line
                String inputLine = sc.nextLine().trim();

                // Check if there's "/by" in the input
                if (!inputLine.contains("/by")) {
                    try {
                        throw new DukeException("OOPS!!! Please provide a deadline time using '/by'.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }

                // Split the input into description and time
                String[] parts = inputLine.split("/by", 2);
                String description = parts[0].trim();
                String time = parts[1].trim();

                // Check if description or time is empty
                if (description.isEmpty() || time.isEmpty()) {
                    try {
                        throw new DukeException("OOPS!!! The description and deadline time cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }

                toBeAdded = new Deadline(description, time);
                break;
            case TODO:
                String descriptionTodo = sc.nextLine().trim();
                if (descriptionTodo.isEmpty()) {
                    try {
                        throw new DukeException.EmptyTodoDescriptionException();
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                toBeAdded = new Todo(descriptionTodo);
                break;
            case EVENT:
                // event project meeting /from Mon 2pm /to 4pm
                String inputLineEvent = sc.nextLine().trim();
                if (!inputLineEvent.contains("/from") || !inputLineEvent.contains("/to")) {
                    try {
                        throw new DukeException("OOPS!!! Please provide event timing using '/from' and '/to'.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }

                String[] partsEvent = inputLineEvent.split("/from", 2);
                String descriptionEvent = partsEvent[0].trim();

                // Check if the description is empty
                if (descriptionEvent.isEmpty()) {
                    try {
                        throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }
                partsEvent = partsEvent[1].split("/to", 2);
                String from = partsEvent[0].trim();
                String to = partsEvent[1].trim();
                // Check if "from" or "to" is empty
                if (from.isEmpty() || to.isEmpty()) {
                    try {
                        throw new DukeException("OOPS!!! The event timing cannot be empty.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                        return;
                    }
                }

                toBeAdded = new Event(descriptionEvent, from, to);
                break;
            default:
                try {
                    throw new DukeException.UnknownCommandException();
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    return;
                }
        }

        store.add(toBeAdded);

        System.out.println("Got it! I've added this task.\n" + toBeAdded + "\nNow you have " + Task.getNumOfTasks() + " tasks in your list");
    }


    public static void delete(ArrayList<Task> store, int num) {
        Task temp;
        if (num < 1 || num > store.size()) {
            // exception if out of bounds num
            System.out.println("Invalid task number. Please enter a valid task number.");
        } else {
            // exception if elemnt doens't exist
            temp = store.get(num - 1);
            store.remove(num - 1);
            System.out.println( "Noted. I've removed this task:");
            Task.decrementTotal();
            System.out.println( temp + "\nNow you have " + Task.getNumOfTasks() + " tasks in your list");
        }


    }

}

