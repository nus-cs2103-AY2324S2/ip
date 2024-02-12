package luke;//Returns a list of Strings neatly parsed as arguments.
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Parser {
    //Some strings the parser should look out for
    private final String BYE_COMMAND = "bye";
    private final String LIST_COMMAND = "list";
    private final String DELETE_COMMAND = "delete";
    private final String MARK_COMMAND = "mark";
    private final String FIND_COMMAND = "find";
    private final String QUIT_STRING = "QUIT";
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
     * Parses a command given a string input. The command is executed if possible.
     *
     * @param input String input as given by the user.
     * @return The output of the command, if any.
     */
    public String parseCommand(String input) throws ParseCommandException, TasklistException {
        String returnMessage;
        String trimmedLowercase = input.trim().toLowerCase();
        if (trimmedLowercase.equals(BYE_COMMAND)) {
            storage.saveHistory(saveFile, taskList.getTasks());
            return QUIT_STRING;
        } else if (trimmedLowercase.equals(LIST_COMMAND)) {
            return taskList.listTasks();
        }

        String missingParam = "";
        try {
            if (trimmedLowercase.split(" ")[0].trim().equals(DELETE_COMMAND)) {
                missingParam = DELETE_COMMAND;
                int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
                return taskList.deleteTask(index);
            } else if (trimmedLowercase.split(" ")[0].trim().equals(MARK_COMMAND)) {
                missingParam = MARK_COMMAND;
                int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
                return taskList.markTask(index);
            } else if (trimmedLowercase.split(" ")[0].trim().equals(FIND_COMMAND)) {
                missingParam = FIND_COMMAND;
                String keyword = input.split(" ")[1].strip();
                return taskList.findTask(keyword);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            String missingParameterMessage = UI.getMissingParameterMessage(missingParam);
            throw new ParseCommandException(missingParameterMessage);
        } catch (NumberFormatException e) {
            String nanMessage = UI.getNanMessage();
            throw new ParseCommandException(nanMessage);
        }

        //tasks
        try {
            Task task = TaskMaker.makeTask(input);
            returnMessage = taskList.addTask(task);
        } catch (MakeTaskException e) {
            returnMessage = e.getMessage();
        }

        return returnMessage;
    }
}
