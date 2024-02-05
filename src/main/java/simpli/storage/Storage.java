package simpli.storage;

import simpli.exceptions.ActionException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.tasks.Task;
import simpli.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles the saving and loading of tasks to and from the filesystem.
 */
public class Storage {
    private final Parser parser;
    private final TaskList taskList;
    private final Interpreter intrpr;

    /**
     * Initializes the storage with the specified parser, interpreter and task list.
     *
     * @param parser Parser that parsers a command String.
     * @param intrpr Interpreter that interprets and executes tokens.
     * @param taskList ArrayList of task.
     */
    public Storage(Parser parser, Interpreter intrpr, TaskList taskList) {
        this.parser = parser;
        this.intrpr = intrpr;
        this.taskList = taskList;
    }

    /**
     * Saves the tasks from taskList into a file in the specified path.
     *
     * @param path String containing the file path.
     * @throws IOException When file is corrupted.
     */
    public void saveTasksToFile(String path) throws IOException {
        File f = new File("./data/simpli.csv");

        if (!f.isFile() && f.getParentFile().mkdir() && !f.createNewFile()) {
            throw new IOException();
        }

        FileWriter fileWriter = new FileWriter(path, false);
        for (Task task : taskList.tasks()) {
            fileWriter.write(task.toCsv() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Load the tasks from a file in the specified file path into taskList.
     *
     * @param path String containing the file path.
     * @throws IOException When file is not found.
     * @throws ActionException When the action or task does not exist.
     */
    public void loadTasksfromFile(String path) throws IOException, ActionException {

        File f = new File(path);

        if (!f.isFile() && f.getParentFile().mkdir() && !f.createNewFile()) {
            throw new IOException();
        }

        Scanner scanner = new Scanner(f);
        while (scanner.hasNext()) {
            String[] tokens = parser.parseCsv(scanner.nextLine());

            intrpr.interpret(tokens);
        }
    }
}
