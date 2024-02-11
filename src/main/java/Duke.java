//import java.util.Scanner;
//import java.util.ArrayList;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.PrintWriter;
//
//public class Duke {
//    private static final String FILE_PATH = "./data/duke.txt";
//    private static ArrayList<Task> tasks = new ArrayList<>();
//    //private static int taskCount = 0;
//    public static void main(String[] args) throws DukeException {
//
//        loadTasksFromFile();
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Hello! I'm Bob");
//        System.out.println("What can I do for you?\n");
//
//        while(true){
//            String input = scanner.nextLine();
//
//            if ("bye".equals(input)) {
//                System.out.println("Bye. Hope to see you again soon!");
//                break;
//            } else if ("list".equals(input)) {
//                listTasks();
//            } else if (input.startsWith("delete ")) {
//                deleteTask(input);
//            } else if (input.startsWith("mark ")) {
//                try {
//                    markTask(input);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            } else if (input.startsWith("unmark ")) {
//                try {
//                    unmarkTask(input);
//                } catch (DukeException e) {
//                    System.out.println(e.getMessage());
//                }
//            } else if (input.startsWith("todo")) {
//                if (input.length() <= 5) {
//                    System.out.println("That's not a valid todo!");
//                } else {
//                    String description = input.substring(5).trim();
//                    if (description.isEmpty()) {
//                        System.out.println("That's not a valid todo!");
//                    } else {
//                        addTask(new ToDo(description));
//                    }
//                }
//            } else if (input.startsWith("deadline")) {
//                if (input.length() <= 9) {
//                    System.out.println("That's not a valid Deadline!");
//                } else {
//                    String[] parts = input.substring(9).split(" /by ");
//                    if (parts.length == 2) {
//                        try {
//                            addTask(new Deadline(parts[0], parts[1]));
//                        } catch (DukeException e) {
//                            System.out.println(e.getMessage());
//                        }
//                    } else {
//                        System.out.println("That's not a valid Deadline!");
//                    }
//                }
//            } else if (input.startsWith("event")) {
//                int fromIndex = input.indexOf(" /from");
//                int toIndex = input.indexOf(" /to");
//
//                if (fromIndex != -1 && toIndex != -1 && fromIndex < toIndex && fromIndex >= 6 && toIndex >= fromIndex + 7 && input.length() >= toIndex + 5) {
//                    String description = input.substring(6, fromIndex).trim();
//                    String startTime = input.substring(fromIndex + 7, toIndex).trim();
//                    String endTime = input.substring(toIndex + 5).trim();
//
//                    if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
//                        System.out.println("The description, start time, and end time of an event cannot be empty.");
//                    } else {
//                        try {
//                            addTask(new Event(description, startTime, endTime));
//                        } catch (DukeException e) {
//                            System.out.println(e.getMessage());
//                        }
//                    }
//                } else {
//                    System.out.println("That's not a valid Event!");
//                }
//            } else {
//                System.out.println("That's not a valid task!\n");
//            }
//        }
//
//        scanner.close();
//    }
//
//    private static void addTask(Task task) {
//        tasks.add(task);
//        System.out.println("Got it. I've added this task:");
//        System.out.println("  " + task);
//        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
//        saveTasksToFile();
//    }
//
//    private static void listTasks() {
//        System.out.println("Here are the tasks in your list:");
//        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println((i + 1) + "." + tasks.get(i).toString());
//        }
//        System.out.println("");
//    }
//
//    private static void markTask(String input) throws DukeException {
//        int taskIndex = Integer.parseInt(input.substring(5)) - 1;
//        if (taskIndex >= 0 && taskIndex < tasks.size()) {
//            tasks.get(taskIndex).markAsDone();
//            System.out.println("Nice! I've marked this task as done:");
//            System.out.println(tasks.get(taskIndex).toString() + "\n");
//            saveTasksToFile();
//        } else {
//            throw new DukeException("Task does not exist.");
//            //System.out.println("Task does not exist.");
//        }
//    }
//
//    private static void unmarkTask(String input) throws DukeException {
//        int taskIndex = Integer.parseInt(input.substring(7)) - 1; // subtract 1 for array index
//        if (taskIndex >= 0 && taskIndex < tasks.size()) {
//            tasks.get(taskIndex).markAsNotDone();
//            System.out.println("OK, I've marked this task as not done yet:");
//            System.out.println(tasks.get(taskIndex).toString() + "\n");
//            saveTasksToFile();
//        } else {
//            throw new DukeException("Task does not exist.");
//            //System.out.println("Task does not exist.");
//        }
//    }
//
//    private static void deleteTask(String input) {
//        try {
//            int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1; // Convert input string to task index
//            if (taskIndex < 0 || taskIndex >= tasks.size()) {
//                System.out.println("Task does not exist.");
//            } else {
//                Task removedTask = tasks.remove(taskIndex);
//                System.out.println("Noted. I've removed this task:");
//                System.out.println("  " + removedTask);
//                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
//                saveTasksToFile();
//            }
//        } catch (NumberFormatException e) {
//            System.out.println("Please enter a valid task number to delete.");
//        }
//    }
//    private static void saveTasksToFile() {
//        try {
//            new File(FILE_PATH).getParentFile().mkdirs(); // Create directories if they do not exist
//            PrintWriter writer = new PrintWriter(new File(FILE_PATH));
//
//            for (Task task : tasks) {
//                writer.println(taskToFileString(task));
//            }
//
//            writer.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred while saving tasks to file.");
//        }
//    }
//
//    private static void loadTasksFromFile() {
//        try {
//            File file = new File(FILE_PATH);
//            if (!file.exists()) {
//                return; // If the file does not exist, just return.
//            }
//
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNext()) {
//                String line = scanner.nextLine();
//                Task task = fileStringToTask(line);
//                if (task != null) {
//                    tasks.add(task);
//                }
//            }
//
//            scanner.close();
//        } catch (FileNotFoundException | DukeException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private static String taskToFileString(Task task) {
//        String type = task instanceof ToDo ? "T" :
//                        task instanceof Deadline ? "D" :
//                        task instanceof Event ? "E" : "";
//        String status = task.isDone ? "1" : "0";
//        String details = type + " | " + status + " | " + task.description;
//
//        if (task instanceof Deadline) {
//            Deadline deadline = (Deadline) task;
//            details += " | " + deadline.getByForFile();
//        } else if (task instanceof Event) {
//            Event event = (Event) task;
//            details += " | " + event.getStartForFile() + " | " + event.getEndForFile();
//        }
//
//        return details;
//    }
//
//
//    private static Task fileStringToTask(String line) throws DukeException {
//        // Convert a string from a file back to a task
//        String[] parts = line.split(" \\| ");
//        if (parts.length < 3) {
//            return null; // Not enough parts to construct a task
//        }
//        String type = parts[0];
//        boolean isDone = "1".equals(parts[1]);
//        String description = parts[2];
//
//        Task task = null;
//        switch (type) {
//            case "T":
//                task = new ToDo(description);
//                break;
//            case "D":
//                task = new Deadline(description, parts.length > 3 ? parts[3] : "");
//                break;
//            case "E":
//                task = new Event(description, parts.length > 3 ? parts[3] : "", parts.length > 4 ? parts[4] : "");
//                break;
//        }
//        if (task != null && isDone) {
//            task.markAsDone();
//        }
//
//        return task;
//    }
//}

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            // Assume Storage.load() returns List<String>
            // and TaskList has a constructor that accepts List<String> and converts it to List<Task>
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(); // Start with an empty TaskList if loading fails
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                // Create a Parser instance and parse the command
                Parser parser = new Parser(tasks, ui, storage);
                parser.parse(command);
                //storage.save(tasks.getTasks());
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
