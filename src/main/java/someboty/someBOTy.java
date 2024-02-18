package someboty;

import someboty.exceptions.TerminateException;
import someboty.managers.CommandManager;
import someboty.managers.FileManager;
import someboty.managers.TaskManager;

// main class for the project
public class SomeBoty {

    private CommandManager commandCenter;

    /**
     * Constructor for "someBOTy" class.
     * @param filePath  a file path to access task data.
     */ 
    public SomeBoty(String filePath) {
        FileManager fileManager = new FileManager(filePath);
        TaskManager taskManager = new TaskManager(fileManager);
        commandCenter = new CommandManager(taskManager);
    }

    public String getResponse(String input) {
        String response;

        try {
            response = commandCenter.parse(input);
        } catch (TerminateException e) {
            response = ExitMessage();
        }

        return response;
    }

    /**
     * Prints out a greeting message from the bot to user.
     */
    public static String Greeting() {
        String message = "おかえりなさい、主殿！ イズナはここに待ってたよ\n"
                       + "何かお困りことはありませんか！";

        return message;
    }

    /**
     * Prints out a farewell message from the bot to user.
     */
    public static String ExitMessage() {
        String message = "もう出ていくの？　じゃーいってらっしゃい、主殿！";
        
        return message;
    }
}
