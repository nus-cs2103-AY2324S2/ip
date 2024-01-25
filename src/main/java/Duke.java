import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Duke {
    protected static final String savePath = "./data/duke.txt";
    public static void main(String[] args) throws IOException {
        ArrayList<Task> list = initiateList();
        Scanner scanner = new Scanner(System.in);
        printWithLines("Hello! I'm Bob!", "What can I do for you?");
        String message = null;

        do {
            try {
                message = scanner.nextLine();
                if (message.startsWith("todo")) {
                    handleTodo(list, message);
                } else if (message.startsWith("deadline")) {
                    handleDeadline(list, message);
                } else if (message.startsWith("event")) {
                    handleEvent(list, message);
                } else if (message.equals("list")) {
                    handleList(list);
                } else if (message.startsWith("mark")) {
                    handleMark(list, message);
                } else if (message.startsWith("unmark")) {
                    handleUnmark(list, message);
                } else if (message.startsWith("delete")) {
                    deleteTask(list, message);
                } else if (!message.equals("bye")) {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means buddy.");
                }
            } catch (DukeException e) {
                printWithLines(e.getMessage());
            }
        } while (!message.equals("bye"));
        saveCurrentList(list);
        printWithLines("Bye. Hope to see you again soon!");
        System.out.println("------------------------------------------");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        File file = new File(filePath);
        file.getParentFile().mkdirs(); // This will create the data directory if it doesn't exist
        FileWriter fw = new FileWriter(file);
        fw.write(textToAdd);
        fw.close();
    }


    private static void handleTodo(ArrayList<Task> list, String message) throws DukeException, IOException {
        if (message.trim().equals("todo")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty buddy.");
        }
        String description = message.substring(5).trim();
        Task task = new Task(description);
        list.add(task);
        printWithLines("Got it. I've added this task:", task.toString(), "Now you have " + list.size() + " tasks in the list.");
    }

    private static void handleDeadline(ArrayList<Task> list, String message) throws DukeException {
        String[] parts = message.split("/by", 2);
        if (parts.length < 2) {
            throw new DukeException("OOPS!!! The deadline date/time is missing buddy.");
        }
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(by, formatter);
            Deadline task = new Deadline(description, date);
            list.add(task);
            printWithLines("Got it. I've added this task:", task.toString(), "Now you have " + list.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The deadline date format is incorrect. Please use yyyy-MM-dd format.");
        }
    }

    private static void printDateEvents(String d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(d, formatter);

    }

    private static void handleEvent(ArrayList<Task> list, String message) throws DukeException {
        String[] parts = message.split(" /from ", 2);
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new DukeException("OOPS!!! The event time is missing or incomplete buddy.");
        }
        String description = parts[0].substring(6).trim();
        String[] timeParts = parts[1].split(" /to ", 2);
        String fromTime = timeParts[0].trim();
        String toTime = timeParts[1].trim();
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTimeFrom = LocalDateTime.parse(fromTime, formatter);
            LocalDateTime dateTimeTo = LocalDateTime.parse(toTime, formatter);
            Event task = new Event(description, dateTimeFrom, dateTimeTo);
            list.add(task);
            printWithLines("Got it. I've added this task:", task.toString(), "Now you have " + list.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!!! The deadline date format is incorrect. Please use yyyy-MM-dd HH:mm format.");
        }
    }

    private static void handleList(ArrayList<Task> list) {
        ArrayList<String> taskDescriptions = new ArrayList<>();
        taskDescriptions.add("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            taskDescriptions.add((i + 1) + ". " + list.get(i).toString());
        }
        printWithLines(taskDescriptions.toArray(new String[0]));
    }

    private static void handleMark(ArrayList<Task> list, String message) throws DukeException {
        if (message.trim().equals("mark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(5).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.markAsDone();
        printWithLines("Nice! I've marked this task as done:", task.toString());
    }

    private static void handleUnmark(ArrayList<Task> list, String message) throws DukeException {
        if (message.trim().equals("unmark")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        task.unMarkAsDone();
        printWithLines("OK, I've marked this task as not done yet:", task.toString());
    }

    private static void deleteTask(ArrayList<Task> list, String message) throws DukeException {
        if (message.trim().equals("delete")) {
            throw new DukeException("OOPS!!! The task number is missing buddy.");
        }
        int index = Integer.parseInt(message.substring(7).trim()) - 1;
        if (index < 0 || index >= list.size()) {
            throw new DukeException("OOPS!!! Task number is invalid buddy.");
        }
        Task task = list.get(index);
        list.remove(index);
        printWithLines("OK, I've deleted this task:", task.toString());
    }

    private static void printWithLines(String... messages) {
        System.out.println("------------------------------------------");
        for (String message : messages) {
            System.out.println(message);
        }
    }

    private static void saveCurrentList(ArrayList<Task> list) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (Task task : list) {
            sb.append(task.toFileString());
            sb.append("\n");
        }
        writeToFile(savePath, sb.toString());
    }

    private static ArrayList<Task> initiateList() throws IOException {
        File file = new File(savePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        Scanner scanner = new Scanner(file);
        ArrayList<Task> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String type = line.substring(0, 1);
            switch (type) {
                case "T":
                    list.add(Task.fromFileString(line));
                    break;
                case "D":
                    list.add(Deadline.fromFileString(line));
                    break;
                case "E":
                    list.add(Event.fromFileString(line));
                    break;
            }
        }
        return list;
    }

}
