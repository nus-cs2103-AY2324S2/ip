import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;

public class Duke {

    private static FileIO fileIO = new FileIO();
    private List<Task> l = fileIO.readFromFile();
    private static final File FILE_PATH = new File("./data/logfile.txt");
    //protected List<Task> list = fileIO.readFromFile();

    public Duke() throws IOException {
        //l = new ArrayList<>();
        l = fileIO.readFromFile();
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Hello! I'm Lucifer\nWhat can I do for you?");
        System.out.println("______________________________________________________");
        Scanner sc = new Scanner(System.in);
        Duke lucifer = new Duke();
        String user_word;

        while (true) {
            user_word = sc.nextLine();
            try {
                if (user_word.equals("bye")) {
                    break;
                }
                if (user_word.equals("list")) {
                    lucifer.listTasks();
                    //fileIO.readFromFile();
                } else if (user_word.contains("unmark")) {
                    int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
                    lucifer.unmarked_task(element_index);
                    //fileIO.saveToFile(lucifer.list);
                } else if (user_word.contains("mark")) {
                    int element_index = Integer.parseInt(user_word.split(" ")[1]) - 1;
                    lucifer.markTask(element_index);
                    //fileIO.saveToFile(lucifer.list);
                } else if (user_word.equals("deadline")) {
                    System.out.println("______________________________________________________");
                    //fileIO.saveToFile(lucifer.list);
                    throw new DukeException("ERROR!! Please give the description of deadline.\n______________________________________________________");
                } else if (user_word.contains("deadline")) {
                    String[] array_split = user_word.split("/by ");
                    String description = array_split[0].substring("deadline".length()).trim();
                    LocalDateTime dateTime = parseDateTime(array_split[1].trim());
                    Deadline deadline = new Deadline(description, dateTime);
                    //Deadline deadline = new Deadline(array_split[0].substring(9), LocalDateTime.parse(array_split[1]));
                    lucifer.addDeadlineTask(deadline);
                    //fileIO.saveToFile(lucifer.list);
                } else if (user_word.equals("todo")) {
                    System.out.println("______________________________________________________");
                    //fileIO.saveToFile(lucifer.list);
                    throw new DukeException("ERROR!! Please give the description of todo.\n______________________________________________________");
                } else if (user_word.contains("todo")) {
                    ToDo todo = new ToDo(user_word.substring(5));
                    //fileIO.saveToFile(lucifer.list);
                    lucifer.addTodoTask(todo);
                } else if (user_word.equals("event")) {
                    System.out.println("______________________________________________________");
                    String[] parts = user_word.split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new DukeException("ERROR!! Please provide both start and end time for the event.\n______________________________________________________");
                    }
                    String description = parts[0].substring("event".length()).trim();
                    LocalDate startTime = parseDate(parts[1].trim());
                    LocalDate endTime = parseDate(parts[2].trim());

                    if (startTime != null && endTime != null) {
                        Event temp_event = new Event(description, startTime, endTime);
                        lucifer.addEventTask(temp_event);
                        //fileIO.saveToFile(lucifer.list); // Uncomment this if you want to save each change
                    } else {
                        System.out.println("Invalid format for event times.");
                    }
                    //lucifer.addEventTask(temp_event);
                    //fileIO.saveToFile(lucifer.list);
                    throw new DukeException("ERROR!! Please give the description of event.\n______________________________________________________");
                } else if (user_word.contains("event")) {
                    String[] event = user_word.split("/from | /to ");
                    Event temp_event = new Event(event[0].substring(6),
                            LocalDate.parse(event[1]), LocalDate.parse(event[2]));
                    lucifer.addEventTask(temp_event);
                    //fileIO.saveToFile(lucifer.list);
                } else if (user_word.contains("delete")) {
                    int deleted_index = Integer.parseInt(user_word.split(" ")[1]);
                    lucifer.deleteTask(deleted_index);
                    //fileIO.saveToFile(lucifer.list);
                } else {
                    //lucifer.addTask(user_word);
                    System.out.println("______________________________________________________");
                    throw new DukeException("ERROR!! I can't understand what you mean by that\n______________________________________________________");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");
    }

    public void addTask(String user_word) {
        Task newTask = new Task(user_word);
        l.add(newTask);
        System.out.println("added: " + user_word);
        System.out.println("______________________________________________________");
    }

    //    private static LocalDateTime parseDateTime(String dateTimeStr) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
//        try {
//            return LocalDateTime.parse(dateTimeStr, formatter);
//        }
//        catch (DateTimeParseException e) {
//            System.out.println("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
//            return null;
//        }
//    }
    private static LocalDate parseDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd, e.g., 2023-03-15");
            return null;
        }
    }
    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use d/M/yyyy HHmm, e.g., 2/12/2019 1800");
            return null;
        }
    }

    public void listTasks() throws IOException {
        fileIO.readFromFile();
        System.out.println("______________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int x = 0; x < l.size(); x++) {
            System.out.println(x + 1 + ". " + l.get(x));
        }
        System.out.println("______________________________________________________");
    }

    public void markTask(int element_index) throws IOException {
        System.out.println("______________________________________________________");
        if (element_index >= 0 && element_index < l.size()) {
            Task task = l.get(element_index);
            task.markDone();
            System.out.println("Nice! I've marked this task as done:");
            fileIO.saveToFile(l);
            System.out.println(l.get(element_index));
        }
        System.out.println("______________________________________________________");
    }

    public void unmarked_task(int element_index) throws IOException {
        System.out.println("______________________________________________________");
        if (element_index >= 0 && element_index < l.size()) {
            Task task = l.get(element_index);
            task.mark_not_done();
            System.out.println("OK, I've marked this task as not done yet:");
            fileIO.saveToFile(l);
            System.out.println(l.get(element_index));
        }
        System.out.println("______________________________________________________");
    }

    public void addDeadlineTask(Task task) throws IOException {
        System.out.println("______________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        l.add(task);
        fileIO.saveToFile(l);
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }
        System.out.println("______________________________________________________");
    }

    public void addTodoTask(Task task) throws IOException {
        System.out.println("______________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        l.add(task);
        fileIO.saveToFile(l);
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }
        System.out.println("______________________________________________________");
    }

    public void addEventTask(Task task) throws IOException {
        System.out.println("______________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        l.add(task);
        fileIO.saveToFile(l);
        if (l.size() == 1) {
            System.out.println("Now you have " + l.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + l.size() + " tasks in the list.");
        }
        System.out.println("______________________________________________________");
    }

    public void deleteTask(int deleted_index) throws IOException {
        System.out.println("______________________________________________________");
        int actual_index = deleted_index - 1;
        Task removed_task = l.remove(actual_index);
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removed_task);
        System.out.println("Now you have " + l.size() + " task in the list.");
        System.out.println("______________________________________________________");
        fileIO.saveToFile(l);
    }
}

