package goldbot;

import java.io.IOException;
import java.util.ArrayList;

import goldbot.exceptions.FileCorruptionException;

public class GoldBot {

    private ChatSession session;
    private Storage storage;
    private TaskList taskList;

    GoldBot() {
        storage = new Storage("./data/data.txt");
        try {
            taskList = storage.createTaskList();
        } catch (IOException e) {
            System.out.println("Unexpected error occured.");
            return;
        } catch (FileCorruptionException e) {
            System.out.println("File corrupted.");
            return;
        }

        assert taskList != null : "TaskList should not be null";
        this.session = new ChatSession(taskList);
    }

    public ArrayList<String> getResponse(String input) {
        session.handleMessage(input);
        return session.getResponses();
    }

    public boolean shouldExit() {
        return !session.shouldContinueSession();
    }
}
