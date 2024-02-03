package botbot;

import botbot.exception.BotBotException;
import botbot.task.TaskList;

import java.io.IOException;

public class BotBot {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    public static String LIST_PATH = "data/list.txt";

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
     * Executes the User input loop of the bot
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
