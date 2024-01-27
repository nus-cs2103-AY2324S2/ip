import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (ArtemisException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }

            Command command = Parser.parseCommand(input);
            command.execute(tasks, ui, storage);
        }
        sc.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasksFromFile();

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Artemis");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        while (true) {
            System.out.println();
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println("    ____________________________________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); ++i) {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println("    ____________________________________________________________");
            } else if (input.contains("mark")) {
                String[] token = input.split(" ");
                int markIndex = Integer.parseInt(token[1]) - 1;

                if (token[0].equals("unmark")) {
                    tasks.get(markIndex).markAsNotDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     OK, I've marked this task as not done yet:");
                    System.out.println("       " + tasks.get(markIndex));
                    System.out.println("    ____________________________________________________________");
                } else if (token[0].equals("mark")) {
                    tasks.get(markIndex).markAsDone();
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("       " + tasks.get(markIndex));
                    System.out.println("    ____________________________________________________________");
                }
            } else if (input.contains("todo")) {
                try {
                    String description = input.replace("todo", "").trim();
                    if (description.isEmpty()) {
                        throw new ArtemisException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    tasks.add(new Todo(description));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1));
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } catch (ArtemisException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else if (input.contains("deadline")) {
                try {
                    String[] tokens = input.split("/by");
                    if (tokens.length < 2) {
                        throw new ArtemisException("OOPS!!! Invalid deadline format. Please use: deadline [description] /by [dd-mm-yyyy hhmm]");
                    }

                    String description = tokens[0].replace("deadline ", "").trim();
                    String by = tokens[1].trim();
                    tasks.add(new Deadline(description, by));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1));
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } catch (ArtemisException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else if (input.contains("event")) {
                try {
                    String[] tokens = input.split("/from");
                    if (tokens.length < 2) {
                        throw new ArtemisException("OOPS!!! Invalid event format. Please use: event [description] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]");
                    }

                    String description = tokens[0].replace("event ", "").trim();
                    String[] fromTo = tokens[1].split("/to");
                    if (fromTo.length < 2) {
                        throw new ArtemisException("OOPS!!! Invalid event format. Please use: event [description] /from [dd-mm-yyyy hhmm] /to [dd-mm-yyyy hhmm]");
                    }

                    String from = fromTo[0].trim();
                    String to = fromTo[1].trim();
                    tasks.add(new Event(description, from, to));
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + tasks.get(tasks.size() - 1));
                    System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("    ____________________________________________________________");
                } catch (ArtemisException e) {
                    System.out.println("    ____________________________________________________________");
                    System.out.println("     " + e.getMessage());
                    System.out.println("    ____________________________________________________________");
                }
            } else if (input.contains("delete")) {
                String[] token = input.split(" ");
                int deleteIndex = Integer.parseInt(token[1]) - 1;

                System.out.println("    ____________________________________________________________");
                System.out.println("     Noted. I've removed this task:");
                System.out.println("       " + tasks.get(deleteIndex));
                tasks.remove(deleteIndex);
                System.out.println("     Now you have " + tasks.size() + " tasks in the list.");
                System.out.println("    ____________________________________________________________");
            } else {
                System.out.println("    ____________________________________________________________");
                System.out.println("     OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("    ____________________________________________________________");
            }

            saveTasksToFile(tasks);

        }

        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
        sc.close();
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            createDirectoryIfNotExists(DIRECTORY_PATH);

            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
                for (Task task : tasks) {
                    writer.println(task.toFileString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            createDirectoryIfNotExists(DIRECTORY_PATH);

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    try {
                        Task task = Task.fromFileString(line);
                        tasks.add(task);
                    } catch (IllegalArgumentException e) {
                        // Log the error or handle it based on your application's needs
                        System.err.println("Error loading task from file: " + e.getMessage());
                        // Optionally, you can choose to skip the corrupted line and continue with the next line
                        continue;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // File doesn't exist, handle this case by creating an empty task list
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private static void createDirectoryIfNotExists(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            if (!created) {
                System.err.println("Failed to create the directory: " + directoryPath);
            }
        }
    }
}
