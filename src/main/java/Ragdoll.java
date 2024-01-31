import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Ragdoll{
    private static String USER = "Master";
    private static List<Task> tasks = new ArrayList<>();
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String logo = " /$$$$$$$                            /$$           /$$ /$$\n"
                + "| $$__  $$                          | $$          | $$| $$\n"
                + "| $$  \\ $$  /$$$$$$   /$$$$$$   /$$$$$$$  /$$$$$$ | $$| $$\n"
                + "| $$$$$$$/ |____  $$ /$$__  $$ /$$__  $$ /$$__  $$| $$| $$\n"
                + "| $$__  $$  /$$$$$$$| $$  \\ $$| $$  | $$| $$  \\ $$| $$| $$\n"
                + "| $$  \\ $$ /$$__  $$| $$  | $$| $$  | $$| $$  | $$| $$| $$\n"
                + "| $$  | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$/| $$| $$\n"
                + "|__/  |__/ \\_______/ \\____  $$ \\_______/ \\______/ |__/|__/\n"
                + "                     /$$  \\ $$\n"
                + "                    |  $$$$$$/\n"
                + "                     \\______/\n";

        System.out.println("Hello! I am your virtual assistant\n" + "\n" + logo);

        loadTasks();

        System.out.println("How can I assist you today, " + USER + "?");

        System.out.println(LINE);

        while(true) {
            String input = scanner.nextLine().trim();
            System.out.println(LINE);

            String[] parts = input.split(" ", 2);
            Commands command;

            try {
                command = Commands.valueOf(parts[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry, " + USER + ", I didn't understand the command...\n\n"
                        + "My commands include:\n"
                        + "LIST: Display all tasks in your list.\n"
                        + "MARK [task number]: Mark a task as completed.\n"
                        + "UNMARK [task number]: Mark a task as not completed.\n"
                        + "DELETE [task number]: Remove a task from your list.\n"
                        + "[task type] [task description]: Add a new task. Task types include TODO, DEADLINE, EVENT.\n"
                        + "LIST_ON [yyyy-mm-dd]: List all tasks on a specific date in format yyyy-MM-dd.\n"
                        + "DATE / TIME: Show the current date and time."
                        + "BYE: Exit the chatbot.");
                System.out.println(LINE);
                continue;
            }

            switch(command) {
            case BYE:
                scanner.close();
                System.out.println("See ya, " + USER + "!");
                System.out.println(LINE);
                return;
            case LIST:
                listTasks();
                break;
            case LIST_ON:
                listTasksOnDate(input);
                break;
            case DATE:
                // Fallthrough
            case TIME:
                showCurrentDateTime();
                break;
            case MARK:
                markTask(input);
                break;
            case UNMARK:
                unmarkTask(input);
                break;
            case DELETE:
                deleteTask(input);
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                addTask(input);
                break;
            }

            System.out.println(LINE);
        }
    }

    private static void addTask(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            System.out.println("Sorry, " + USER + ", please format your tasks as [task type] [task description]!\n"
                    + "I am only able to handle 3 task types: todo, deadline, and event.");
            return;
        }

        TaskType taskType;
        Task task = null;

        try {
            taskType = TaskType.valueOf(parts[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry, " + USER + ", please format your tasks as [task type] [task description]!\n"
                    + "I am only able to handle 3 task types: todo, deadline, and event.");
            return;
        }

        switch (taskType) {
        case TODO:
            task = new ToDo(parts[1]);
            break;

        case DEADLINE:
            String[] info = parts[1].split(" /by ", 2);
            if (info.length < 2) {
                System.out.println("Sorry, " + USER + ", please use 'deadline [task] /by [yyyy-mm-dd]'.");
                return;
            }
            try {
                LocalDate byDate = LocalDate.parse(info[1], DateTimeFormatter.ISO_LOCAL_DATE);
                task = new Deadline(info[0], byDate);
            } catch (DateTimeParseException e) {
                System.out.println("Sorry, " + USER + ", please use 'deadline [task] /by [yyyy-mm-dd]'.");
                return;
            }
            break;

        case EVENT:
            String[] eventParts = parts[1].split(" /from ", 2);
            if (eventParts.length < 2) {
                System.out.println("Sorry, " + USER
                        + ", please use 'event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]'.");
                return;
            }
            String[] timeParts = eventParts[1].split(" /to ", 2);
            if (timeParts.length < 2) {
                System.out.println("Sorry, " + USER
                        + ", please use 'event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]'.");
                return;
            }
            try {
                LocalDate from = LocalDate.parse(timeParts[0], DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate to = LocalDate.parse(timeParts[1], DateTimeFormatter.ISO_LOCAL_DATE);
                task = new Event(eventParts[0], from, to);
            } catch (DateTimeParseException e) {
                System.out.println("Sorry, " + USER
                        + ", please use 'event [task] /from [yyyy-mm-dd] /to [yyyy-mm-dd]'.");
                return;
            }
            break;
        }

        if (task != null) {
            tasks.add(task);
            saveTasks();
            System.out.println(USER + ", I've added this task:\n  " + task);

            int taskCount = tasks.size();
            if (taskCount <= 1) {
                System.out.println("Now you have " + taskCount + " task in the list, " + USER + "!");
            } else {
                System.out.println("Now you have " + taskCount + " tasks in the list, " + USER + "!");
            }
        }
    }

    private static void deleteTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            int taskCount = tasks.size();

            if (idx < 0 || idx >= taskCount) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                Task removed = tasks.remove(idx);
                taskCount--;
                saveTasks();
                System.out.println(USER + ", I've removed this task:\n  " + removed);

                if (taskCount <= 1) {
                    System.out.println("Now you have " + taskCount + " task in the list, " + USER + "!");
                } else {
                    System.out.println("Now you have " + taskCount + " tasks in the list, " + USER + "!");
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as delete [task index].");
        }
    }

    private static void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("There is no task yet, " + USER + "!");
        } else {
            System.out.println(USER + ", your task list has the following tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void listTasksOnDate(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println(USER + ", please use list_on [yyyy-mm-dd] to list tasks on a specific date.");
            return;
        }

        LocalDate date;
        try {
             date = LocalDate.parse(parts[1]);
        } catch (DateTimeParseException e) {
            System.out.println(USER + ", please use list_on [yyyy-mm-dd] to list tasks on a specific date.");
            return;
        }

        System.out.println(USER + ", on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy").withLocale(Locale.US))
                + ", you have the following tasks:");
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).getBy().isEqual(date)) {
                System.out.println(task);
            } else if (task instanceof Event && ((Event) task).getFrom().isEqual(date)) {
                System.out.println(task);
            }
        }
    }

    private static void showCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm:ss").withLocale(Locale.US);
        System.out.println(USER + ", the current date and time is: " + now.format(formatter) + "!");
    }

    private static void markTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(5)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                tasks.get(idx).mark();
                saveTasks();
                System.out.println(USER + "! I've marked this task as done:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as mark [task index].");
        }
    }

    private static void unmarkTask(String input) {
        try {
            int idx = Integer.parseInt(input.substring(7)) - 1;
            if (idx < 0 || idx >= tasks.size() || tasks.get(idx) == null) {
                System.out.println("No task numbered " + (idx + 1) + ", " + USER + "!");
            } else {
                tasks.get(idx).unmark();
                saveTasks();
                System.out.println("Ok, " + USER + "! I've undone this task:\n" + tasks.get(idx));
            }
        } catch (NumberFormatException e) {
            System.out.println(USER + ", please format it as mark [task index].");
        }
    }

    private static void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tasklist.txt"))) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(USER + ", an error occurred while saving tasks!");
            e.printStackTrace();
        }
    }

    private static void loadTasks() {
        File file = new File("tasklist.txt");
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" \\| ");
                    Task task = null;
                    switch (parts[0]) {
                    case "T":
                        task = new ToDo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], LocalDate.parse(parts[3]));
                        break;
                    case "E":
                        task = new Event(parts[2], LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
                        break;
                    }
                    if (task != null) {
                        if (parts[1].equals("1")) {
                            task.mark();
                        }
                        tasks.add(task);
                    }
                } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                    System.out.println(USER + ", I found a corrupted line in tasklist file! I'll skip it..!");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(USER + ", I could not find the task file... We have an empty task list!");
        } catch (IOException e) {
            System.out.println(USER + ", I could not read the tasklist file..!");
            e.printStackTrace();
        }
    }
}
