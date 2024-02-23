package someboty;

import someboty.exceptions.TerminateException;
import someboty.managers.CommandManager;
import someboty.managers.ExpenseManager;
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
        ExpenseManager expenseManager = new ExpenseManager(fileManager);
        commandCenter = new CommandManager(taskManager, expenseManager);
    }

    public String getResponse(String input) {
        try {
            String response = commandCenter.parse(input);
            return response;

        } catch (TerminateException e) { 
            // re-wrap exception with a farewell message.
            throw new TerminateException(ExitMessage());
        }
        
    }

    /**
     * Prints out a greeting message from the bot to user.
     */
    public static String Greeting() {
        String message = "おかえりなさい、主殿！ イズナはここにお待ちしておりましたよ！"
                       + "何かお困りことはありませんか！";

        return message;
    }

    /**
     * Prints out a farewell message from the bot to user.
     */
    public static String ExitMessage() {
        String message = "もう出ていくの？　じゃーいってらっしゃい、主殿！\n\n"
                       + "*Press enter again to close the window.*";
        
        return message;
    }
}
