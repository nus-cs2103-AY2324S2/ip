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

    private static void storeTasks(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            try {
                task.writeToFile(storedTasksPath);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    private static void loadTasks(Scanner sc, TaskList tasks) {
        while (sc.hasNext()) {
            String current = sc.nextLine();
            String[] taskInfo = current.split("/");
            switch (taskInfo[0]) {
                case "T":
                    tasks.createToDo(taskInfo[2], false);
                    break;
                case "D":
                    tasks.createDeadline(taskInfo[2], LocalDate.parse(taskInfo[3]), false);
                    break;
                case "E":
                    tasks.createEvent(taskInfo[2], LocalDate.parse(taskInfo[3]), LocalDate.parse(taskInfo[4]), false);
                    break;
            }
            if (taskInfo[1].equals("X")) {
                tasks.mark(tasks.size(), false);
            }

        }
    }

    private static void resetTasksFile() {
        try {
            FileWriter fw = new FileWriter(storedTasksPath, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
//        ArrayList<Task> taskList = new ArrayList<>();

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
            loadTasks(fileScanner, taskList);
            resetTasksFile();
        }

        System.out.println("Hello! I'm Blob.\nWhat can I do for you?\n");
        String message = sc.nextLine();

        while (!message.equalsIgnoreCase("bye")) {
            if (message.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                System.out.println();
            } else if (message.split(" ")[0].equalsIgnoreCase("mark")) {
                try {
                    String number = message.split(" ")[1];
                    int n = Integer.parseInt(number);
                    taskList.mark(n);
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
                    taskList.unmark(n);
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
                    taskList.delete(n);
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
                    taskList.createToDo(task);

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
                        System.out.println("The correct format is: YYYY-MM-DD\n");
                    }
                    if (byDate != null) {
                        taskList.createDeadline(description, byDate);
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
                    LocalDate fromDate = null;
                    LocalDate toDate = null;
                    try {
                        fromDate = LocalDate.parse(from);
                        toDate = LocalDate.parse(to);
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format");
                        System.out.println("The correct format is: YYYY-MM-DD\n");
                    }
                    if (fromDate != null && toDate != null) {
                        taskList.createEvent(description, fromDate, toDate);
                    }
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

        storeTasks(taskList.tasks);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
