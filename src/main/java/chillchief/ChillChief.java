package chillchief;

import util.Parser;
import util.Storage;
import util.TaskList;
import util.TextUi;

import exceptions.ChillChiefException;
import java.io.IOException;
import java.util.Scanner;

/**
 * ChillChief is a task management application.
 * Users can add, mark, unmark, delete, find, various tasks such as todos, deadlines and events.
 */
public class ChillChief {

    private Storage storage = new Storage("./data/chillchief.txt");
    private TaskList taskList;
    private TextUi textUi = new TextUi();

    public ChillChief() {
        try {
            taskList = new TaskList(storage.load());
        } catch (IOException e) {
            textUi.showErrorMessage("Error while loading!");
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            return Parser.parseInput(input, taskList, textUi, storage);
        } catch (Exception e) {
            return textUi.showErrorMessage("Could not get response from ChillChief");
        }
    }
}
