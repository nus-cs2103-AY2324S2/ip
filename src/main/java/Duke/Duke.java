package Duke;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

import actions.Parser;
import actions.Ui;
import actions.TaskList;
import actions.DataManager;
;
public class Duke {

    /**
     * Duke is a command line interface application that helps to keep track of tasks
     */

    private static final String FILE_PATH = "./data/duke.txt";
    private TaskList taskList;
    private Ui ui = new Ui();
    private DataManager dataManager = new DataManager(FILE_PATH);

    /**
     * instantiates a duke object
     * with TaskList, Ui and DataManager objects
     */
    public Duke() {
        this.taskList = new TaskList(dataManager.retrieveTasks());
    }

    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input, taskList, ui);
            return parser.parse(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}