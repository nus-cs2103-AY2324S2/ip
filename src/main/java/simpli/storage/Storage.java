package simpli.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import simpli.exceptions.CommandException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.tasks.Task;
import simpli.tasks.TaskList;

/**
 * Handles the saving and loading of tasks to and from the filesystem.
 */
public class Storage {
    private Parser parser;
    private TaskList taskList;
    private Interpreter intrpr;

    /**
     * Initializes the storage with the specified parser, interpreter and task list.
     *
     * @param parser Parser that parsers a command String.
     * @param intrpr Interpreter that interprets and executes tokens.
     * @param taskList ArrayList of task.
     */
    public Storage(Parser parser, Interpreter intrpr, TaskList taskList) {
        assert parser != null : "Parser object not found";
        assert intrpr != null : " Interpreter object not found";
        assert taskList != null : "TaskList object not found";
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
     * Loads the tasks from a file in the specified file path into taskList.
     *
     * @param path String containing the file path.
     * @throws IOException When file is not found.
     * @throws CommandException When the action or task does not exist.
     */
    public void loadTasksFromFile(String path) throws IOException, CommandException, TaskException {

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
