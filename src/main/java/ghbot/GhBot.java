package ghbot;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import ghbot.executor.ByeExecutor;
import ghbot.executor.Executor;

/**
 * GhBot Class.
 * This is the main class for the chatbot.
 */
public class GhBot {

    public static void main(String[] args) {
        String fileName = "./data/history.txt";
        Storage storage = new Storage(fileName);
        TaskList taskLst = new TaskList(storage.getInputFromFile());
        Ui ui = new Ui();

        while (true) {
            try {
                String[] subStr = ui.validateInput();
                Executor executor = Parser.parse(subStr);
                if (executor != null) {
                    executor.set(taskLst);
                    executor.execute();
                }
                if (executor instanceof ByeExecutor) {
                    break;
                }
                storage.writeDataToFile(taskLst.toList());
            } catch (GhBotException | IOException | DateTimeParseException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
