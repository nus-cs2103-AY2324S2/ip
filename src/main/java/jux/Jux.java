package jux;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;


/**
 * main chatbot class that contains the logic for the chatbot
 */
public class Jux{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isNewStart = true;

    public Jux(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            isNewStart = tasks.isEmpty();

        } catch (FileNotFoundException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * Response to user Input
     * @param input user input
     * @return  the response
     */
    @FXML
    public String getResponse(String input) {
        String output = "";
        try {
            if (Parser.isExit(input)) {
                System.exit(0);
            } else {
                output = Parser.parsingInput(input, tasks, ui);
                storage.saveFile(tasks.getTaskList());
            }
        } catch(JuxException e) {
            return (e.getMessage());
        }
        return output;
    }
    @FXML
    public String getStorageList() {
        return tasks.showListWithIndexing(ui);
    }

    /**
     * Returns whether the chatbot's history is empty or not
     * @return boolean value of chatbot's history
     */
    public boolean isNewStart() {
        return this.isNewStart;
    }
}

