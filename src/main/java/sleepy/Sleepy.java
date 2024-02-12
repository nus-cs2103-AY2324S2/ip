package sleepy;

import sleepy.taskstorage.TaskList;
import sleepy.tools.Parser;
import sleepy.tools.ResponseHandler;

/**
 * This class is the main class for the Sleepy AI Chatbot.
 *
 * @author kjw142857
 */
public class Sleepy {
    public static final String WELCOME_TEXT = "Hello! I'm Sleepy \n Zzz...";
    private TaskList taskList;

    private boolean isInExitState;

    /**
     * Constructor for the Sleepy class.
     */
    public Sleepy() {
        String filePath = "./storage/HardDiskStorage.txt";
        // Retrieve saved data
        taskList = new TaskList(filePath);
        isInExitState = false;
    }

    /**
     * Gets the response from Sleepy depending on the user command.
     *
     * @param input Input by the user.
     * @return Response from Sleepy.
     */
    public String getResponse(String input) {
        try {
            String[] parsedCommand = Parser.parse(input);
            if (parsedCommand[0].equals("bye")) {
                isInExitState = true;
            } else {
                taskList.access(parsedCommand);
            }
        } catch (NumberFormatException n) {
            ResponseHandler.appendError("Zzz... The target task number must be an integer!");
        } catch (IllegalArgumentException i) {
            ResponseHandler.appendError(i.getMessage());
        }
        return ResponseHandler.returnResponse();
    }

    /**
     * Shows whether Sleepy is about to exit.
     *
     * @return Exit state of Sleepy.
     */
    public boolean isInExitState() {
        return this.isInExitState;
    }
}
