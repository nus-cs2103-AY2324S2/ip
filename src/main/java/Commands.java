import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Commands {

    Storage storage;
    public Commands(String filePath) {
        this.storage = new Storage(filePath);

    }
    public void list(TaskList tasks) {
        Ui.printDivider();
        System.out.print("List of things to do :\n");
        for (int i = 1; i <= tasks.taskList.size(); i++) {
            Task item = tasks.taskList.get(i-1);
            System.out.println("\t" + i + "." + item.toString());
        }
        Ui.printDivider();

    }

    public void unmark(TaskList tasks, int num) {
        if (num < 1 | num > tasks.taskList.size()) {
            System.out.println(Ui.notWithinRange() + tasks.taskList.size());
        } else {
            Task item = tasks.taskList.get(num-1);
            if (item.getStatusIcon().equals("X")) {
                item.markNotDone();
                Ui.printDivider();
                storage.reWriteFile(num);
                System.out.println("Sure Master, I've marked this task as not done :");
                System.out.println(item);
                Ui.printDivider();
                Ui.promptNext();
            } else {
                System.out.println("Oops! Task already NOT done!");
            }
        }
    }
    public void mark(TaskList tasks, int num) {
        if (num < 1 | num > tasks.taskList.size()) {
            System.out.println(Ui.notWithinRange() + tasks.taskList.size());
        } else {
            Task item = tasks.taskList.get(num-1);
            if (item.getStatusIcon().equals(" ")) {
                item.markDone();
                Ui.printDivider();
                storage.reWriteFile(num);
                System.out.println("Sure Master, I've marked this task as done :");
                System.out.println(item);
                Ui.printDivider();
                Ui.promptNext();
            } else {
                System.out.println("Oops! Task already done!");
            }
        }
    }

    public void deadline(TaskList tasks, String input) throws NoTimingException, EmptyDescriptionException, IOException, DateTimeParseException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String deadline = parts[1];
        String[] parts2 = deadline.split("/by");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include deadline!");
        }
        String description = parts2[0].trim();
        String by = parts2[1].trim();

        // Automatically assume that if time is not given, then time is 0000hrs
        int sizeOfBy = by.split(" ").length;
        if (sizeOfBy < 2) by += " 0000";
        // Format the date, time, and create Deadline object, add to list
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTime = LocalDateTime.parse(by, formatter);
        Deadline item_deadline = new Deadline(description, dateTime);
        tasks.taskList.add(item_deadline);

        String stringToSave = "D | " + (item_deadline.getStatusIcon().equals("X") ? "1" : "0") + " | " + description + " | " + by + "\n";
        // May produce IOException
        storage.writeToFile(stringToSave);
        Ui.printDivider();
        Ui.addedTasksMessage();
        System.out.println(item_deadline);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");
        Ui.printDivider();
        Ui.promptNext();
    }

    public void toDo(TaskList tasks, String input) throws EmptyDescriptionException, IOException{
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String toDo = parts[1];
        Todo item_toDo = new Todo(toDo);
        tasks.taskList.add(item_toDo);
        String stringToSave = "T | " + (item_toDo.getStatusIcon().equals("X") ? "1" : "0") + " | " + toDo +"\n";
        // May produce IOException
        storage.writeToFile(stringToSave);
        Ui.printDivider();
        Ui.addedTasksMessage();
        System.out.println(item_toDo);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");
        Ui.printDivider();
    }

    public void event(TaskList tasks, String input) throws EmptyDescriptionException, NoTimingException, IOException, DateTimeParseException {
        String[] parts = input.split(" ",2);
        if (parts.length < 2) {
            throw new EmptyDescriptionException("Description is EMPTY!");
        }
        String description_date = parts[1];
        String[] parts2 = description_date.split("/from ");
        if (parts2.length < 2) {
            throw new NoTimingException("WOI! Please include time!");
        }
        String[] details = parts2[1].split("/to ");
        String description = parts2[0].trim();
        String from = details[0].trim();
        String to = details[1].trim();

        // Automatically assume that if time is not given, then time is 0000hrs
        int sizeOfFrom = from.split(" ").length;
        if (sizeOfFrom < 2) from += " 0000";
        int sizeOfTo = to.split(" ").length;
        if (sizeOfTo < 2) to += " 0000";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        LocalDateTime dateTimeFrom = LocalDateTime.parse(from, formatter);
        LocalDateTime dateTimeTo = LocalDateTime.parse(to, formatter);
        Event item_event = new Event(description, dateTimeFrom, dateTimeTo);
        tasks.taskList.add(item_event);
        String stringToSave = "E | " + (item_event.getStatusIcon().equals("X") ? "1" : "0") + " | " + description + " | " + from + " | " + to + "\n";
        // May produce IOException
        storage.writeToFile(stringToSave);
        Ui.printDivider();
        Ui.addedTasksMessage();
        System.out.println(item_event);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");
        Ui.printDivider();
    }

    public void delete(TaskList tasks, String input) throws EmptyDescriptionException, IOException {
        String[] parts = input.split(" ");
        if (parts.length < 2) {
            throw new EmptyDescriptionException("You did not specify an number!");
        } else if (parts.length > 2) {
            System.err.println("Provided too many arguments!");
        }

        if (Integer.parseInt(parts[1]) > tasks.taskList.size() | Integer.parseInt(parts[1]) < 1) {
            throw new IndexOutOfBoundsException();
        }

        Ui.printDivider();
        System.out.println("Noted Master. I've removed this task:");
        System.out.println(tasks.taskList.get(Integer.parseInt(parts[1]) - 1).toString());
        tasks.taskList.remove(Integer.parseInt(parts[1]) - 1);
        storage.removeFromFile(Integer.parseInt(parts[1]) - 1);
        System.out.println("Now you have " + tasks.taskList.size() + " tasks in the list.");
        Ui.printDivider();
    }
}
