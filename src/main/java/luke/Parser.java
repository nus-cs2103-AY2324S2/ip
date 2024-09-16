package luke;
import java.io.File;
import java.util.ArrayList;

public class Parser {
    //Some strings the parser should look out for
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
     * Idea to separate original code into different sections provided by ChatGPT with my modification and corrections.
     * I gave it my original code. See https://chat.openai.com/share/e4d5e892-5d06-4f57-906c-7ca4e23ff272
     *
     * @param input String input as given by the user.
     * @return The output of the command, if any.
     */
    public String parseCommand(String input) throws ParseCommandException, TasklistException {
        String returnMessage;
        String trimmedLowercase = input.trim().toLowerCase();
        for (Command command : Command.values()) {
            if (command.toString().equals(trimmedLowercase.split(" ")[0].trim())) {
                return handleCommand(command, input);
            }
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

    /**
     * Interprets an input using the given command.
     * Returns the result of executing the command as a string.
     *
     * @param command The type of command
     * @param input String input as given by the user
     * @return The result of executing the command.
     * @throws ParseCommandException
     * @throws TasklistException
     */
    private String handleCommand(Command command, String input) throws ParseCommandException, TasklistException {
        switch (command) {
            case BYE:
                storage.saveHistory(saveFile, taskList.getTasks());
                return QUIT_STRING;
            case LIST:
                return taskList.listTasks();
            case REMIND:
                return taskList.remind();
            case DELETE:
                return handleDeleteCommand(input);
            case MARK:
                return handleMarkCommand(input);
            case FIND:
                return handleFindCommand(input);
            default:
                throw new ParseCommandException(UI.getCommandNotFoundMessage());
        }
    }

    /**
     * Helper function for handleCommand.
     * In specific, handles a find command.
     *
     * @param input
     * @return The result of executing the command.
     * @throws TasklistException
     * @throws ParseCommandException
     */
    private String handleFindCommand(String input) throws TasklistException, ParseCommandException {
        try {
            String keyword = input.split(" ")[1].strip();
            return taskList.findTask(keyword);
        } catch (NumberFormatException e) {
            String nanMessage = UI.getNanMessage();
            throw new ParseCommandException(nanMessage);
        } catch (ArrayIndexOutOfBoundsException e) {
            String missingParametersMessage = UI.getMissingParameterMessage(Command.DELETE.toString());
            throw new ParseCommandException(missingParametersMessage);
        }
    }

    /**
     * Helper function for handleCommand.
     * In specific, handles a mark command.
     *
     * @param input
     * @return The result of executing the command.
     * @throws TasklistException
     * @throws ParseCommandException
     */
    private String handleMarkCommand(String input) throws TasklistException, ParseCommandException {
        try {
            int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
            return taskList.markTask(index);
        } catch (NumberFormatException e) {
            String nanMessage = UI.getNanMessage();
            throw new ParseCommandException(nanMessage);
        } catch (ArrayIndexOutOfBoundsException e) {
            String missingParametersMessage = UI.getMissingParameterMessage(Command.DELETE.toString());
            throw new ParseCommandException(missingParametersMessage);
        }
    }

    /**
     * Helper function for handleCommand.
     * In specific, handles a delete command.
     *
     * @param input
     * @return The result of executing the command.
     * @throws TasklistException
     * @throws ParseCommandException
     */
    private String handleDeleteCommand(String input) throws TasklistException, ParseCommandException {
        try {
            int index = Integer.parseInt(input.split(" ")[1].strip()) - 1;
            return taskList.deleteTask(index);
        } catch (NumberFormatException e) {
            String nanMessage = UI.getNanMessage();
            throw new ParseCommandException(nanMessage);
        } catch (ArrayIndexOutOfBoundsException e) {
            String missingParametersMessage = UI.getMissingParameterMessage(Command.DELETE.toString());
            throw new ParseCommandException(missingParametersMessage);
        }
    }

}
