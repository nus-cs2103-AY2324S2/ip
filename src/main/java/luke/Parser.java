package luke;//Returns a list of Strings neatly parsed as arguments.
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    private File saveFile;
    private TaskList taskList;
    private Storage storage;

    /**
     * Creates a parser with the specified Storage and save file.
     *
     * @param storage Serializable which represents the save data.
     * @param saveFile File object used to save/load.
     */
    public Parser(Storage storage, File saveFile) {
        this.storage = storage;
        ArrayList<Task> tasks = storage.getHistory();
        this.taskList = new TaskList(tasks);
        this.saveFile = saveFile;
    }

    /**
     * Parses a command given a string input. Returns false if there is a next command, else true.
     *
     * @param input String input as given by the user.
     * @return False if there is a next command, else true.
     */
    public boolean parseCommand(String input) {
        String trimmedLowercase = input.trim().toLowerCase();
        if (trimmedLowercase.equals("bye")) {
            storage.saveHistory(saveFile, taskList.getTasks());
            return true;
        } else if (trimmedLowercase.equals("list")) {
            taskList.listTasks();
            return false;
        } else if (trimmedLowercase.split(" ")[0].trim().equals("delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
                taskList.deleteTask(index);
                return false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Hey! You forgot something! Be glad I'm here to remind you.");
                System.out.println("[Missing delete parameter(s)]\n");
                return false;
            }
        } else if (trimmedLowercase.split(" ")[0].trim().equals("mark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
                taskList.markTask(index);
                return false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Hey! You forgot something! Be glad I'm here to remind you.");
                System.out.println("[Missing mark parameter(s)]\n");
                return false;
            }
        } else if (trimmedLowercase.split(" ")[0].trim().equals("find")) {
            try {
                String keyword = input.split(" ")[1].strip();
                taskList.findTask(keyword);
                return false;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Hey! You forgot something! Be glad I'm here to remind you.");
                System.out.println("[Missing search phrase]\n");
                return false;
            }
        }

        //tasks
        Task task = makeTask(input);

        if (task != null) {
            taskList.addTask(task);
            return false;
        }

        return false;
    }

    /**
     * Returns a Task given a string input by the user.
     *
     * @param input String input given by user
     * @return The task if the input is valid, else null.
     */
    protected static Task makeTask(String input) {
        Task task;
        String trimmedLowercase = input.trim().toLowerCase();
        String taskType = trimmedLowercase.split(" ")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String todoString = TaskType.TODO.toString();
        String deadlineString = TaskType.DEADLINE.toString();
        String eventString = TaskType.EVENT.toString();

        if (taskType.equals(todoString)) {
            task = new Todo(trimmedLowercase.substring(todoString.length()).trim());
            if (input.split(" ").length < 2) {
                System.out.println("You have eyes for a reason, don't you?");
                System.out.println("[Missing todo description]\n");
                return null;
            }
        } else if (taskType.equals(deadlineString)) {
            try {
                String taskName = input.split("/")[0].trim().substring(deadlineString.length()).trim();
                LocalDate by = LocalDate.parse(input.split("/")[1].replace("by", "").trim(), formatter);
                task = new Deadline(taskName, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Hey! You forgot something! Be glad I'm here to remind you.");
                System.out.println("[Missing deadline parameter(s)]\n");
                return null;
            } catch (DateTimeParseException e) {
                System.out.println("Hey! What are you even talking about?!");
                System.out.println("[Input date in format dd-mm-yyyy]\n");
                return null;
            }
        } else if (taskType.equals(eventString)) {
            try {
                String taskName = input.split("/")[0].trim().substring(eventString.length()).trim();
                LocalDate start = LocalDate.parse(input.split("/")[1].replace("from", "").trim(), formatter);
                LocalDate end = LocalDate.parse(input.split("/")[2].replace("to", "").trim(), formatter);
                task = new Event(taskName, start, end);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("/// I don't know when you are free... ///");
                System.out.println("[Missing event parameter(s)]\n");
                return null;
            } catch (DateTimeParseException e) {
                System.out.println("Hey! What are you even talking about?!");
                System.out.println("[Input date in format dd-mm-yyyy]\n");
                return null;
            }
        } else {
            System.out.println("/// What on earth are you saying! ///");
            System.out.println("[Command not found]\n");
            return null;
        }
        return task;
    }
}
