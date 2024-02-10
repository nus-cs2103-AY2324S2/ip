package botbot;

import java.io.IOException;

import botbot.ui.Ui;
import botbot.exception.BotBotException;
import botbot.task.TaskList;


public class BotBot  {
    public static final String LIST_PATH = "data/list.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Returns a new instance of chatbot
     * @throws BotBotException
     * @throws IOException
     */
    public BotBot() throws BotBotException, IOException {
        ui = new Ui();
        storage = new Storage(BotBot.LIST_PATH);
        taskList = new TaskList();
        storage.load(taskList);
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) throws BotBotException, IOException {
        return ui.getResponseAndExecute(input, taskList, storage);
    }

    /**
     * Executes the User input loop of the bot
     * !!OBSOLETE - FOR RUNNING ON COMMANDLINE
     * @throws IOException
     * @throws BotBotException
     */
    public void run() throws IOException, BotBotException {
        BotBot bot = new BotBot();
        bot.ui.run(bot.taskList, bot.storage);
        bot.storage.save(bot.taskList);
    }

    public static void main(String[] args) throws BotBotException, IOException {
        new BotBot().run();
    }
}
