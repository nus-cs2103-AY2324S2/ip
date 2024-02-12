package luke;//Returns a list of Strings neatly parsed as arguments.
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    public String QUIT_STRING = "QUIT";
    private File saveFile;
    private TaskList taskList;
    private Storage storage;
    boolean isLastCommand = false;

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
     * @return The output of the command, if any.
     */
    public String parseCommand(String input) throws ParseCommandException, TasklistException {
        String returnMessage;
        String errorMessage;
        String trimmedLowercase = input.trim().toLowerCase();
        if (trimmedLowercase.equals("bye")) {
            storage.saveHistory(saveFile, taskList.getTasks());
            return QUIT_STRING;
        } else if (trimmedLowercase.equals("list")) {
            return taskList.listTasks();
        } else if (trimmedLowercase.split(" ")[0].trim().equals("delete")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
                return taskList.deleteTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                errorMessage = "Hey! You forgot something! Be glad I'm here to remind you.\n"
                        + "[Missing delete parameter(s)]\n";
                throw new ParseCommandException(errorMessage);
            } catch (NumberFormatException e) {
                errorMessage = "Not a number";
                throw new ParseCommandException(errorMessage);
            }
        } else if (trimmedLowercase.split(" ")[0].trim().equals("mark")) {
            try {
                int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
                return taskList.markTask(index);
            } catch (ArrayIndexOutOfBoundsException e) {
                errorMessage = "Hey! You forgot something! Be glad I'm here to remind you.\n"
                        + "[Missing mark parameter(s)]\n";
                throw new ParseCommandException(errorMessage);
            } catch (NumberFormatException e) {
                errorMessage = "Not a number";
                throw new ParseCommandException(errorMessage);
            }
        } else if (trimmedLowercase.split(" ")[0].trim().equals("find")) {
            try {
                String keyword = input.split(" ")[1].strip();
                return taskList.findTask(keyword);
            } catch (ArrayIndexOutOfBoundsException e) {
                errorMessage = "Hey! You forgot something! Be glad I'm here to remind you.\n"
                        + "[Missing search phrase]\n";
                throw new ParseCommandException(errorMessage);
            } catch (NumberFormatException e) {
                errorMessage = "Not a number";
                throw new ParseCommandException(errorMessage);
            }
        }

        //tasks
        try {
            Task task = makeTask(input);
            returnMessage = taskList.addTask(task);
        } catch (MakeTaskException e) {
            returnMessage = e.getMessage();
        }

        return returnMessage;
    }

    /**
     * Returns a Task given a string input by the user.
     *
     * @param input String input given by user
     * @return The task if the input is valid, else null.
     * @throws MakeTaskException
     */
    protected Task makeTask(String input) throws MakeTaskException {
        Task task;
        String trimmedLowercase = input.trim().toLowerCase();
        String taskType = trimmedLowercase.split(" ")[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        String todoString = TaskType.TODO.toString();
        String deadlineString = TaskType.DEADLINE.toString();
        String eventString = TaskType.EVENT.toString();
        String errorMessage;

        if (taskType.equals(todoString)) {
            task = new Todo(trimmedLowercase.substring(todoString.length()).trim());
            if (input.split(" ").length < 2) {
                 errorMessage = "You have eyes for a reason, don't you?\n" +
                        "[Missing todo description]\n";
                throw new MakeTaskException(errorMessage);
            }
        } else if (taskType.equals(deadlineString)) {
            try {
                String taskName = input.split("/")[0].trim().substring(deadlineString.length()).trim();
                LocalDate by = LocalDate.parse(input.split("/")[1].replace("by", "").trim(), formatter);
                task = new Deadline(taskName, by);
            } catch (ArrayIndexOutOfBoundsException e) {
                errorMessage = "Hey! You forgot something! Be glad I'm here to remind you.\n"
                        + "[Missing deadline parameter(s)]\n";
                throw new MakeTaskException(errorMessage);
            } catch (DateTimeParseException e) {
                errorMessage = "Hey! What are you even talking about?!\n"
                        + "[Input date in format dd-mm-yyyy]\n";
                throw new MakeTaskException(errorMessage);
            }
        } else if (taskType.equals(eventString)) {
            try {
                String taskName = input.split("/")[0].trim().substring(eventString.length()).trim();
                LocalDate start = LocalDate.parse(input.split("/")[1].replace("from", "").trim(), formatter);
                LocalDate end = LocalDate.parse(input.split("/")[2].replace("to", "").trim(), formatter);
                task = new Event(taskName, start, end);
            } catch (IndexOutOfBoundsException e) {
                errorMessage = "/// I don't know when you are free... ///\n"
                        + "[Missing event parameter(s)]\n";
                throw new MakeTaskException(errorMessage);
            } catch (DateTimeParseException e) {
                errorMessage = "Hey! What are you even talking about?!\n"
                        + "[Input date in format dd-mm-yyyy]\n";
                throw new MakeTaskException(errorMessage);
            }
        } else {
            errorMessage = "/// What on earth are you saying! ///\n"
                    + "[Command not found]\n";
            throw new MakeTaskException(errorMessage);
        }
        return task;
    }
}
