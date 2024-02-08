package sleepy;

import sleepy.taskstorage.TaskList;
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
        String filePath = "./src/main/java/sleepy/taskstorage/HardDiskStorage.txt";
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
        String nextUserCommand = input.toLowerCase();
        if (nextUserCommand.equals("bye")) {
            isInExitState = true;
            return "Bye. Gonna go back to sleep now *yawn*";
        }
        taskList.access(nextUserCommand);
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
