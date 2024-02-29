package LeBron;

import actions.Parser;
import actions.Ui;
import actions.TaskList;
import actions.DataManager;
;
public class LeBron {

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
    public LeBron() {
        this.taskList = new TaskList(dataManager.retrieveTasks());
    }

    public String getResponse(String input) {
        try {
            Parser parser = new Parser(input, taskList, ui);
            String response =  parser.parse(input);
            dataManager.saveTasks(taskList.getTasks());
            return response;
        } catch (LeBronException e) {
            return e.getMessage();
        }
    }
}