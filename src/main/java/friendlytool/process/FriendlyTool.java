package friendlytool.process;

import friendlytool.command.CommandTypes;
import friendlytool.command.Parser;

/**
 * Main class for this app.
 */
public class FriendlyTool {
    private boolean isActive;
    private TaskList tasks;

    /**
     * Constructs FriendlyTool.
     *
     * @param isActive indicates whether program is active or not
     */
    public FriendlyTool(Boolean isActive) {
        this.isActive = isActive;
        this.tasks = new TaskList();
        try {
            Storage.loadTask(tasks);
        } catch (FtException e) {
            System.out.println(e.getMessage());
        }
    }

    public FriendlyTool() {
        this(true);
    }

    /**
     * Decides the next action based on the input.
     *
     * @param input user input.
     * @throws FtException
     */
    private String findNextAction(String input) throws FtException {
        String response;
        if (input.isEmpty()) {
            throw new FtException("Error: Please Type Command");
        }
        try {
            CommandTypes command = Parser.parseType(input);
            assert command != null : "Command should not be null";
            switch (command) {
            case BYE:
                this.isActive = false;
                response = UI.getByeMsg();
                System.exit(0);
                break;
            case LIST:
                response = UI.getListMsg(tasks);
                break;
            case MARK:
                response = tasks.mark(input);
                Storage.updateTask(tasks);
                break;
            case UNMARK:
                response = tasks.unmark(input);
                Storage.updateTask(tasks);
                break;
            case TODO:
                response = tasks.addTask(input, CommandTypes.TODO);
                Storage.updateTask(tasks);
                break;
            case DEADLINE:
                response = tasks.addTask(input, CommandTypes.DEADLINE);
                Storage.updateTask(tasks);
                break;
            case EVENT:
                response = tasks.addTask(input, CommandTypes.EVENT);
                Storage.updateTask(tasks);
                break;
            case DELETE:
                response = tasks.deleteTask(input);
                Storage.updateTask(tasks);
                break;
            case FIND:
                response = TaskFinder.findTask(tasks, input);
                break;
            case SORT:
                response = tasks.sortTask();
                Storage.updateTask(tasks);
                break;
            default:
                throw new FtException("Unknown Command: Please use a correct command");
            }
            assert response != null : "Response should not be null";
            return response;
        } catch (IllegalArgumentException e) {
            throw new FtException("Unknown Command: Please use a correct command");
        }
    }

    /**
     * @param input user input
     * @return response from the chatbot.
     */
    public String getResponse(String input) {
        try {
            return findNextAction(input);
        } catch (FtException e) {
            return UI.getErrorMsg(e);
        }
    }
}

