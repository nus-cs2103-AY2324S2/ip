package someboty;

import someboty.Exceptions.TerminateException;
import someboty.Managers.commandManager;
import someboty.Managers.fileManager;
import someboty.Managers.responseManager;
import someboty.Managers.taskManager;

// main class for the project
public class someBOTy {

    public someBOTy(String filePath) {
        // Initialize
        fileManager files = new fileManager(filePath);
        taskManager tasks = new taskManager(files);
        commandManager commandCenter = new commandManager(tasks);
        responseManager response = new responseManager(commandCenter);

        responseManager.printGreeting();

        while (true) {
            try {
                response.parseInput(); // read input once
            } catch (TerminateException e) { // only thrown if command is "bye"
                responseManager.printExitMessage();
                return;
            }
        }
    }

    public static void main(String[] args) { 
        String filePath = System.getProperty("user.dir");
        new someBOTy(filePath);
    }
}
