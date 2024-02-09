import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static String storedTasksPath = "./data/storedTasks.txt";

    private static void mark(int n, ArrayList<Task> tasks) {
        if ((n > 0) && (tasks.get(n - 1) != null)) {
            tasks.get(n - 1).setDone();
            System.out.println("Nice! I've marked this task done:");
            System.out.println("[" + tasks.get(n - 1).getStatusIcon() + "] "
                    + tasks.get(n - 1).getDescription() + "\n");
        } else {
            System.out.println("You don't have that task!\n");
        }
    }

    private static void unmark(int n, ArrayList<Task> tasks) {
        if ((n > 0) && (tasks.get(n - 1) != null)) {
            tasks.get(n - 1).setUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + tasks.get(n - 1).getStatusIcon() + "] "
                    + tasks.get(n - 1).getDescription() + "\n");
        } else {
            System.out.println("You don't have that task!\n");
        }
    }

    private static void delete(int n, ArrayList<Task> tasks) {
        if ((n > 0) && (tasks.get(n - 1) != null)) {
            Task removed = tasks.remove(n - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removed);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
        } else {
            System.out.println("You don't have that task!\n");
        }
    }

    private static void createToDo(String task, ArrayList<Task> tasks) {
        ToDo todo = new ToDo(task);
        tasks.add(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    private static void createDeadline(String description, LocalDate by, ArrayList<Task> tasks) {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    private static void createEvent(String description, String from, String to, ArrayList<Task> tasks) {
        Event event = new Event(description, from, to);
        tasks.add(event);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.\n");
    }

    private static void storeTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            try {
                task.writeToFile(storedTasksPath);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    private static void loadTasks(Scanner sc, ArrayList<Task> tasks) {
        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] taskInfo = current.split("/");
            Task task = null;
            switch (taskInfo[0]) {
                case "T":
                    task = new ToDo(taskInfo[2]);
                    break;
                case "D":
                    task = new Deadline(taskInfo[2], LocalDate.parse(taskInfo[3]));
                    break;
                case "E":
                    task = new Event(taskInfo[2], taskInfo[3], taskInfo[4]);
                    break;
            }
            if (task != null) {
                if (taskInfo[1].equals("X")) {
                    task.setDone();
                }
                tasks.add(task);
            }
        }
    }

    private static void resetTasksFile() {
        try {
            FileWriter fw = new FileWriter(storedTasksPath, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wromg: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(storedTasksPath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error while creating file: " + e.getMessage());
            }
        }

        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Stored tasks file does not exists: " + e.getMessage());
        }

        if (fileScanner != null) {
            loadTasks(fileScanner, tasks);
            resetTasksFile();
        }

        System.out.println("Hello! I'm Blob.\nWhat can I do for you?\n");
        String message = sc.nextLine();

        while (!message.equalsIgnoreCase("bye")) {
            if (message.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println();
            } else if (message.split(" ")[0].equalsIgnoreCase("mark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    mark(n, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!");
                    System.out.println("Type: 'mark n' to mark the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'mark n' to mark the n-th task.");
                    System.out.println("For example type: 'mark 1' to mark the first task.\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("unmark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    unmark(n, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!");
                    System.out.println("Type: 'unmark n' to unmark the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'unmark n' to unmark the n-th task.");
                    System.out.println("For example, type: 'unmark 1' to mark the first task.\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("delete")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    delete(n, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("You forgot to type which task!");
                    System.out.println("Type: 'delete n' to delete the n-th task.\n");
                } catch (NumberFormatException e) {
                    System.out.println("Type: 'delete n' to delete the n-th task.");
                    System.out.println("For example type: 'delete 1' to delete the first task.\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("todo")) {
                try {
                    String task = message.split(" ", 2)[1];
                    createToDo(task, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The correct format is:");
                    System.out.println("todo <description>\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("deadline")) {
                try {
                    String task = message.split(" ", 2)[1];
                    String description = task.split(" /by ", 2)[0];
                    String by = task.split(" /by ", 2)[1];
                    LocalDate byDate = null;
                    try {
                        byDate = LocalDate.parse(by);
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format");
                    }
                    if (byDate != null) {
                        createDeadline(description, byDate, tasks);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The correct format is:");
                    System.out.println("deadline <description> /by <deadline time>\n");
                }
            } else if (message.split(" ")[0].equalsIgnoreCase("event")) {
                try {
                    String task = message.split(" ", 2)[1];
                    String description = task.split(" /from ", 2)[0];
                    String fromBy = task.split(" /from ", 2)[1];
                    String from = fromBy.split(" /to ", 2)[0];
                    String to = fromBy.split(" /to ", 2)[1];
                    createEvent(description, from, to, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("The correct format is:");
                    System.out.println("event <description> /from <start time> /to <end time>\n");
                }
            } else {
                System.out.println("Sorry :(");
                System.out.println("You need to use 'todo', 'deadline' or 'event' command to add a task.");
                System.out.println("You can use 'list' to see all of your tasks.");
                System.out.println("Use 'mark' or 'unmark' for any of your tasks.\n");
            }
            message = sc.nextLine();
        }

        storeTasks(tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
