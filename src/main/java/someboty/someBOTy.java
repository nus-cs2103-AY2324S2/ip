package someboty;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import someboty.Exceptions.TerminateException;
import someboty.Managers.CommandManager;
import someboty.Managers.FileManager;
import someboty.Managers.TaskManager;

// main class for the project
public class someBOTy {

    private CommandManager commandCenter;

    /**
     * Constructor for "someBOTy" class.
     * @param filePath  a file path to access task data.
     */ 
    public someBOTy(String filePath) {
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
