package jux;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;


/**
 * main chatbot class
 */
public class Jux{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    /**
     * No arg constructor for application instance
     */
    public Jux() {

    }
    public Jux(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }
    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    @FXML
    public String getResponse(String input) {
        String output = "";
        try {
            if (Parser.isExit(input)) {
                output = ui.showGoodbye();
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
        return ui.printList(tasks.getTaskList());
    }
    /**
     * Runs the program when user runs the file
     */
//    public void run() {
//
//        if (!tasks.isEmpty()) {
//            ui.printList(tasks.getTaskList());
//        }
//        ui.showWelcome();
//            while(true) {
//            try {
//                String input = ui.takeInput();
//                if (Parser.isExit(input)) {
//                    ui.showGoodbye();
//                    break;
//                }
//                Parser.parsingInput(input, tasks, ui);
//                storage.saveFile(tasks.getTaskList());
//                } catch (JuxException e) {
//                System.err.println(e.getMessage());
//            }
//
//
//        }
//
//
//
//    }
//
//    public static void main(String[] args) {
//        new Jux("data/Jux.txt").run();
//    }
}

