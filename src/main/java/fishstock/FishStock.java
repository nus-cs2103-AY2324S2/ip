package fishstock;

import java.io.IOException;

import javafx.application.Application;

/**
 * Encapsulates a FishStock object.
 */
public class FishStock {
    private Storage storage;
    private TaskList list;

    /**
     * Initializes a FishStock object.
     */
    public FishStock() {
        storage = new Storage("./data/tasks.txt");

        try {
            list = new TaskList(storage.load());
        } catch (FishStockException e) {
            e.printStackTrace();
            list = new TaskList();
        }
    }

    /**
     * The response to user input.
     * @param inputStr The user input.
     */
    public String getResponse(String inputStr) {
        UserInput input = new UserInput(inputStr);
        String response = Logic.runCommand(list, input);
        return response;
    }

    /**
     * Exits program while saving list.
     */
    public void exit() {
        try {
            storage.close(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Entrypoint to FishStock.
     * @param args ignored
     */
    public static void main(String[] args) {
        Application.launch(fishstock.ui.Main.class, args);
    }
}
