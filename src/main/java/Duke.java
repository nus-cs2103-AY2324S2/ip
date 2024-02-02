import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    static void greeting(String botName) {
        System.out.println("Hello! I'm " + botName + "\n"
                + "What can I do for you?");
    }

    static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    static void echo(String userInput, ArrayList<Task> TodoList) {
        String[] words = userInput.split("\\s+");
        if (words.length > 0) {
            String firstWord = words[0];
            int firstSpaceIndex = userInput.indexOf(' ');
            String description = userInput.substring(firstSpaceIndex + 1);
            switch (firstWord) {
                case "todo": {
                    if (words.length == 1) {
                        System.out.println("The description of a todo cannot be empty!");
                    } else {
                        Task t = new Todo(description);
                        TodoList.add(t);
                        listOverviewAfterAdding(t, TodoList);
                    }
                    break;
                }
                case "deadline": {
                    if (words.length == 1) {
                        System.out.println("The description of a deadline cannot be empty!");
                    } else {
                        String[] parts = description.split("\\\\by");
                        String ddl_description = parts.length > 0 ? parts[0].trim() : "";
                        String ddl_time = parts.length > 1 ? parts[1].trim() : "";
                        Task t = new Deadline(ddl_description, readAsDate(ddl_time));
                        TodoList.add(t);
                        listOverviewAfterAdding(t, TodoList);
                    }
                    break;
                }
                case "event": {
                    if (words.length == 1) {
                        System.out.println("The description of a todo cannot be empty!");
                    } else {
                        String[] parts = userInput.split("\\\\from|\\\\to");

                        // Collect words before "\from" into one string
                        String event_description = parts.length > 0 ? parts[0].trim() : "";

                        // Collect words between "\from" and "\to" into one string
                        String event_from = parts.length > 1 ? parts[1].trim() : "";

                        // Collect words after "\to" into one string
                        String event_to = parts.length > 2 ? parts[2].trim() : "";
                        Task t = new Event(event_description, readAsDate(event_from), readAsDate(event_to));
                        TodoList.add(t);
                        listOverviewAfterAdding(t, TodoList);
                    }
                    break;
                }
                default:
                    System.out.println("Sorry, I don't understand your command.");
                    break;
            }
        } else {
            System.out.println("Sorry, I don't understand your command.");
        }
    }

    static LocalDate readAsDate(String time) {
        String[] parts = time.split(" ");
        String datePart = parts[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(datePart, formatter);

        return localDate;
    }

    static void printList(ArrayList<Task> TodoList) {
        System.out.println("Here are the tasks in your list:");
        int length = TodoList.size();
        for (int i = 0; i < length; i++) {
            String pos = String.valueOf(i + 1);
            System.out.println(pos + "." + TodoList.get(i));
        }
    }

    static void listOverviewAfterAdding(Task t, ArrayList<Task> TodoList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        int length = TodoList.size();
        if (length == 1) {
            System.out.println("Now you have " + length + " task in the list.");
        } else {
            System.out.println("Now you have " + length + " tasks in the list.");
        }
        autoUpdate(TodoList);
    }

    static boolean isMarkTask(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length == 2) {
            if (words[0].equals("mark") || words[0].equals("unmark")) {
                try {
                    int number = Integer.parseInt(words[1]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }

    static void changeMarkingOfTask(String userInput, ArrayList<Task> TodoList) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = TodoList.get(number - 1);
        if (words[0].equals("mark")) {
            t.markAsDone();
        } else {
            t.unmark();
        }
        autoUpdate(TodoList);
    }

    static boolean isDeleteTask(String userInput) {
        String[] words = userInput.split("\\s+");
        if (words.length == 2) {
            if (words[0].equals("delete")) {
                try {
                    int number = Integer.parseInt(words[1]);
                    return true;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return false;
    }

    static void deleteTask(String userInput, ArrayList<Task> TodoList) {
        String[] words = userInput.split("\\s+");
        int number = Integer.parseInt(words[1]);
        Task t = TodoList.remove(number - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        int length = TodoList.size();
        if (length == 1) {
            System.out.println("Now you have " + length + " task in the list.");
        } else {
            System.out.println("Now you have " + length + " tasks in the list.");
        }
        autoUpdate(TodoList);
    }

    static void autoUpdate(ArrayList<Task> TodoList) {
        File file = new File("./data/duke.txt");
        file.getParentFile().mkdirs();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task t : TodoList) {
                if (t instanceof Todo) {
                    writer.write("T | " + t.isDone + " | " + t.description);
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    writer.write("D | " + d.isDone + " | " + d.description + " | " + d.getBy());
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    writer.write("E | " + e.isDone + " | " + e.description + " | " + e.getFrom() + " | " + e.getTo());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> getHistory() {
        String fileName = "./data/duke.txt";
        ArrayList<Task> historyList = new ArrayList<>();
        File file = new File(fileName);
        if (!file.exists()) {
            return new ArrayList<Task>();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task t = createTaskFromLine(line);
                if (t != null) {
                    historyList.add(t);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historyList;
    }

    private static Task createTaskFromLine(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length >= 3) {
            boolean isDone = Boolean.parseBoolean(parts[1]);
            String description = parts[2];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            switch (parts[0]) {
                case "T":
                    return new Todo(description, isDone);
                case "D":
                    if (parts.length >= 4) {
                        String by = parts[3];
                        LocalDate localDate = LocalDate.parse(by, formatter);
                        return new Deadline(description, localDate, isDone);
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        String from = parts[3];
                        String to = parts[4];
                        LocalDate localDateFrom = LocalDate.parse(from, formatter);
                        LocalDate localDateTo = LocalDate.parse(to, formatter);
                        return new Event(description, localDateFrom, localDateTo, isDone);
                    }
                    break;
                default:
                    break;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String botName = "Zizhen";
        greeting(botName);

        ArrayList<Task> TodoList = new ArrayList<>();
        TodoList = getHistory();

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        while ((!isExit) && scanner.hasNextLine()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                isExit = true;
            } else if (userInput.equals("list")) {
                printList(TodoList);
            } else if (isMarkTask(userInput)) {
                changeMarkingOfTask(userInput, TodoList);
            } else if (isDeleteTask(userInput)) {
                deleteTask(userInput, TodoList);
            } else {
                echo(userInput, TodoList);
            }
        }
        scanner.close();

        exit();
    }
}
