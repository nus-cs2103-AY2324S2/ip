package duke;

import java.io.IOException;
import java.util.ArrayList;
import duke.exceptions.FileCorruptionException;

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

        this.session = new ChatSession(taskList);
    }

    public ArrayList<String> getResponse(String input) {
        session.handleMessage(input);
        return session.getResponses();
    }
}
