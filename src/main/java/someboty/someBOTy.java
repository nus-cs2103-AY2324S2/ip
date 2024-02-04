package someboty;

import someboty.Exceptions.TerminateException;
import someboty.Managers.responseManager;
import someboty.Managers.taskManager;

// main class for the project
public class someBOTy {

    public someBOTy(String filePath) {
        // Initialize
        responseManager response = new responseManager(new taskManager(filePath));

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
        String filePath = System.getProperty("user.dir") + "/data/tasks.csv";
        new someBOTy(filePath);
    }
}
