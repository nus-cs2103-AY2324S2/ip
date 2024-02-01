package duke;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

import duke.utility.DukeException;
import duke.utility.Parser;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

import duke.command.Command;

import duke.task.Task;
public class Duke {
    /** TaskList Object to be used to store Tasks. */
    private TaskList taskList;
    /** Ui Object for User Interactions. */
    private Ui userInterface;
    /** Storage Object to store and load Tasklist states. */
    private Storage fileStorage;

    /**
     * Constucts a Duke Object that will be loaded with existing TaskList state.
     *
     * @param FilePath FilePath of file to be used to load TaskList stored.
     * @throws DukeException
     * @throws IOException
     */
    public Duke(String FilePath) throws DukeException,IOException{
        this.userInterface = new Ui();
        this.fileStorage = new Storage(FilePath);
    }

    /**
     * Runs the Duke Chatbot.
     * 
     * @throws DukeException
     * @throws IOException
     */
    public void run() throws DukeException,IOException {
        userInterface.showWelcome();
        Scanner s = new Scanner(System.in);
        if (this.fileStorage.isOccupied){
            ArrayList<Task> loadedList = fileStorage.loadStorage();
            taskList = new TaskList(loadedList);
        } else {
            taskList = new TaskList();
        }
        boolean isExit = false;
        while(!isExit){
            try {
                Command c = Parser.parseInstructions(s.nextLine());
                c.execute(taskList, userInterface, fileStorage);
                isExit = c.isExit();
            } catch (DukeException e) {
                userInterface.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./data/tasks.txt").run();
    }
}
